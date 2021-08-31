package server_config;

import java.net.ServerSocket;
import java.net.Socket;


class Servidor {

	public static void main(String argv[]) throws Exception {
		
		System.out.println("SERVIDOR INICIOU, ESPERANDO CONEXÃO NA PORTA 6789!");
		
		ServerSocket serverSocket = new ServerSocket(6789);

		while (true) {
			Socket clientSocket = serverSocket.accept();

			Thread clientThread = new Thread(new ClienteThread(clientSocket));

			System.out.println("Conexão bem sucedida a:"+ clientSocket.getInetAddress() + clientSocket.getPort());

			clientThread.start();
		}
	}
}