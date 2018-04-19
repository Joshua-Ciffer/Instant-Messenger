package src.instantMessenger.client.model;

import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static java.lang.Math.random;

import static src.instantMessenger.util.Constants.getTime;

/**
 * This class represents a single chat client that has a connection to the server.
 * 
 * @author Joshua Ciffer
 * @version 04/19/2018
 */
public final class Client {

	/**
	 * The TCP connection to the chat messenger server.
	 */
	private Socket serverConnection;

	/**
	 * Incoming network traffic.
	 */
	private DataInputStream inboundTraffic;

	/**
	 * Outgoing network traffic.
	 */
	private DataOutputStream outboundTraffic;

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
		userName = "User" + (int)(random() * 1_000);
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
		outboundTraffic.writeUTF(userName + " " + getTime() + ": " + message);
	}

	/**
	 * Initializes the connection to the server and sets up network streams.
	 *
	 * @throws IOException
	 *         Thrown if a network or IO error occurs.
	 */
	public void connect() throws IOException {
		serverConnection = new Socket(serverIP, serverPort);
		inboundTraffic = new DataInputStream(serverConnection.getInputStream());
		outboundTraffic = new DataOutputStream(serverConnection.getOutputStream());
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
	 * @return A reference to the server connection.
	 */
	public Socket getServerConnection() {
		return serverConnection;
	}

	/**
	 * @return A reference to the inbound traffic stream.
	 */
	public DataInputStream getInboundTraffic() {
		return inboundTraffic;
	}

	/**
	 * @return A reference to the outbound traffic stream.
	 */
	DataOutputStream getOutboundTraffic() {
		return outboundTraffic;
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