package Protocolo;

import models.Estoque;
import models.Status;
import models.Pedido;

import java.util.*;
import java.util.function.Function;

public class Protocolo {

    FuncoesDoProtocolo funcoes = new FuncoesDoProtocolo();

    public ArrayList<String> processaMensagem(String mensagem, Status statusCliente, Estoque catalogo, HashMap<List<String>, String> protocolos, Pedido pedido){
        System.out.println(statusCliente.getStatusCliente());
        System.out.println("Mensagem " + mensagem);
        if(mensagem.equals("status")){
            return funcoes.processa("respondeStatus", statusCliente, catalogo, mensagem, pedido);
        }
        else if(statusCliente.getStatusCliente() == "ANALISE_STATUS"){
            return funcoes.processa("respondeStatus", statusCliente, catalogo, mensagem, pedido);
        }
        else if(statusCliente.getStatusCliente() == "ESCOLHENDO_PRODUTO"){
            return funcoes.processa("respondeItem", statusCliente, catalogo, mensagem, pedido);
        }
        else if(statusCliente.getStatusCliente() == "QUANTIDADE_PRODUTO"){
            return funcoes.processa("respondeQuantidade", statusCliente, catalogo, mensagem, pedido);
        }
        else{
            Iterator<List<String>> protocolosIterator = protocolos.keySet().iterator();
            while (protocolosIterator.hasNext()) {
                List<String> protocoloKey = protocolosIterator.next();
                for (String key : protocoloKey) {
                    if (mensagem.contains(key)) {
                        return funcoes.processa(protocolos.get(protocoloKey), statusCliente, catalogo, null, pedido);
                    }
                }
            }
        }
        ArrayList<String> response =  new ArrayList<String>();
        response.add("Desculpe, não entendi oque você deseja, tente buscar, 'catalogo' ou 'status'");
        return response;
    }

 }
