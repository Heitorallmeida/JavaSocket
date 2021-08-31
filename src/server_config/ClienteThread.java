package server_config;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClienteThread implements Runnable {

	private Socket connectionSocket;

	public ClienteThread(Socket s) {
		this.connectionSocket = s;
	}

	public void run() {
		String clientSentence;
		String capitalizedSentence;

		BufferedReader inFromClient;
		DataOutputStream outToClient;
		try {
			inFromClient = new BufferedReader(new InputStreamReader(
					connectionSocket.getInputStream()));

			outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());

			System.out.println(inFromClient);

			String inputLine;

			while ((inputLine = inFromClient.readLine()) != null) {
				System.out.println("InputLine que chegou no Thread"+inputLine);

				clientSentence = inFromClient.readLine();
				capitalizedSentence = clientSentence.toUpperCase() + '\n';

				System.out.println("Setença em maiusclo na thread"+capitalizedSentence);

				outToClient.writeBytes(capitalizedSentence);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
