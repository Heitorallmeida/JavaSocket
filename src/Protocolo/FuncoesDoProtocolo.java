package Protocolo;

import Utils.StatusCliente;
import models.Estoque;
import models.Produto;
import models.Status;

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

    public ArrayList<String> respondeItem(Estoque estoque, String mensagem, Status statusCliente) {
        ArrayList<String> response =  new ArrayList<String>();

        Produto produto = estoque.buscaProduto(mensagem);

        if(produto == null){
            response.add("Desculpe não foi possivel achar o produto, se deseja sair da compra digite 'sair'");
        }
        else{
            statusCliente.setStatusCliente("QUANTIDADE_PRODUTO");
            response.add("Informe a quantidade do produto desejada");
        }
        return response;
    }

    public ArrayList<String> respondeQuantidade(Estoque estoque, String mensagem, Status statusCliente) {
        ArrayList<String> response =  new ArrayList<String>();


        response.add("Item adicionado ao carrinho");
        statusCliente.setStatusCliente("ESCOLHENDO_PRODUTO");

        return response;
    }


    public ArrayList<String> processa(String funcao, Status statusCliente, Estoque estoque, String mensagem) {
        ArrayList<String> response =  new ArrayList<String>();
        System.out.println("Status Cliente em processa:" + statusCliente.getStatusCliente());
        this.statusCliente = statusCliente.getStatusCliente();
        if(funcao == "respondeOla"){
            return this.respondeOla(funcao);
        } else if(funcao == "respondeCatalogo"){
            return this.respondeCatalogo(estoque, statusCliente);
        } else if(funcao == "respondeItem"){
            return this.respondeItem(estoque,mensagem,statusCliente);
        } else if(funcao == "respondeQuantidade"){
            return this.respondeQuantidade(estoque,mensagem,statusCliente);
        }


        return response;
    }
}
