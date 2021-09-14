import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

class Cliente {

	public static void main(String argv[]) throws Exception {
		String sentence = "";
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

		while(!sentence.equals("sair")) {
			Socket clientSocket = new Socket("localhost", 5566);

			ArrayList<String> titleList;

			BufferedReader entradaDeTextoDousuario = new BufferedReader(new InputStreamReader(System.in)); //entrada cliente(1)

			DataOutputStream saidaParaServidor = new DataOutputStream(clientSocket.getOutputStream());
			//entrada do servidor, saida do cliente


			ObjectInputStream entradaDoServidor = new ObjectInputStream(clientSocket.getInputStream());
			//saida do servidor, entrada(2) cliente

			sentence = entradaDeTextoDousuario.readLine();
			if(!sentence.equals("sair")) {
				saidaParaServidor.writeBytes(sentence + '\n'); //envio ao servidor

				Object arrayDeResposta = entradaDoServidor.readObject(); //retorno servidor
				titleList =  (ArrayList<String>) arrayDeResposta;
				titleList.forEach(title->System.out.println(title));

			}
			clientSocket.close();
		}
	}
}