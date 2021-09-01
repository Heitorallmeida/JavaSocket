import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Servidor {

	public static void main(String argv[]) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("DIGITE A PORTA DE CONEXÃO QUE DESEJA UTILIZAR:");
		int portNumber = input.nextInt();
		
		System.out.println("SERVIDOR INICIOU, ESPERANDO CONEXÃO NA PORTA " + portNumber + "!");

		try(ServerSocket serverSocket = new ServerSocket(portNumber);) {
			while (true) {

				Socket connectionSocket = serverSocket.accept();
				Thread t = new Thread(new ClienteThread(connectionSocket));
				t.start();
			}
		}
		catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port"
					+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}