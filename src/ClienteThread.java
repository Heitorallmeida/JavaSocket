import Protocolo.Protocolo;
import Utils.StatusCliente;
import models.Estoque;
import models.Status;
import models.Pedido;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ClienteThread implements Runnable {

	private Socket connectionSocket;

	private Estoque estoque;

	private Status statusCliente;

	private Pedido pedido;

	HashMap<List<String>, String> protocolos;


	public ClienteThread(Socket socket, Estoque estoque, Status status, HashMap<List<String>, String> protocolos, Pedido pedido) {

		this.connectionSocket = socket;
		this.estoque = estoque;
		this.statusCliente = status;
		this.protocolos = protocolos;
		this.pedido = pedido;
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

			clientSentence = inFromClient.readLine(); //recebido do cliente

			mensagemDoProtocolo = protocolo.
					processaMensagem(clientSentence,statusCliente, estoque, protocolos, pedido);
			/*
			for(int i = 0; i < mensagemDoProtocolo.size();i++){
				System.out.println(mensagemDoProtocolo.get(i));
			}*/
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
