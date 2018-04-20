package src.instantMessengerTest;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static src.instantMessenger.util.Constants.getTime;
import static src.instantMessengerTest.Constants.TEST_SERVER_IP;
import static src.instantMessengerTest.Constants.TEST_SERVER_PORT;

/**
 * Simple command line test client.
 *
 * @author Joshua Ciffer
 * @version 04/19/2018
 */
public final class Client {

	/**
	 * The main entry point of the client.
	 *
	 * @param args
	 *        Command line arguments.
	 * @throws IOException
	 *         Thrown if there is an error setting up the network streams.
	 * @throws UnknownHostException
	 *         Thrown if the server IP address is invalid.
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client(TEST_SERVER_IP, TEST_SERVER_PORT);
		Thread i = new Thread() {

			public void run() {
				String incoming;
				try {
					while ((incoming = client.getInboundTraffic().readUTF()) != null) {
						synchronized (System.out) {
							System.out.flush();
							System.out.println(incoming);
							System.out.flush();
						}
					}
				} catch (IOException e) {}
			}

		};
		Thread o = new Thread() {

			public void run() {
				try {
					String outgoing;
					while (true) {
						synchronized (System.out) {
							System.out.flush();
							System.out.print("Enter message: ");
							outgoing = getTime() + " CLIENT: " + client.getUserInput().nextLine();
							client.getOutboundTraffic().writeUTF(outgoing);
							System.out.println(outgoing);
							System.out.flush();
							client.getOutboundTraffic().writeUTF(outgoing);
						}
					}
				} catch (IOException e) {}
			}

		};
		i.start();
		o.start();
	}

	/**
	 * The connection to the server.
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
	 * Gathers user input from the console.
	 */
	private Scanner userInput;

	/**
	 * The server's IP address.
	 */
	private String serverIP;

	/**
	 * The server's port.
	 */
	private short serverPort;

	/**
	 * Constructs a new instance of Client.
	 *
	 * @param serverIP
	 *        The server's IP address.
	 * @param serverPort
	 *        The server's port.
	 * @throws IOException
	 *         Thrown if there is an error setting up the network streams.
	 * @throws UnknownHostException
	 *         Thrown if the server IP address is invalid.
	 */
	public Client(String serverIP, short serverPort) throws UnknownHostException, IOException {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		serverConnection = new Socket(serverIP, serverPort);
		inboundTraffic = new DataInputStream(serverConnection.getInputStream());
		outboundTraffic = new DataOutputStream(serverConnection.getOutputStream());
		userInput = new Scanner(System.in);
	}

	/**
	 * @return A reference to the server connection.
	 */
	public Socket getServerConnection() {
		return serverConnection;
	}

	/**
	 * @return A reference to the incoming network stream.
	 */
	public DataInputStream getInboundTraffic() {
		return inboundTraffic;
	}

	/**
	 * @return A reference to the outgoing network stream.
	 */
	public DataOutputStream getOutboundTraffic() {
		return outboundTraffic;
	}

	/**
	 * @return A reference to the user input scanner.
	 */
	public Scanner getUserInput() {
		return userInput;
	}

	/**
	 * @return The server's IP address.
	 */
	public String getServerIP() {
		return serverIP;
	}

	/**
	 * @return The server's port.
	 */
	public short getServerPort() {
		return serverPort;
	}

	/**
	 * @param serverConnection
	 *        The server connection to set.
	 */
	public void setServerConnection(Socket serverConnection) {
		this.serverConnection = serverConnection;
	}

	/**
	 * @param inboundTraffic
	 *        The incoming network stream to set.
	 */
	public void setInboundTraffic(DataInputStream inboundTraffic) {
		this.inboundTraffic = inboundTraffic;
	}

	/**
	 * @param outboundTraffic
	 *        The outgoing network stream to set.
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
	 * @param serverIP
	 *        The server IP address to set.
	 */
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	/**
	 * @param serverPort
	 *        The server port to set.
	 */
	public void setServerPort(short serverPort) {
		this.serverPort = serverPort;
	}

}