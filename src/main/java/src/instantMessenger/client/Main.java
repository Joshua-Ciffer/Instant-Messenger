package src.instantMessenger.client;

import src.instantMessenger.client.model.Client;
import src.instantMessenger.client.controller.ClientController;
import src.instantMessenger.client.view.ClientView;

/**
 * The main client entry point.
 *
 * @author Joshua Ciffer
 * @version 05/08/2018
 */
public final class Main {

	/**
	 * Don't let anyone instantiate this class.
	 */
	private Main() {}

	/**
	 * The model represents a single chat client with its state and behavior.
	 */
	private static Client model;

	/**
	 * The view is the GUI that the user sees and interacts with.
	 */
	private static ClientView view;

	/**
	 * The controller bridges the model and the view and allows information to be updated and requested.
	 */
	private static ClientController controller;

	/**
	 * The main client entry point.
	 *
	 * @param args
	 *        Command line arguments.
	 */
	public static void main(String[] args) {
		model = new Client();
		view = new ClientView();
		controller = new ClientController(model, view);
		controller.init();
	}

}