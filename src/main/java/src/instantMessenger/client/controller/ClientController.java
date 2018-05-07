package src.instantMessenger.client.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import src.instantMessenger.client.model.Client;
import src.instantMessenger.client.view.ClientView;

/**
 * The controller acts as a bridge between the view and model. Essentially, the model servers as the client object and the view displays the information from that object.
 * Requests from the view to update data in the model pass through the controller, and information to be displayed from the model passes from the controller to the view.
 *
 * @author Joshua Ciffer
 * @version 05/06/2018
 */
public class ClientController {

	/**
	 * The client model associated with this controller and the view.
	 */
	private Client model;

	/**
	 * The view associated with this controller and model.
	 */
	private ClientView view;

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
		model.disconnect();
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
		model.sendMessage(message);
	}

	/**
	 * Terminates the program.
	 */
	public void terminate() {
		disconnect();
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