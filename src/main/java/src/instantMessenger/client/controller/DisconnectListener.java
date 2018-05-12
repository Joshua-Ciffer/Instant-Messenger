package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.instantMessenger.client.view.ClientFrame;

import static src.instantMessenger.util.Constants.serverDisconnectedMessage;
import static src.instantMessenger.util.Constants.ALREADY_DISCONNECTED;

/**
 * Listens for an event created by a disconnect button to disconnect the client from the current server.
 *
 * @author Joshua Ciffer
 * @version 05/06/2018
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
		if (parentFrame.getParentView().getController().isConnected()) {
			parentFrame.getParentView().getController().appendToChatFeed(
					serverDisconnectedMessage(parentFrame.getParentView().getController().getServerIP(), parentFrame.getParentView().getController().getServerPort()));
			parentFrame.getParentView().getController().disconnect();
		} else {
			parentFrame.getParentView().getController().appendToChatFeed(ALREADY_DISCONNECTED);
		}
	}

}