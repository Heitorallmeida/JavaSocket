package Protocolo;

import Utils.StatusCliente;
import models.Estoque;

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

    public ArrayList<String> respondeCatalogo(Estoque estoque) {
        ArrayList<String> response =  new ArrayList<String>();


        response = estoque.retornaCatalogo();

        response.add("Digite o nome do item que deseja comprar");
        return response;
    }

    public ArrayList<String> respondeItem(Estoque catalogo) {
        ArrayList<String> response =  new ArrayList<String>();

        return response;
    }


    public ArrayList<String> processa(String funcao, Estoque catalogo) {
        ArrayList<String> response =  new ArrayList<String>();
        System.out.println(funcao);
        if(funcao == "respondeOla"){
            return this.respondeOla(funcao);
        } else if(funcao == "respondeCatalogo"){
            return this.respondeCatalogo(catalogo);
        }
        return response;
    }
}
