package src.instantMessengerTest;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static src.instantMessenger.util.Constants.getTime;
import static src.instantMessengerTest.Constants.SERVER_PORT;

/**
 * Simple command line test server.
 *
 * @author Joshua Ciffer
 * @version 04/19/2018
 */
public class Server {

	/**
	 * The main entry point of the server.
	 *
	 * @param args
	 *        Command line arguments.
	 * @throws IOException
	 *         Thrown if an error occurs with the network streams.
	 */
	public static void main(String[] args) throws IOException {
		Server server = new Server(SERVER_PORT);
		String message;
		while ((message = server.getInboundTraffic().readUTF()) != null) {
			System.out.println(getTime() + ": " + message);
			server.getOutboundTraffic().writeUTF(getTime() + " SERVER ECHOED: " + message);
		}
	}

	/**
	 * Listens for client connections, passes them off to the client connection socket.
	 */
	private ServerSocket clientListener;

	/**
	 * Direct connection to the client.
	 */
	private Socket clientConnection;

	/**
	 * Incoming network traffic.
	 */
	private DataInputStream inboundTraffic;

	/**
	 * Outgoing network traffic.
	 */
	private DataOutputStream outboundTraffic;

	/**
	 * Constructs a new instance of Server.
	 *
	 * @param port
	 *        The port this server is hosting on.
	 * @throws IOException
	 *         Thrown if there is an error setting up the network streams.
	 */
	public Server(int port) throws IOException {
		clientListener = new ServerSocket(port);
		clientConnection = clientListener.accept();
		inboundTraffic = new DataInputStream(clientConnection.getInputStream());
		outboundTraffic = new DataOutputStream(clientConnection.getOutputStream());
	}

	/**
	 * @return A reference to the client listener.
	 */
	public ServerSocket getClientListener() {
		return clientListener;
	}

	/**
	 * @return A reference to the client connection;
	 */
	public Socket getClientConnection() {
		return clientConnection;
	}

	/**
	 * @return A reference to the network input stream.
	 */
	public DataInputStream getInboundTraffic() {
		return inboundTraffic;
	}

	/**
	 * @return A reference to the network output stream.
	 */
	public DataOutputStream getOutboundTraffic() {
		return outboundTraffic;
	}

	/**
	 * @param clientListener
	 *        The client listener to set.
	 */
	public void setClientListener(ServerSocket clientListener) {
		this.clientListener = clientListener;
	}

	/**
	 * @param clientConnection
	 *        The client connection to set.
	 */
	public void setClientConnection(Socket clientConnection) {
		this.clientConnection = clientConnection;
	}

	/**
	 * @param inboundTraffic
	 *        The incoming network traffic stream to set.
	 */
	public void setInboundTraffic(DataInputStream inboundTraffic) {
		this.inboundTraffic = inboundTraffic;
	}

	/**
	 * @param outboundTraffic
	 *        The outgoing network traffic stream to set.
	 */
	public void setOutboundTraffic(DataOutputStream outboundTraffic) {
		this.outboundTraffic = outboundTraffic;
	}

}