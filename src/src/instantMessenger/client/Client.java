package src.instantMessenger.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import src.instantMessenger.util.Constants;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/08/2018
 */
public final class Client {

	private Socket serverConnection;

	private ObjectInputStream incommingTraffic;

	private ObjectOutputStream outgoingTraffic;

	private InetAddress serverIP;
	
	private int serverPort;

	private String userName;

	Client() {
		serverPort = 2075;
		userName = "User" + (int)(Math.random() * 1_000);
		//connectToServer();

	}

	void sendMessage(String message) {
//		try {
//			outgoingTraffic.writeObject(userName + " " + Constants.getTime() + " - " + message + "\n");
//			outgoingTraffic.flush();
//		} catch (IOException e) {
//		.	e.printStackTrace();
//		}
	}

	void connectToServer() {
		try {
			serverConnection = new Socket(serverIP, serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		initializeNetworkStreams();
	}
	
	void terminateConnection() {
		
	}

	private void initializeNetworkStreams() {
		try {
			incommingTraffic = new ObjectInputStream(serverConnection.getInputStream());
			outgoingTraffic = new ObjectOutputStream(serverConnection.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		this.serverIP = InetAddress.getByName(serverIP);
	}
	
	void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

}