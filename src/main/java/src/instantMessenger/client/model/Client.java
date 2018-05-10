package src.instantMessenger.client.model;

import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * This class represents a single chat client that has a connection to the server.
 * 
 * @author Joshua Ciffer
 * @version 04/21/2018
 */
public final class Client {

	/**
	 * The TCP connection to the chat messenger server.
	 */
	private Socket serverConnection;

	/**
	 * Incoming network traffic.
	 */
	private DataInputStream networkInput;

	/**
	 * Outgoing network traffic.
	 */
	private DataOutputStream networkOutput;

	/**
	 * The server's IP address.
	 */
	private InetAddress serverIP;

	/**
	 * The server's port.
	 */
	private short serverPort;

	/**
	 * The current client's name.
	 */
	private String userName;

	/**
	 * Constructs a new instance of <code>Client</code>. The socket is left unconnected at the time of construction, and a random user name is generated.
	 */
	public Client() {
		serverConnection = new Socket();
		userName = "User" + (int)((Math.random() * 1_000) + 100);	// Generates random user name. Example: User(*random 3 digit number*)
	}

	/**
	 * Sends a text based message over the network through the server connection.
	 *
	 * @param message
	 *        The message to send.
	 * @throws IOException
	 *         Thrown if the network stream can't be written to.
	 */
	public void sendMessage(String message) throws IOException {
		if (isConnected() && (networkOutput != null)) {
			networkOutput.writeUTF(message);
		}
	}

	/**
	 * Reads a text based message over the network sent from the server.
	 *
	 * @return The message read from the server. Null, if no message.
	 * @throws IOException
	 *         Thrown if there was an error reading from the network stream.
	 */
	public String readMessage() throws IOException {
		if (isConnected() && (networkInput != null)) {
			return networkInput.readUTF();
		} else {
			return null;
		}
	}

	/**
	 * Initializes the connection to the server and sets up network streams.
	 * 
	 * @param serverIP
	 *        The server's IP address.
	 * @param serverPort
	 *        The server's port.
	 * @throws UnknownHostException
	 *         Thrown if the IP address is invalid.
	 * @throws IOException
	 *         Thrown if the connection to the server could not be established.
	 */
	public void connect(String serverIP, short serverPort) throws UnknownHostException, IOException {
		disconnect();
		setServerIP(serverIP);
		setServerPort(serverPort);
		serverConnection = new Socket(serverIP, serverPort);
		networkInput = new DataInputStream(serverConnection.getInputStream());
		networkOutput = new DataOutputStream(serverConnection.getOutputStream());
	}

	/**
	 * Terminates the server connection and closes network streams.
	 */
	public void disconnect() {
		try {
			if (serverConnection != null) {
				serverConnection.close();
				serverConnection = null;
			}
			if (networkInput != null) {
				networkInput.close();
				networkInput = null;
			}
			if (networkOutput != null) {
				networkOutput.close();
				networkOutput = null;
			}
			if (serverIP != null) {
				serverIP = null;
			}
			if (serverPort != 0) {
				serverPort = 0;
			}
		} catch (IOException e) {}
	}

	/**
	 * @return True if the client is connected to the server, false if disconnected.
	 */
	public boolean isConnected() {
		return (serverConnection == null) ? false : serverConnection.isConnected();
	}

	/**
	 * @return The server IP.
	 */
	public String getServerIP() {
		return serverIP.getHostAddress();
	}

	/**
	 * @return The server port.
	 */
	public short getServerPort() {
		return serverPort;
	}

	/**
	 * @return The user name.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param serverIP
	 *        The server IP to set.
	 * @throws UnknownHostException
	 *         Thrown if the IP is invalid.
	 */
	public void setServerIP(String serverIP) throws UnknownHostException {
		this.serverIP = InetAddress.getByName(serverIP);
	}

	/**
	 * @param serverPort
	 *        The server port to set.
	 */
	public void setServerPort(short serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @param userName
	 *        The user name to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}