package src.instantMessengerTest;

import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws IOException {
		new Server();
		new Client();
	}
	
	private ServerSocket clientConnection;

	public Server() throws IOException {
		clientConnection = new ServerSocket(27015);
		clientConnection.accept();
	}

	public static class Client {

		private Socket serverConnection;

		private Scanner userInput;

		private ObjectInputStream inboundTraffic;

		private ObjectOutputStream outboundTraffic;

		public Client() throws UnknownHostException, IOException {
			userInput = new Scanner(System.in);
			serverConnection = new Socket("localhost", 27015);
			inboundTraffic = (ObjectInputStream)serverConnection.getInputStream();
			outboundTraffic = (ObjectOutputStream)serverConnection.getOutputStream();
			while (serverConnection.isConnected()) {
				System.out.print("Enter a message: ");
				outboundTraffic.writeObject(userInput.nextLine());
				outboundTraffic.flush();
			}
			inboundTraffic.close();
			outboundTraffic.close();
			serverConnection.close();
			userInput.close();
		}

	}

}