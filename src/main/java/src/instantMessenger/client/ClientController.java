package src.instantMessenger.client;


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
	Client model;
	
	/**
	 * 
	 */
	ClientView view;
	
	/**
	 *
	 *
	 * @param model
	 */
	public ClientController(Client model, ClientView view) {
		this.model = model;
		this.view = view;
	}
	
}