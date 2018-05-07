package src.instantMessenger.client.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import src.instantMessenger.client.model.Client;
import src.instantMessenger.client.view.ClientView;

/**
 * 
 *
 * @author Joshua Ciffer
 * @version 04/12/2018
 */
public class ClientController {

	/**
	 * 
	 */
	private Client model;

	/**
	 * 
	 */
	private ClientView view;

	/**
	 *
	 *
	 * @param model
	 * @param view 
	 */
	public ClientController(Client model, ClientView view) {
		this.model = model;
		this.view = view;
		this.view.setController(this);
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
	 *
	 *
	 * @param serverIP
	 * @param serverPort
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void connect(String serverIP, short serverPort) throws UnknownHostException, IOException {
		model.connect(serverIP, serverPort);
	}
	
	/**
	 *
	 *
	 * @return
	 */
	public String getServerIP() {
		return model.getServerIP();
	}
	
	/**
	 *
	 *
	 * @return
	 */
	public short getServerPort() {
		return model.getServerPort();
	}

	/**
	 *
	 *
	 * @return
	 */
	public String getUserName() {
		return model.getUserName();
	}
	
	/**
	 *
	 *
	 */
	public void disconnect() {
		model.disconnect();
	}

	/**
	 *
	 *
	 */
	public void terminate() {
		disconnect();
	}
	
	/**
	 *
	 *
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		model.sendMessage(message);
	}
	
}