import java.io.*;
import java.net.*;
import java.util.Scanner;

class Cliente {

	public static void main(String argv[]) throws Exception {
		String sentence = "null";
		String modifiedSentence;
		Scanner input = new Scanner(System.in);
		System.out.println("DIGITE A PORTA CORRESPONDENTE AO SERVIDOR DA APLICAÇÃO: ");
		int portNumber = input.nextInt();
		System.out.println("CLIENTE INICIADO, DIGITE UM TEXTO: ");

		while(!sentence.equals("sair")) {

			Socket clientSocket = new Socket("localhost", portNumber);
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); //entrada cliente(1)

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			//entrada do servidor, saida do cliente

			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			//saida do servidor, entrada(2) cliente

			sentence = inFromUser.readLine();
			if(!sentence.equals("sair")) {
				outToServer.writeBytes(sentence + '\n'); //envio ao servidor

				modifiedSentence = inFromServer.readLine(); //retorno servidor

				System.out.println("FROM SERVER: " + modifiedSentence);
			}
			clientSocket.close();
		}
	}
}