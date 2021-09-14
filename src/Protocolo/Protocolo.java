package Protocolo;

import models.Estoque;
import models.Status;

import java.util.*;
import java.util.function.Function;

public class Protocolo {

    FuncoesDoProtocolo funcoes = new FuncoesDoProtocolo();

    public ArrayList<String> processaMensagem(String mensagem, Status statusCliente, Estoque catalogo, HashMap<List<String>, String> protocolos){
        System.out.println(statusCliente.getStatusCliente());
        if(statusCliente.getStatusCliente() == "ESCOLHENDO_PRODUTO"){
            return funcoes.processa("respondeItem", statusCliente, catalogo, mensagem);
        }
        if(statusCliente.getStatusCliente() == "QUANTIDADE_PRODUTO"){
            return funcoes.processa("respondeQuantidade", statusCliente, catalogo, mensagem);
        }
        else{
            Iterator<List<String>> protocolosIterator = protocolos.keySet().iterator();
            while (protocolosIterator.hasNext()) {
                List<String> protocoloKey = protocolosIterator.next();
                for (String key : protocoloKey) {
                    if (mensagem.contains(key)) {
                        return funcoes.processa(protocolos.get(protocoloKey), statusCliente, catalogo, null);
                    }
                }
            }
        }
        ArrayList<String> response =  new ArrayList<String>();
        response.add("Desculpe, não entendi oque você deseja, tente buscar, 'catalogo' ou 'status'");
        return response;
    }

 }
