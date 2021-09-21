import Utils.BancoDeMensagens;
import Utils.StatusCliente;
import models.Estoque;
import models.Produto;
import models.Status;
import models.Pedido;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

class Servidor {

	public static void main(String argv[]) throws Exception {

		Scanner input = new Scanner(System.in);
		System.out.println("DIGITE A PORTA DE CONEXÃO QUE DESEJA UTILIZAR:");
		int portNumber = input.nextInt();

		System.out.println("DIGITE O IP DE CONEXÃO QUE DESEJA UTILIZAR:");
		Scanner ip = new Scanner(System.in);
		//System.out.println("SERVIDOR INICIOU, ESPERANDO CONEXÃO NA PORTA " + portNumber + "!");



		HashMap<Produto, Integer> produtos = new HashMap<Produto, Integer>();
		produtos.put(new Produto("areia",20.00,"João","Material de contrucao"), 20);
		produtos.put(new Produto("cimento",25.00,"João","Material de contrucao"),20);

		ArrayList<Produto> produtos_pedido = new ArrayList<Produto>();
		Date data = new Date(System.currentTimeMillis());

		Estoque estoque = new Estoque(produtos);
		//new Pedido(produtos_pedido, 0.0, data);
		HashMap<Integer, Pedido> pedidos = new HashMap<>();



		try(ServerSocket serverSocket = new ServerSocket(portNumber);) {
			HashMap<Integer, Status> clientes = new HashMap<>();
			Status statusCliente = new Status(StatusCliente.INICIAL.getValor());


			HashMap<List<String>, String> protocolos = BancoDeMensagens.criaBancoDeMensagem();
			//novas conexoes nunca definem novamente o status como inicial, pois o servidor fica preso no while abaixo
			// o status inicial é definido em novas conexões apenas na linha 36, ou seja, na execucao inicial do servidor
			while (true) {
				Socket connectionSocket = serverSocket.accept();

				Thread t = new Thread(new ClienteThread(connectionSocket, estoque,statusCliente, protocolos, pedidos, clientes));
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