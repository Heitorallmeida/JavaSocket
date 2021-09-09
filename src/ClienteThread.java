import Protocolo.Protocolo;
import Utils.StatusCliente;
import models.Estoque;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClienteThread implements Runnable {

	private Socket connectionSocket;

	private Estoque estoque;

	public ClienteThread(Socket s, Estoque e) {
		this.connectionSocket = s;
		this.estoque = e;
	}

	public void run() {
		String clientSentence;
		ArrayList<String> mensagemDoProtocolo;

		BufferedReader inFromClient;
		ObjectOutputStream outToClient;

		Protocolo protocolo = new Protocolo();
		protocolo.criaBancoDeMensagem();

		String statusCliente = StatusCliente.INICIAL.getValor();



		try {
			inFromClient = new BufferedReader(new InputStreamReader(
					connectionSocket.getInputStream()));

			outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());

			clientSentence = inFromClient.readLine();

			mensagemDoProtocolo = protocolo.processaMensagem(clientSentence,statusCliente, estoque);

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
