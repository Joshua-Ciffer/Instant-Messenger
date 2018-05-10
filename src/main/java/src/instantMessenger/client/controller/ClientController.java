package src.instantMessenger.client.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import src.instantMessenger.client.model.Client;
import src.instantMessenger.client.model.NetworkTrafficHandler;
import src.instantMessenger.client.view.ClientView;

import static src.instantMessenger.util.Constants.getTime;
import static src.instantMessenger.util.Constants.SERVER_DISCONNECTED;
import static src.instantMessenger.util.Constants.MESSAGE_NOT_SENT;
import static src.instantMessenger.util.Constants.ALREADY_DISCONNECTED;

/**
 * The controller acts as a bridge between the view and model. Essentially, the model serves as the client object and the view displays the information from that object.
 * Requests from the view to update data in the model pass through the controller, and information to be displayed from the model passes from the controller to the view.
 *
 * @author Joshua Ciffer
 * @version 05/06/2018
 */
public final class ClientController {

	/**
	 * The client model associated with this controller and the view.
	 */
	private Client model;

	/**
	 * The view associated with this controller and the model.
	 */
	private ClientView view;

	/**
	 * Handler thread that processes incoming network traffic.
	 */
	private NetworkTrafficHandler trafficHandler;

	/**
	 * Constructs a new <code>ClientController</code>.
	 *
	 * @param model
	 *        The client model object.
	 * @param view
	 *        The GUI view object.
	 */
	public ClientController(Client model, ClientView view) {
		this.model = model;
		this.view = view;
		this.view.setController(this);	// The view needs a reference to the controller so it can make requests for information from the model.
		trafficHandler = new NetworkTrafficHandler(this);
	}

	/**
	 * Starts running all client threads and prepares program to launch.
	 */
	public void init() {
		view.getClientFrame().setVisible(true);
		trafficHandler.run();
	}

	/**
	 * Connects the client to the server with the IP address and port specified.
	 *
	 * @param serverIP
	 *        The server's IP address.
	 * @param serverPort
	 *        The server's port.
	 * @throws UnknownHostException
	 *         Thrown if the IP address is invalid.
	 * @throws IOException
	 *         Thrown if there was an error connecting to the server.
	 */
	public void connect(String serverIP, short serverPort) throws UnknownHostException, IOException {
		model.connect(serverIP, serverPort);
	}

	/**
	 * Disconnects the client from the current server.
	 */
	public void disconnect() {
		if (model.isConnected()) {
			model.disconnect();
			view.appendToChatFeed(SERVER_DISCONNECTED);
		} else {
			view.appendToChatFeed(ALREADY_DISCONNECTED);
		}
	}

	/**
	 * Sends a message to the server.
	 *
	 * @param message
	 *        The message to be sent.
	 * @throws IOException
	 *         Thrown if there was an error writing to the network streams.
	 */
	public void sendMessage(String message) throws IOException {
		message = getTime() + " - " + getUserName() + ": " + message + "\n";
		if (model.isConnected()) {
			model.sendMessage(message);
			view.appendToChatFeed(message);
		} else {
			view.appendToChatFeed(MESSAGE_NOT_SENT);
		}
	}

	/**
	 * Reads a text based message over the network sent from the server.
	 *
	 * @return The message read from the server. Null, if no message.
	 * @throws IOException
	 *         Thrown if there was an error reading from the network stream.
	 */
	public String readMessage() throws IOException {
		if (model.isConnected()) {
			return model.readMessage();
		} else {
			return null;
		}
	}

	/**
	 * Terminates the program.
	 */
	public void terminate() {
		disconnect();
		int exitCode = 0;
		System.out.println("Program terminated with exit code: " + exitCode);
		System.exit(exitCode);
	}

	/**
	 * Appends a message to the chat feed.
	 *
	 * @param message
	 *        The message to append.
	 */
	public synchronized void appendToChatFeed(String message) {
		view.appendToChatFeed(message);
	}

	/**
	 * Updates the user name saved in the client model.
	 * 
	 * @param userName
	 *        The user name to set.
	 */
	public void changeUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * @return True if the client is connected to a server, false if not connected.
	 */
	public boolean isConnected() {
		return model.isConnected();
	}

	/**
	 * @return The client's user name.
	 */
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * @return The IP address of the current server.
	 */
	public String getServerIP() {
		return model.getServerIP();
	}

	/**
	 * @return The port of the current server.
	 */
	public short getServerPort() {
		return model.getServerPort();
	}

}