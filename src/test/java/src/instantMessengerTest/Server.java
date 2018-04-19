package src.instantMessengerTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static src.instantMessengerTest.Constants.SERVER_PORT;

import static src.instantMessenger.util.Constants.getTime;

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
	
	private DataInputStream inboundTraffic;
	
	private DataOutputStream outboundTraffic;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Server server = new Server(SERVER_PORT);
		while (server.getClientConnection().isConnected()) {
			String message = server.getInboundTraffic().readUTF();
			if (message != null) {
				System.out.println(server.getInboundTraffic().readUTF());
				server.getOutboundTraffic().writeUTF("Message RECEIVED!!" + getTime());
			}
			
			
		}
	}
	
	public Server(int port) throws IOException {
		clientListener = new ServerSocket(port);
		clientConnection = clientListener.accept();
		inboundTraffic = new DataInputStream(clientConnection.getInputStream());
		outboundTraffic = new DataOutputStream(clientConnection.getOutputStream());
	}
	
	public ServerSocket getClientListener() {
		return clientListener;
	}
	
	public Socket getClientConnection() {
		return clientConnection;
	}
	public DataInputStream getInboundTraffic() {
		return inboundTraffic;
	}
	public DataOutputStream getOutboundTraffic() {
		return outboundTraffic;
	}
	
}