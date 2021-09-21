import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

class Cliente {

    public static void main(String argv[]) throws Exception {
        String sentence = "";

        Scanner input = new Scanner(System.in);

        System.out.println("DIGITE O IP DE CONEXÃO QUE DESEJA UTILIZAR:");
        String ip = input.nextLine();

		/*
		Scanner input = new Scanner(System.in);
		System.out.println("DIGITE A PORTA CORRESPONDENTE AO SERVIDOR DA APLICAÇÃO: ");
		int portNumber = input.nextInt();
		*/
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("VOCÊ FOI CONECTADO AO SISTEMA, POR FAVOR DIGITE OQUE DESEJA");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Algumas pções 'ola', 'oi', 'hello'...");

        byte[] b = InetAddress.getByName(ip).getAddress();


        int meuId = -1;
        while(!sentence.equals("sair")) {
            Socket clientSocket = new Socket(InetAddress.getByAddress(b).getHostName(), 5566);

            ArrayList<String> titleList;

            BufferedReader entradaDeTextoDousuario = new BufferedReader(new InputStreamReader(System.in)); //entrada cliente(1)

            DataOutputStream saidaParaServidor = new DataOutputStream(clientSocket.getOutputStream());
            //entrada do servidor, saida do cliente

            ObjectInputStream entradaDoServidor = new ObjectInputStream(clientSocket.getInputStream());
            //saida do servidor, entrada(2) cliente

            sentence = entradaDeTextoDousuario.readLine();
            if(!sentence.equals("sair")) {

                if(meuId != -1){
                    sentence = sentence + "&" + Integer.toString(meuId);
                }
                saidaParaServidor.writeBytes(sentence + '\n'); //envio ao servidor

                Object arrayDeResposta = entradaDoServidor.readObject(); //retorno servidor


                titleList =  (ArrayList<String>) arrayDeResposta;


                int titleSize = titleList.size();
                if(meuId == -1){
                    meuId = Integer.parseInt(titleList.get(titleSize-1).split("&")[1]);
                }
                titleList.forEach(title-> {
                    if(!title.contains("&")){
                        if(title.equals("finalizar pedido")){
                            System.out.println(title);
                            System.out.println("Pedido realizado!!!\nDigite 'sair', para a finalizar a conexão");
                        }else{
                            System.out.println(title);
                        }
                    }
                });
            }
            clientSocket.close();
        }
    }
}