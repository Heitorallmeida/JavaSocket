package Protocolo;

import Utils.StatusCliente;
import models.Estoque;
import models.Produto;
import models.Status;
import models.Pedido;

import java.util.ArrayList;

public class FuncoesDoProtocolo {

    String statusCliente;

    public ArrayList<String> respondeOla(String faseDoCLiente) {
        ArrayList<String> response =  new ArrayList<String>();
        response.add("Ola, bem vindo a loja de materiais de construcoes, " +
                "IC Ink. Se deseja ver o catalogo digite 'Catalogo'");
        response.add("Se deseja ver o statuos de Algum pedido, digite 'status'");
        return response;
    }

    public ArrayList<String> respondeCatalogo(Estoque estoque, Status statusCliente) {
        ArrayList<String> response =  new ArrayList<String>();

        response = estoque.retornaCatalogo();
        statusCliente.setStatusCliente("ESCOLHENDO_PRODUTO");
        response.add("Digite o nome do item que deseja comprar");
        return response;
    }

    public ArrayList<String> respondeStatus(Pedido pedido) {
        ArrayList<String> response =  new ArrayList<String>();

        if(pedido.getProdutos().size() == 0){
            response.add("Desculpe, ainda não foi realizado nenhum pedido.");
        }
        else{
            response.add("Seu pedido inclui:");
            for(int i = 0; i < pedido.getProdutos().size()-1; i++){
                response.add(pedido.getProdutos().get(i).getNome());
            }
            response.add("Total do pedido: " + pedido.getPreço());
            response.add("Data do pedido: " + pedido.getData());
        }
        return response;
    }

    public ArrayList<String> respondeItem(Estoque estoque, String mensagem, Status statusCliente, Pedido pedido) {
        ArrayList<String> response =  new ArrayList<String>();

        Produto produto = estoque.buscaProduto(mensagem);

        pedido.add_produto(produto);

        response.add("produto: " + produto.getNome());
        response.add("estoque: " + estoque.getProdutos().get(produto));

        if(produto == null || estoque.getProdutos().get(produto) == 0){
            response.add("Desculpe não foi possivel achar o produto ou o mesmo está esgotado, se deseja sair da compra digite 'sair'");
        }
        else{
            statusCliente.setStatusCliente("QUANTIDADE_PRODUTO");
            response.add("Informe a quantidade do produto desejada");
        }
        return response;
    }

    public ArrayList<String> respondeQuantidade(Estoque estoque, String mensagem, Status statusCliente, Pedido pedido) {
        ArrayList<String> response =  new ArrayList<String>();

        mensagem = mensagem.split("&")[0];

        Produto produto = pedido.busca_Ultimo_produto();
        if(estoque.getProdutos().get(produto) < Integer.parseInt(mensagem)){
            response.add("Desculpa, temos apenas " + estoque.getProdutos().get(produto) + " disponivel em estoque.\nTente um novo valor.");
        }
        else{
            estoque.atualizar_estoque(produto, Integer.parseInt(mensagem));
            System.out.println("quantide em estoque " + estoque.getProdutos().get(produto)); // checagem ok
            pedido.total_pedido(produto, Integer.parseInt(mensagem));
            System.out.println("Total pedido $$$: " + pedido.getPreço());
            System.out.println("Data pedido: " + pedido.getData());
            response.add("Item adicionado ao carrinho");
            statusCliente.setStatusCliente("ESCOLHENDO_PRODUTO");
            response.add("Digite 'catalogo' se deseja o ver novamente e adicionar outro produto, ou 'finalizar pedido'.");
        }
        return response;
    }


    public ArrayList<String> processa(String funcao, Status statusCliente, Estoque estoque, String mensagem, Pedido pedido) {
        ArrayList<String> response =  new ArrayList<String>();
        System.out.println("Status Cliente em processa:" + statusCliente.getStatusCliente());
        this.statusCliente = statusCliente.getStatusCliente();
        if(funcao == "respondeOla"){
            return this.respondeOla(funcao);
        } else if(funcao == "respondeStatus"){
            return this.respondeStatus(pedido);
        } else if(funcao == "respondeCatalogo"){
            return this.respondeCatalogo(estoque, statusCliente);
        } else if(funcao == "respondeItem"){
            return this.respondeItem(estoque,mensagem,statusCliente, pedido);
        } else if(funcao == "respondeQuantidade"){
            return this.respondeQuantidade(estoque,mensagem,statusCliente, pedido);
        }


        return response;
    }
}
