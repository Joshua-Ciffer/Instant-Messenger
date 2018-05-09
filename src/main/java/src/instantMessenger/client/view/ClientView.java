package src.instantMessenger.client.view;

import src.instantMessenger.client.controller.ClientController;

/**
 * This class represents the visual aspect of the program that the user sees and interacts with.
 *
 * @author Joshua Ciffer
 * @version 04/21/2018
 */
public final class ClientView {

	/**
	 * The main GUI frame.
	 */
	private ClientFrame clientFrame;

	/**
	 * The controller that bridges this view class with the client model.
	 */
	private ClientController controller;

	/**
	 * Constructs a new instance of <code>ClientView</code>.
	 */
	public ClientView() {
		clientFrame = new ClientFrame(this);
	}

	/**
	 * @param message
	 *        The message to append to the chat feed.
	 */
	public void appendToChatFeed(String message) {
		clientFrame.getChatFeedPanel().appendToChatFeed(message);
	}
	
	/**
	 * @return The main GUI view frame.
	 */
	public ClientFrame getClientFrame() {
		return clientFrame;
	}

	/**
	 * @return The controller associated with this view.
	 */
	public ClientController getController() {
		return controller;
	}

	/**
	 * @param controller
	 *        The controller to be set.
	 */
	public void setController(ClientController controller) {
		this.controller = controller;
	}

}