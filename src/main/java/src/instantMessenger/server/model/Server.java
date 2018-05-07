package src.instantMessenger.server.model;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static src.instantMessenger.util.Constants.getTime;

/**
 * This class represents a single chat server that has connections to multiple clients.
 *
 * @author Joshua Ciffer
 * @version 04/13/2018
 */
@SuppressWarnings("unused")
public final class Server {

	/**
	 * 
	 */
	private ServerSocket clientListener;

	/**
	 * 
	 */
	private Socket clientConnection;

	/**
	 * Incoming network traffic.
	 */
	private ObjectInputStream inboundTraffic;

	/**
	 * Outgoing network traffic.
	 */
	private ObjectOutputStream outboundTraffic;

	/**
	 * This server's port.
	 */
	private short serverPort;

	/**
	 * The name of this server.
	 */
	private String serverName;

	/**
	 *
	 *
	 */
	public Server() {
		serverName = "Server" + (int)(Math.random() * 1_000);
	}

	/**
	 * Broadcasts a message to everyone on the server.
	 *
	 * @param message
	 *        The message to broadcast.
	 * @throws IOException 
	 */
	void broadcastMessage(String message) throws IOException {
		outboundTraffic.writeUTF("SERVER " + serverName + " " + getTime() + ": " + message);
	}

	/**
	 * @return The server port.
	 */
	short getServerPort() {
		return serverPort;
	}

	/**
	 * @return The server name.
	 */
	String getServerName() {
		return serverName;
	}

	/**
	 * @param serverPort
	 *        The server port to set.
	 */
	void setServerPort(short serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @param serverName
	 *        The server name to set.
	 */
	void setServerName(String serverName) {
		this.serverName = serverName;
	}

}