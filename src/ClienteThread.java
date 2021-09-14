import Protocolo.Protocolo;
import Utils.StatusCliente;
import models.Estoque;
import models.Status;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ClienteThread implements Runnable {

	private Socket connectionSocket;

	private Estoque estoque;

	private Status statusCliente;

	HashMap<List<String>, String> protocolos;


	public ClienteThread(Socket socket, Estoque estoque, Status status, HashMap<List<String>, String> protocolos) {

		this.connectionSocket = socket;
		this.estoque = estoque;
		this.statusCliente = status;
		this.protocolos = protocolos;
	}

	public void run() {
		String clientSentence;
		ArrayList<String> mensagemDoProtocolo;

		BufferedReader inFromClient;
		ObjectOutputStream outToClient;

		Protocolo protocolo = new Protocolo();

		try {
			inFromClient = new BufferedReader(new InputStreamReader(
					connectionSocket.getInputStream()));

			outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());

			clientSentence = inFromClient.readLine();

			mensagemDoProtocolo = protocolo.
					processaMensagem(clientSentence,statusCliente, estoque, protocolos);

			try {
				outToClient.writeObject(mensagemDoProtocolo);
			}catch (IOException e){
				e.printStackTrace();
			}
			//outToClient.writeBytes(mensagemFinal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//TODO: criar enum representando o status do cliente

}
