package src.instantMessenger.client.controller;

import src.instantMessenger.client.model.Client;
import src.instantMessenger.client.view.ClientView;
import src.instantMessenger.util.Constants;

import static src.instantMessenger.util.Constants.LISTENER_THREAD;

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
	 */
	public ClientController(Client model, ClientView view) {
		this.model = model;
		this.view = view;
		Constants.LISTENER_THREAD.setController(this);
	}
	
	Client getModel() {
		return model;
	}
	
	ClientView getView() {
		return view;
	}
	
	
}