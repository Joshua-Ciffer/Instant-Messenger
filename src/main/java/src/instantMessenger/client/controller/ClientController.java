package src.instantMessenger.client.controller;

import src.instantMessenger.client.model.Client;
import src.instantMessenger.client.view.ClientView;
import src.instantMessenger.util.Constants;

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

	public Client getModel() {
		return model;
	}

	public ClientView getView() {
		return view;
	}
	
	public String getUserName() {
		return model.getUserName();
	}
	
	public void disconnect() {
		model.disconnect();
	}

	public void terminate() {
		disconnect();
	}
	
}