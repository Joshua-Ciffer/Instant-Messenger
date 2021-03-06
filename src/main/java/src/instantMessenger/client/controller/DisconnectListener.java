package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.instantMessenger.client.view.ClientFrame;

/**
 * Listens for an event created by a disconnect button to disconnect the client from the current server. This listener is added to the disconnect button on the
 * connection sub-menu of the menu bar.
 *
 * @author Joshua Ciffer
 * @version 05/13/2018
 */
public final class DisconnectListener implements ActionListener {

	/**
	 * The parent component that has added this listener.
	 */
	private ClientFrame parentFrame;

	/**
	 * Constructs a new <code>DisconnectListener</code>.
	 *
	 * @param parentFrame
	 *        The parent component that has added this listener.
	 */
	public DisconnectListener(ClientFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	/**
	 * Disconnects the client from the current server.
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		parentFrame.getController().disconnect();
	}

}