import Utils.BancoDeMensagens;
import Utils.StatusCliente;
import models.Estoque;
import models.Produto;
import models.Status;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Servidor {

	public static void main(String argv[]) throws Exception {
		/*
		Scanner input = new Scanner(System.in);
		System.out.println("DIGITE A PORTA DE CONEXÃO QUE DESEJA UTILIZAR:");
		int portNumber = input.nextInt();
		
		System.out.println("SERVIDOR INICIOU, ESPERANDO CONEXÃO NA PORTA " + portNumber + "!");

		 */

		HashMap<Produto, Integer> produtos = new HashMap<Produto, Integer>();
		produtos.put(new Produto("areia",20.00,"João","Material de contrucao"), 20);
		produtos.put(new Produto("cimento",25.00,"João","Material de contrucao"),20);

		Estoque estoque = new Estoque(produtos);

		try(ServerSocket serverSocket = new ServerSocket(5566);) {
			Status statusCliente = new Status(StatusCliente.INICIAL.getValor());
			HashMap<List<String>, String> protocolos = BancoDeMensagens.criaBancoDeMensagem();

			while (true) {
				Socket connectionSocket = serverSocket.accept();
				Thread t = new Thread(new ClienteThread(connectionSocket, estoque,statusCliente, protocolos));
				t.start();
			}
		}
		catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port"
					+ 5566 + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}