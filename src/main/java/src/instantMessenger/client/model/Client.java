package src.instantMessenger.client.model;

import java.net.Socket;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.IOException;

import src.instantMessenger.util.Constants;

/**
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
	private ObjectInputStream inboundTraffic;

	/**
	 * Outgoing network traffic.
	 */
	private PrintWriter outboundTraffic;

	private Inet4Address serverIP;

	private short serverPort;

	private String userName;

	Client() throws IOException {
		userName = "User" + (int)(Math.random() * 1_000);
		try {
			serverIP = (Inet4Address)Inet4Address.getByName("192.168.1.1");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		serverPort = 123;
		serverConnection = new Socket(serverIP, serverPort);
		inboundTraffic = new ObjectInputStream(serverConnection.getInputStream());
		outboundTraffic = new PrintWriter(serverConnection.getOutputStream());
	}

	void sendMessage(String message) {
		outboundTraffic.println(userName + " " + Constants.getTime() + " - " + message + "\n");
		outboundTraffic.flush();
	}

	void connect() throws IOException {

	}

	void disconnect() {
		try {
			if (serverConnection != null) {
				serverConnection.close();
				serverConnection = null;
			} else if (inboundTraffic != null) {
				inboundTraffic.close();
				inboundTraffic = null;
			} else if (outboundTraffic != null) {
				outboundTraffic.close();
				outboundTraffic = null;
			}
		} catch (IOException e) {}
	}

	String getServerIP() {
		return serverIP.getHostAddress();
	}

	short getServerPort() {
		return serverPort;
	}

	String getUserName() {
		return userName;
	}

	void setServerIP(String serverIP) throws UnknownHostException {
		this.serverIP = (Inet4Address)Inet4Address.getByName(serverIP);
	}

	void setServerPort(short serverPort) {
		this.serverPort = serverPort;
	}

	void setUserName(String userName) {
		this.userName = userName;
	}

}