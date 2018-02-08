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
public class Client {

	private Socket serverConnection;

	private ObjectInputStream incommingTraffic;

	private ObjectOutputStream outgoingTraffic;

	private String serverIP;
	
	private int serverPort;

	private String userName = "User" + (int)(Math.random() * 1_000);

	Client() {
		serverIP = "192.168.1.1";
		serverPort = 2075;
		//connectToServer();

	}

	void sendMessage(String message) {
//		try {
//			outgoingTraffic.writeObject(userName + " " + Constants.getTime() + " - " + message + "\n");
//			outgoingTraffic.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	void connectToServer() {
		try {
			serverConnection = new Socket(InetAddress.getByName(serverIP), serverPort);
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
		return serverIP;
	}

	int getServerPort() {
		return serverPort;
	}

	String getUserName() {
		return userName;
	}

	void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	void setUserName(String userName) {
		this.userName = userName;
	}

}