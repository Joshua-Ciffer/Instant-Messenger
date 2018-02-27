package src.instantMessenger.client;
import java.net.Socket;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import src.instantMessenger.util.Constants;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/26/2018
 */
public final class Client {

	private Socket serverConnection;

	private ObjectInputStream incommingTraffic;

	private ObjectOutputStream outgoingTraffic;

	private Inet4Address serverIP;

	private short serverPort;

	private String userName;

	Client() {
		userName = "User" + (int)(Math.random() * 1_000);
		try {
			serverIP = (Inet4Address)Inet4Address.getByName("192.168.1.1");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serverPort = 60;
	}

	void sendMessage(String message) {
		try {
			outgoingTraffic.writeObject(userName + " " + Constants.getTime() + " - " + message + "\n");
			outgoingTraffic.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void connect() throws IOException {
		serverConnection = new Socket(serverIP, serverPort);
		incommingTraffic = new ObjectInputStream(serverConnection.getInputStream());
		outgoingTraffic = new ObjectOutputStream(serverConnection.getOutputStream());
	}

	void disconnect() {
		try {
			if (serverConnection != null) {
				serverConnection.close();
				serverConnection = null;
			} else if (incommingTraffic != null) {
				incommingTraffic.close();
				incommingTraffic = null;
			} else if (outgoingTraffic != null) {
				outgoingTraffic.close();
				outgoingTraffic = null;
			}
		} catch (IOException e) {}
	}

	String getServerIP() {
		return serverIP.getHostAddress();
	}

	int getServerPort() {
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