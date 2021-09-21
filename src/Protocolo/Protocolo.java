package Protocolo;

import models.Estoque;
import models.Status;
import models.Pedido;

import java.util.*;

public class Protocolo {

    FuncoesDoProtocolo funcoes = new FuncoesDoProtocolo();

    public ArrayList<String> processaMensagem(String mensagem, Status statusCliente, Estoque catalogo, HashMap<List<String>, String> protocolos, HashMap<Integer, Pedido> pedidos, HashMap<Integer, Status> clientes){
        String mensagemSemId = mensagem;

        int userId = -1;

        if (mensagem!= null && mensagem.contains("&")) {
            mensagemSemId = mensagem.split("&")[0];
            userId =  Integer.parseInt(mensagem.split("&")[1]);
        }
        if(mensagemSemId.equals("status")){
            return funcoes.processa("respondeStatus", statusCliente, catalogo, mensagemSemId, pedidos, userId);
        }
        else if(statusCliente.getStatusCliente() == "ANALISE_STATUS" || mensagemSemId.equals("finalizar pedido")){
            return funcoes.processa("respondeStatus", statusCliente, catalogo, mensagemSemId, pedidos, userId);
        }
        else if(statusCliente.getStatusCliente() == "ESCOLHENDO_PRODUTO"){
            return funcoes.processa("respondeItem", statusCliente, catalogo, mensagemSemId, pedidos, userId);
        }
        else if(statusCliente.getStatusCliente() == "QUANTIDADE_PRODUTO"){
            return funcoes.processa("respondeQuantidade", statusCliente, catalogo, mensagemSemId, pedidos, userId);
        }
        else{
            Iterator<List<String>> protocolosIterator = protocolos.keySet().iterator();
            while (protocolosIterator.hasNext()) {
                List<String> protocoloKey = protocolosIterator.next();
                for (String key : protocoloKey) {
                    if (mensagem.contains(key)) {
                        return funcoes.processa(protocolos.get(protocoloKey), statusCliente, catalogo, null, pedidos, userId);
                    }
                }
            }
        }
        ArrayList<String> response =  new ArrayList<String>();
        response.add("");
        return response;
    }

 }
