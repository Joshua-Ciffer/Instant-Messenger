package src.instantMessenger.client.view;

import src.instantMessenger.client.controller.ClientController;

/**
 * This class represents the visual aspect of the program that the user sees and interacts with. The view consists of the client frame, which is an instance of
 * <code>JFrame</code>. The client frame is broken down into the chat feed panel, message field panel, menu bar, and its necessary dialogs. The chat feed panel
 * contains a scrollable text area that displays the chat history. The message field panel contains a text field for the user to type their message, and a send
 * button to send their message. The menu bar contains sub-menus for connections options and additional features, like saving the chat log, changing the user
 * name, etc.
 *
 * @author Joshua Ciffer
 * @version 04/21/2018
 */
public final class ClientView {

	/**
	 * The main GUI frame. This frame contains the major sub-panels and menu bar.
	 */
	private ClientFrame clientFrame;

	/**
	 * The controller that bridges this view with the client model.
	 */
	private ClientController controller;

	/**
	 * Constructs a new <code>ClientView</code>.
	 */
	public ClientView() {
		clientFrame = new ClientFrame(this);
	}

	/**
	 * @param message
	 *        The message to append to the chat feed.
	 */
	public synchronized void appendToChatFeed(String message) {
		clientFrame.getChatFeedPanel().appendToChatFeed(message);
	}

	/**
	 * Clears the text in the chat feed.
	 */
	public void clearChatFeed() {
		clientFrame.getChatFeedPanel().clearChatFeed();
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