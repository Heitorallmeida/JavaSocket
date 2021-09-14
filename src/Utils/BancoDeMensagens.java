package Utils;

import java.util.HashMap;
import java.util.List;

public class BancoDeMensagens {
    static public HashMap<List<String>, String> criaBancoDeMensagem(){
        HashMap<List<String>, String> protocolos = new HashMap<>();

        System.out.println("Criando Banco de Mensagens");
        List<String> mensagensDeApresentacao = List.of("ola","oi","helo");
        protocolos.put(mensagensDeApresentacao ,"respondeOla");
        List<String> mensagensDeCatalogo = List.of("catalogo","menu","produtos", "comprar");
        protocolos.put(mensagensDeCatalogo ,"respondeCatalogo");

        System.out.println("Banco de Mensagens Criado com Sucesso!");


        return protocolos;
    }
}
