package Protocolo;

import java.util.ArrayList;

public class FuncoesDoProtocolo {

    public static ArrayList<String> respondeOla(String faseDoCLiente) {
        ArrayList<String> response =  new ArrayList<String>();
        response.add("Ola, bem vindo a loja de materiais de construcoes, " +
                "IC Ink. Se deseja ver o catalogo digite 'Catalogo'");
        return response;
    }

    public static ArrayList<String> respondeCatalogo(String faseDoCLiente) {
        ArrayList<String> response =  new ArrayList<String>();

        if(faseDoCLiente == null || faseDoCLiente == "inicial"){
            response.add("Cimento -- 25 R$ / saco");
            response.add("Areia -- 30 R$ / metro");
        }
        return response;
    }


}
