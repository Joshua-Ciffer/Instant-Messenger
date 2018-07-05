package src.instantMessenger.client.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import src.instantMessenger.client.model.Client;
import src.instantMessenger.client.model.NetworkTrafficHandler;
import src.instantMessenger.client.view.ClientView;

import static src.instantMessenger.util.Constants.getTime;
import static src.instantMessenger.util.Constants.disconnectedFromServerMessage;
import static src.instantMessenger.util.Constants.connectedToServerMessage;
import static src.instantMessenger.util.Constants.MESSAGE_NOT_SENT_MESSAGE;
import static src.instantMessenger.util.Constants.ALREADY_DISCONNECTED_MESSAGE;
import static src.instantMessenger.util.Constants.changedUserNameMessage;

/**
 * The controller acts as a bridge between the view and model. Essentially, the model serves as the client object and the view displays the information from
 * that object. Requests from the view to update data in the model pass through the controller, and information to be displayed from the model passes from
 * the controller to the view.
 *
 * @author Joshua Ciffer
 * @version 07/04/2018
 */
public final class ClientController {

	/**
	 * The client model associated with this controller and view.
	 */
	private Client model;

	/**
	 * The client view associated with this controller and model.
	 */
	private ClientView view;

	/**
	 * Handler thread that processes incoming network traffic using the model.
	 */
	private NetworkTrafficHandler trafficHandler; // TODO: Move to model?

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
		this.view.setController(this);	// The view needs a reference to this controller so it can make requests for information from the model.
		trafficHandler = new NetworkTrafficHandler(this);
	}

	/**
	 * Starts running all client threads and prepares the program to launch.
	 */
	public void init() {
		view.getClientFrame().setVisible(true);
		trafficHandler.start();
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
		if (isConnected()) {
			disconnect();
		}
		appendToChatFeed(connectedToServerMessage(serverIP, serverPort));
		model.connect(serverIP, serverPort);
	}

	/**
	 * Disconnects the client from the current server.
	 */
	public void disconnect() {
		if (isConnected()) {
			appendToChatFeed(disconnectedFromServerMessage(getServerIP(), getServerPort()));
			model.disconnect();
		} else {
			appendToChatFeed(ALREADY_DISCONNECTED_MESSAGE);
		}
	}

	/**
	 * @return True if the client is connected to a server, false if not connected.
	 */
	public boolean isConnected() {
		return model.isConnected();
	}

	/**
	 * Writes a text based message to the network streams which is then sent to the server.
	 *
	 * @param message
	 *        The message to be sent.
	 * @throws IOException
	 *         Thrown if there was an error writing to the network streams.
	 */
	public void sendMessage(String message) throws IOException {
		if (isConnected()) {
			message = getTime() + " - " + getUserName() + ": " + message + "\n";
			model.sendMessage(message);
			appendToChatFeed(message);
		} else {
			appendToChatFeed(MESSAGE_NOT_SENT_MESSAGE);
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
		if (isConnected()) {
			return model.readMessage();
		} else {
			return null;
		}
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
	 * Clears the text in the chat feed panel of the view.
	 */
	public void clearChatFeed() {
		view.clearChatFeed();
	}

	/**
	 * Terminates the program after disconnected from the current server and stopping all network threads.
	 */
	@SuppressWarnings("deprecation")
	public void terminate() {	// TODO: Figure out how to safely stop threads.
		disconnect();
		trafficHandler.stop();
		int exitCode = 0;
		System.out.println("Program terminated with exit code: " + exitCode);
		System.exit(exitCode);
	}

	/**
	 * Updates the user name saved in the client model.
	 * 
	 * @param userName
	 *        The user name to set.
	 */
	public void changeUserName(String userName) {
		appendToChatFeed(changedUserNameMessage(userName));
		model.setUserName(userName);
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