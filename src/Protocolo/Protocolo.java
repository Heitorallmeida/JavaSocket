package Protocolo;

import models.Estoque;

import java.util.*;
import java.util.function.Function;

public class Protocolo {

    public HashMap<List<String>, String> protocolos = new HashMap<>();
    FuncoesDoProtocolo funcoes = new FuncoesDoProtocolo();

    public void criaBancoDeMensagem(){
        List<String> mensagensDeApresentacao = List.of("ola","oi","helo");
        protocolos.put(mensagensDeApresentacao ,"respondeOla");
        List<String> mensagensDeCatalogo = List.of("catalogo","menu","produtos", "comprar");
        protocolos.put(mensagensDeCatalogo ,"respondeCatalogo");
    }

    public ArrayList<String> processaMensagem(String mensagem, String statusCliente, Estoque catalogo){
        Iterator<List<String>> protocolosIterator = protocolos.keySet().iterator();
        while(protocolosIterator.hasNext()){
            List<String> protocoloKey = protocolosIterator.next();
            for(String key : protocoloKey){
                if(mensagem.contains(key)) {
                    return funcoes.processa(protocolos.get(protocoloKey),catalogo);
                }
            }
        }
        ArrayList<String> response =  new ArrayList<String>();
        response.add("Desculpe, não entendi oque você deseja, tente buscar, 'catalogo' ou 'status'");
        return response;
    }

 }
