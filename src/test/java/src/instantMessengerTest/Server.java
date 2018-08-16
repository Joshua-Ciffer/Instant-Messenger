package src.instantMessengerTest;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

// import static src.instantMessenger.util.Constants.getTime;
import static src.instantMessengerTest.Constants.TEST_SERVER_PORT;

/**
 * Simple command line test server.
 *
 * @author Joshua Ciffer
 * @version 04/19/2018
 */
public final class Server {

	/**
	 * The main entry point of the server.
	 *
	 * @param args
	 *        Command line arguments.
	 * @throws IOException
	 *         Thrown if an error occurs with the network streams.
	 */
	public static void main(String[] args) throws IOException {
		Server server = new Server(TEST_SERVER_PORT);
		String incoming = "";
		try {
			String message = "";
			while ((message = server.getInboundTraffic().readUTF()) != null) {
				System.out.println(message);
				server.getOutboundTraffic().writeUTF("SERVER ECHOED: " + message);
				server.getOutboundTraffic().flush();
			}
		} catch (IOException e) {}
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
	 * Gathers user input from the console.
	 */
	private Scanner userInput;

	/**
	 * The port that this server is hosting on.
	 */
	private short serverPort;

	/**
	 * Constructs a new instance of Server.
	 *
	 * @param serverPort
	 *        The port this server is hosting on.
	 * @throws IOException
	 *         Thrown if there is an error setting up the network streams.
	 */
	public Server(short serverPort) throws IOException {
		this.serverPort = serverPort;
		clientListener = new ServerSocket(serverPort);
		clientConnection = clientListener.accept();
		System.out.println("connected");
		inboundTraffic = new DataInputStream(clientConnection.getInputStream());
		outboundTraffic = new DataOutputStream(clientConnection.getOutputStream());
		userInput = new Scanner(System.in);
	}

	/**
	 *
	 *
	 * @throws IOException
	 */
	public void waitForConnection() throws IOException {
		if ((clientConnection == null) || !(clientConnection.isConnected())) {
			clientConnection = clientListener.accept();
		}
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
	 * @return A reference to the user input scanner object.
	 */
	public Scanner getUserInput() {
		return userInput;
	}

	/**
	 * @return The port that this server is hosting on.
	 */
	public short getServerPort() {
		return serverPort;
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

	/**
	 * @param userInput
	 *        The user input scanner to set.
	 */
	public void setUserInput(Scanner userInput) {
		this.userInput = userInput;
	}

	/**
	 * @param serverPort
	 *        The server port to set.
	 */
	public void setServerPort(short serverPort) {
		this.serverPort = serverPort;
	}

}