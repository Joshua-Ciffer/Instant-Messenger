package src.instantMessengerTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static src.instantMessengerTest.Constants.SERVER_PORT;

/**
 * 
 *
 * @author Joshua
 * @version 04/12/2018
 */
@SuppressWarnings("javadoc")
public class Server {
	
	private ServerSocket clientListener;
	
	private Socket clientConnection;
	
	private BufferedReader inboundTraffic;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Server server = new Server(SERVER_PORT);
		while (server.getClientConnection().isConnected()) {
			System.out.println(server.getInboundTraffic().readLine());
		}
	}
	
	public Server(int port) throws IOException {
		clientListener = new ServerSocket(port);
		clientConnection = clientListener.accept();
		inboundTraffic = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
	}
	
	public ServerSocket getClientListener() {
		return clientListener;
	}
	
	public Socket getClientConnection() {
		return clientConnection;
	}
	public BufferedReader getInboundTraffic() {
		return inboundTraffic;
	}
	
}