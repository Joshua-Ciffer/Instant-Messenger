package src.instantMessengerTest;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static src.instantMessengerTest.Constants.SERVER_PORT;

@SuppressWarnings("javadoc")
public final class Client {

	private Socket serverConnection;

	private DataOutputStream outboundTraffic;

	private Scanner userInput;

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client("localhost", SERVER_PORT);
		int i = 0;
		while (true) {
			client.getOutboundTraffic().writeUTF("Message #" + i++);
		}
	}

	public Client(String ip, int port) throws UnknownHostException, IOException {
		serverConnection = new Socket(ip, port);
		outboundTraffic = new DataOutputStream(serverConnection.getOutputStream());
		outboundTraffic.flush();
		userInput = new Scanner(System.in);
	}

	public Socket getSocket() {
		return serverConnection;
	}

	public DataOutputStream getOutboundTraffic() {
		return outboundTraffic;
	}

	public Scanner getUserInput() {
		return userInput;
	}

}