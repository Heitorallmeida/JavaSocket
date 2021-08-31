package server_config;

import java.io.*;
import java.net.*;

class Cliente {

	public static void main(String argv[]) throws Exception {
		String sentence = null;
		String modifiedSentence;
		System.out.println("CLIENTE INICIADO, DIGITE UM TEXTO: ");
		Socket clientSocket = new Socket("localhost", 6789);

		BufferedReader inputFromServer = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));

		String fromServer;
		while((fromServer = inputFromServer.readLine()) != null) {
			System.out.println("Entrou no while");

			System.out.println("Server: " + fromServer);

			DataOutputStream outToServer = new DataOutputStream(
					clientSocket.getOutputStream());

			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

			sentence = inFromUser.readLine();

			System.out.println("Sentença no cliente"+sentence);

			outToServer.writeBytes(sentence + '\n');

			modifiedSentence = inFromServer.readLine();

			System.out.println("FROM SERVER: " + modifiedSentence);
		}
		clientSocket.close();

	}
}