package src.instantMessengerTest;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

@SuppressWarnings("javadoc")
public final class SocketExample {

	private static int serverPort = 27015;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client("localhost", serverPort);
		Server server = new Server(serverPort);
	}
		
	public static class Server {
		
		public native void doStuff();
		
		private ServerSocket clientListener;
		
		private Socket clientConnection;
		
		private ObjectOutputStream outboundTraffic;
		
		private ObjectInputStream inboundTraffic;
		
		public Server(int port) throws IOException {
			clientListener = new ServerSocket(port);
			clientConnection = clientListener.accept();
			outboundTraffic = (ObjectOutputStream)clientConnection.getOutputStream();
			inboundTraffic = (ObjectInputStream)clientConnection.getInputStream();
			
		}
		
		public ServerSocket getClientListener() {
			return clientListener;
		}
		
		public Socket getClientConnection() {
			return clientConnection;
		}
		
		public ObjectOutputStream getOutboundTraffic() {
			return outboundTraffic;
		}
		
		public ObjectInputStream getInboundTraffic() {
			return inboundTraffic;
		}
		
	}
	
	public static class Client {
		
		private Socket serverConnection;
		
		private ObjectOutputStream outboundTraffic;
		
		private ObjectInputStream inboundTraffic;
		
		private Scanner userInput;
		
		public Client(String ip, int port) throws UnknownHostException, IOException {
			serverConnection = new Socket(ip, port);
			outboundTraffic = (ObjectOutputStream)serverConnection.getOutputStream();
			inboundTraffic = (ObjectInputStream)serverConnection.getInputStream();
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