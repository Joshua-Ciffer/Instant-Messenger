package src.instantMessengerTest;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

@SuppressWarnings("javadoc")
public final class SocketExample {

	private SocketExample() {}
	
	public class Server {
		
		private ServerSocket clientConnection;
		
		private ObjectOutputStream outboundTraffic;
		
		private ObjectInputStream inboundTraffic;
		
		public Server() {
			try {
				clientConnection = new ServerSocket();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public class Client {
		
		private Socket serverConnection;
		
		private ObjectOutputStream outboundTraffic;
		
		private ObjectInputStream inboundTraffic;
		
		private Scanner userInput;
		
		public Client(String ip, int port) {
			try {
				serverConnection = new Socket(ip, port);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				outboundTraffic = (ObjectOutputStream)serverConnection.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				inboundTraffic = (ObjectInputStream)serverConnection.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			userInput = new Scanner(System.in);
		}
		
		public Socket getSocket() {
			return serverConnection; 
		}
		
		public ObjectOutputStream getOutboundTraffic() {
			return outboundTraffic;
		}
		
		public ObjectInputStream getInboundTraffic() {
			return inboundTraffic;
		}
		
		public Scanner getUserInput() {
			return userInput;
		}
		
	}

}