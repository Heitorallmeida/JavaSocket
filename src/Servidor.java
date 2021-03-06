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
		System.out.println("DIGITE A PORTA DE CONEXAO QUE DESEJA UTILIZAR:");
		int portNumber = input.nextInt();


		//System.out.println("SERVIDOR INICIOU, ESPERANDO CONEXÃO NA PORTA " + portNumber + "!");



		HashMap<Produto, Integer> produtos = new HashMap<Produto, Integer>();
		produtos.put(new Produto("areia",20.00,"João","Material de contrucao"), 20);
		produtos.put(new Produto("cimento",25.00,"João","Material de contrucao"),20);
		produtos.put(new Produto("brita",50.00,"pedra","Material de contrucao"), 30);
		produtos.put(new Produto("argamassa",15.00,"massa","Material de contrucao"),25);
		produtos.put(new Produto("tijolo",2.00,"pedra","Material de contrucao"), 1000);
		produtos.put(new Produto("bloco",5.00,"casa","Material de contrucao"),1000);
		produtos.put(new Produto("telha",7.00,"telhados","Material de contrucao"), 1000);
		produtos.put(new Produto("janela",80.00,"janelas","Material de casa"),500);
		produtos.put(new Produto("porta",100.00,"portas","Material de casa"),500);

		ArrayList<Produto> produtos_pedido = new ArrayList<Produto>();
		Date data = new Date(System.currentTimeMillis());

		Estoque estoque = new Estoque(produtos);
		//new Pedido(produtos_pedido, 0.0, data);
		HashMap<Integer, Pedido> pedidos = new HashMap<>();



		try(ServerSocket serverSocket = new ServerSocket(portNumber);) {
			HashMap<Integer, Status> clientes = new HashMap<>();
			Status statusCliente = new Status(StatusCliente.INICIAL.getValor());


			HashMap<List<String>, String> protocolos = BancoDeMensagens.criaBancoDeMensagem();
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