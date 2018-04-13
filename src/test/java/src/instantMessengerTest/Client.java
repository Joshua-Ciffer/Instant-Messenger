package src.instantMessengerTest;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static src.instantMessengerTest.Constants.SERVER_PORT;

@SuppressWarnings("javadoc")
public final class Client {

	private Socket serverConnection;

	private PrintWriter outboundTraffic;

	private Scanner userInput;

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client("localhost", SERVER_PORT);
		while (true) {
			client.getOutboundTraffic().println("FUCK YEA");
		}
	}

	public Client(String ip, int port) throws UnknownHostException, IOException {
		serverConnection = new Socket(ip, port);
		outboundTraffic = new PrintWriter(serverConnection.getOutputStream(), true);
		outboundTraffic.flush();
		userInput = new Scanner(System.in);
	}

	public Socket getSocket() {
		return serverConnection;
	}

	public PrintWriter getOutboundTraffic() {
		return outboundTraffic;
	}

	public Scanner getUserInput() {
		return userInput;
	}

}