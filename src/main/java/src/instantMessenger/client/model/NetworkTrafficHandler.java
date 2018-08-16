package src.instantMessenger.client.model;

import java.io.IOException;
import java.net.SocketException;

import src.instantMessenger.client.controller.ClientController;

import static src.instantMessenger.util.Constants.serverConnectionLostMessage;

/**
 * Handles incoming network traffic from the server.
 *
 * @author Joshua Ciffer
 * @version 08/16/2018
 */
public final class NetworkTrafficHandler extends Thread {

	/**
	 * The controller associated with the current client.
	 */
	private ClientController controller;

	/**
	 * Constructs a new instance of <code>NetworkTrafficHandler</code>.
	 *
	 * @param controller
	 *        The controller associated with the current client.
	 */
	public NetworkTrafficHandler(ClientController controller) {
		this.controller = controller;
	}

	/**
	 * Listens for messages sent from the server and appends them to the chat feed.
	 */
	@Override
	public void run() {
		String message = "";
		try {
			while (true) {
				System.out.print("");	// TODO: For some god unknown reason to me, without this line of code messages are not read from the socket.
				if ((message = controller.readMessage()) != null) {
					controller.appendToChatFeed(message);
				}
			}
		} catch (SocketException e) {
			controller.disconnect();
			controller.appendToChatFeed(serverConnectionLostMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}