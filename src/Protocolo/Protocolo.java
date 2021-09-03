package Protocolo;

import java.util.*;
import java.util.function.Function;

public class Protocolo {
    public HashMap<List<String>, Function<String, ArrayList<String>>> protocolos = new HashMap<>();




    public void criaBancoDeMensagem(){
        List<String> mensagensDeApresentacao = List.of("ola","oi","helo");
        protocolos.put(mensagensDeApresentacao ,FuncoesDoProtocolo::respondeOla);
        List<String> mensagensDeCatalogo = List.of("catalogo","menu","produtos", "comprar");
        protocolos.put(mensagensDeCatalogo ,FuncoesDoProtocolo::respondeCatalogo);

    }

    public ArrayList<String> processaMensagem(String mensagem){
        Iterator<List<String>> protocolosIterator = protocolos.keySet().iterator();
        while(protocolosIterator.hasNext()){
            List<String> protocoloKey = protocolosIterator.next();
            for(String key : protocoloKey){
                if(mensagem.contains(key)) {
                    return protocolos.get(protocoloKey).apply(mensagem);
                }
            }
        }
        ArrayList<String> response =  new ArrayList<String>();
        response.add("Desculpe, não entendi oque você deseja, tente buscar, 'catalogo' ou 'status'");
        return response;
    }

 }
