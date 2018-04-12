package src.instantMessenger;

import java.io.IOException;

import src.instantMessenger.server.Server;

/**
 * The main entry point class for the instant messenger server.s
 *
 * @author Joshua
 * @version 04/12/2018
 */
public class MainServer {

	/**
	 * The main entry point for the instant messenger server.
	 *
	 * @param args
	 *        Command line arguments.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		System.out.println(server.getClientConnection().getInetAddress());
		while (server.getClientConnection().isConnected()) {
			System.out.println(server.getInboundTraffic().readLine());
		}

	}

}