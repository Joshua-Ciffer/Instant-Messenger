package src.instantMessenger.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 *
 * @author Joshua
 * @version 04/12/2018
 */
public class Server {

	/**
	 * 
	 */
	private ServerSocket clientListener;
	
	/**
	 * 
	 */
	private Socket clientConnection;
	
	/**
	 * 
	 */
	private BufferedReader inboundTraffic;
	
	/**
	 *
	 *
	 * @throws IOException
	 */
	public Server() throws IOException {
		clientListener = new ServerSocket(123);
		clientConnection = clientListener.accept();
		inboundTraffic = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
	}
	
	/**
	 *
	 *
	 * @return df
	 */
	public Socket getClientConnection() {
		return clientConnection;
	}
	
	/**
	 *
	 *
	 * @return jksdf
	 */
	public BufferedReader getInboundTraffic() {
		return inboundTraffic;
	}
	
}