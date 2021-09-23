package Protocolo;

import Utils.StatusCliente;
import models.Estoque;
import models.Produto;
import models.Status;
import models.Pedido;

import java.util.*;

public class FuncoesDoProtocolo {

    String statusCliente;

    public ArrayList<String> respondeOla(String faseDoCLiente) {
        ArrayList<String> response =  new ArrayList<String>();
        response.add("Ola, bem vindo a loja de materiais de construcoes, " +
                "IC Ink.");
        response.add("\n->Para ver status de um pedido digite: 'status'" +
                     "\n->Para ver o catalogo de produtos: 'catalogo', 'menu', 'produtos'...\n");
        return response;
    }

    public ArrayList<String> respondeCatalogo(Estoque estoque, Status statusCliente) {
        ArrayList<String> response =  new ArrayList<String>();

        response = estoque.retornaCatalogo();
        statusCliente.setStatusCliente("ESCOLHENDO_PRODUTO");
        response.add("Digite o nome do item que deseja comprar");
        return response;
    }

    public ArrayList<String> respondeStatus(String mensagem, HashMap<Integer, Pedido> pedidos, int userId, Status statusCliente) {
        ArrayList<String> response =  new ArrayList<String>();

        Pedido pedido = pedidos.get(userId);

        if(pedido == null || pedido.getProdutos().size() == 0){
            response.add("Desculpe, ainda não foi realizado nenhum pedido.");
            response.add("Para ver o catalogo e realizar um pedido digite: 'catalogo', 'menu', 'produtos'...");
        }
        else{
            response.add("\nSeu pedido inclui:");
            Set produtosUnicos = new HashSet(pedido.getProdutos());

            for (Object setProduto : produtosUnicos){
                Produto produto = (Produto) setProduto;
                response.add("-" + produto.getNome());
            }
            response.add("Total do pedido: R$" + pedido.getPreco());
            response.add("Data do pedido: " + pedido.getData());
            response.add("---------------------------------------");
            statusCliente.setStatusCliente("INICIAL");
            response.add("\n->Digite 'catalogo' se deseja o ver novamente e adicionar outro produto\n"
                    + "->'status' para visualizar o pedido atual\n"
                    + "->'finalizar pedido' pata finalizar\n");
        }
        if(mensagem.equals("finalizar pedido")){
            response.add("finalizar pedido");
        }
        return response;
    }

    public ArrayList<String> respondeItem(Estoque estoque, String mensagem, Status statusCliente, HashMap<Integer, Pedido> pedidos, int userId) {
        ArrayList<String> response =  new ArrayList<String>();

        Produto produto = estoque.buscaProduto(mensagem);

        if(produto == null || estoque.getProdutos().get(produto) == 0){
            response.add("Desculpe não foi possivel achar o produto ou o mesmo está esgotado, tente novamente outro produto.");
        }
        else{
            ArrayList<Produto> produtos_pedido = new ArrayList<Produto>();
            Date data = new Date(System.currentTimeMillis());
            if(pedidos.get(userId) == null) {
                pedidos.put(userId, new Pedido(produtos_pedido, 0.0, data));
                pedidos.get(userId).add_produto(produto);
            } else {
                pedidos.get(userId).add_produto(produto);
            }

            statusCliente.setStatusCliente("QUANTIDADE_PRODUTO");
            response.add("Informe a quantidade do produto desejada");
        }
        return response;
    }

    public ArrayList<String> respondeQuantidade(Estoque estoque, String mensagem, Status statusCliente, HashMap<Integer, Pedido> pedidos, int userId) {
        ArrayList<String> response =  new ArrayList<String>();
        Pedido pedido = pedidos.get(userId);

        Produto produto = pedido.busca_Ultimo_produto();

        if(estoque.getProdutos().get(produto) < Integer.parseInt(mensagem)){
            response.add("Desculpa, temos apenas " + estoque.getProdutos().get(produto) + " disponivel em estoque.\nTente um novo valor.");
        }
        else{
            estoque.atualizar_estoque(produto, Integer.parseInt(mensagem));
            System.out.println("quantide em estoque " + estoque.getProdutos().get(produto)); // checagem ok


            pedido.total_pedido(produto, Integer.parseInt(mensagem));

            System.out.println("Total pedido $$$: " + pedido.getPreco());
            System.out.println("Data pedido: " + pedido.getData());
            response.add("Item adicionado ao carrinho");
            statusCliente.setStatusCliente("INICIAL");
            response.add("\n->Digite 'catalogo' se deseja o ver novamente e adicionar outro produto\n"
                        + "->'status' para visualizar o pedido atual\n"
                        + "->'finalizar pedido' pata finalizar\n");
        }
        return response;
    }


    public ArrayList<String> processa(String funcao, Status statusCliente, Estoque estoque, String mensagem, HashMap<Integer, Pedido> pedidos, int userId) {
        ArrayList<String> response =  new ArrayList<String>();

        System.out.println("Status Cliente em processa:" + statusCliente.getStatusCliente());
        this.statusCliente = statusCliente.getStatusCliente();
        if(funcao == "respondeOla"){
            return this.respondeOla(funcao);
        } else if(funcao == "respondeStatus"){
            return this.respondeStatus(mensagem, pedidos, userId, statusCliente);
        } else if(funcao == "respondeCatalogo"){
            return this.respondeCatalogo(estoque, statusCliente);
        } else if(funcao == "respondeItem"){
            return this.respondeItem(estoque,mensagem,statusCliente, pedidos, userId);
        } else if(funcao == "respondeQuantidade"){
            return this.respondeQuantidade(estoque,mensagem,statusCliente, pedidos, userId);
        }
        return response;
    }
}
