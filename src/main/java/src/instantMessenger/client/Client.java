package src.instantMessenger.client;

import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

import static src.instantMessenger.util.Constants.generateUserName;
import static src.instantMessenger.util.Constants.getTime;

/**
 * This class represents a single chat client that has a connection to the server.
 * 
 * @author Joshua Ciffer
 * @version 04/12/2018
 */
public final class Client {

	/**
	 * The TCP connection to the chat messenger server.
	 */
	private Socket serverConnection;

	/**
	 * Incoming network traffic.
	 */
	private BufferedReader inboundTraffic;

	/**
	 * Outgoing network traffic.
	 */
	private PrintWriter outboundTraffic;

	/**
	 * The server's IP address.
	 */
	private InetAddress serverIP;

	/**
	 * The server's port.
	 */
	private short serverPort;

	/**
	 * The current user's name.
	 */
	private String userName;

	/**
	 * Constructs a new client.
	 */
	public Client() {
		userName = generateUserName();
	}

	/**
	 * Sends a text based message over the network through the server connection.
	 *
	 * @param message
	 *        The message to send.
	 */
	void sendMessage(String message) {
		outboundTraffic.println(userName + " " + getTime() + " - " + message);
		outboundTraffic.flush();
	}

	/**
	 * Initializes the connection to the server and sets up network streams.
	 *
	 * @throws IOException
	 *         Thrown if a network or IO error occurs.
	 */
	void connect() throws IOException {
		serverConnection = new Socket(serverIP, serverPort);
		inboundTraffic = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
		outboundTraffic = new PrintWriter(serverConnection.getOutputStream());
	}

	/**
	 * Terminates the server connection and closes network streams.
	 */
	void disconnect() {
		try {
			if (serverConnection != null) {
				serverConnection.close();
				serverConnection = null;
			}
			if (inboundTraffic != null) {
				inboundTraffic.close();
				inboundTraffic = null;
			}
			if (outboundTraffic != null) {
				outboundTraffic.close();
				outboundTraffic = null;
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
	 * @return The server IP.
	 */
	String getServerIP() {
		return serverIP.getHostAddress();
	}

	/**
	 * @return The server port.
	 */
	short getServerPort() {
		return serverPort;
	}

	/**
	 * @return The user name.
	 */
	String getUserName() {
		return userName;
	}

	/**
	 * @param serverIP
	 *        The server IP to set.
	 * @throws UnknownHostException
	 *         Thrown if the IP is invalid.
	 */
	void setServerIP(String serverIP) throws UnknownHostException {
		this.serverIP = InetAddress.getByName(serverIP);
	}

	/**
	 * @param serverPort
	 *        The server port to set.
	 */
	void setServerPort(short serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @param userName
	 *        The user name to set.
	 */
	void setUserName(String userName) {
		this.userName = userName;
	}

}