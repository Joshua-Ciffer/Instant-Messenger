package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.instantMessenger.client.view.ClientFrame;

/**
 * Listens for an action event that clears the text in the chat feed. This listener is added to the "Clear Chat Feed" button on the file sub-menu of the menu
 * bar.
 *
 * @author Joshua Ciffer
 * @version 05/09/2018
 */
public final class ClearChatFeedListener implements ActionListener {

	/**
	 * The parent component that added this listener.
	 */
	private ClientFrame parentFrame;

	/**
	 * Constructs a new <code>ClearChatFeedListener</code>.
	 *
	 * @param parentFrame
	 *        The parent component that added this listener.
	 */
	public ClearChatFeedListener(ClientFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	/**
	 * Clears the text in the chat feed.
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		parentFrame.getController().clearChatFeed();
	}

}