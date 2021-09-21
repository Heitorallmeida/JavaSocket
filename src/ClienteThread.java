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

	HashMap<Integer, Status> clientes;


	public ClienteThread(Socket socket, Estoque estoque, Status status, HashMap<List<String>, String> protocolos, Pedido pedido, HashMap<Integer, Status> clientes) {
		this.connectionSocket = socket;
		this.estoque = estoque;
		this.statusCliente = status;
		this.protocolos = protocolos;
		this.pedido = pedido;
		this.clientes = clientes;
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

			int meuId;
			//"ola$1";
			//meuId = ["ola", "1"];
			if(clientSentence.contains("&")) {
				meuId = Integer.parseInt(clientSentence.split("&")[1]);
			}else {
				meuId = clientes.size();
				clientes.put(meuId, new Status(StatusCliente.INICIAL.getValor()));
			}

			mensagemDoProtocolo = protocolo.
					processaMensagem(clientSentence,clientes.get(meuId), estoque, protocolos, pedido, clientes);

			mensagemDoProtocolo.add("id&"+Integer.toString(meuId));

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
