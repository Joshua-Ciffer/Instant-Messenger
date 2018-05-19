package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import src.instantMessenger.client.view.ConnectToServerDialog;

import static src.instantMessenger.util.Constants.connectedToServerMessage;

/**
 * Listens for the connect button to be clicked on a <code>ConnectToServerDialog</code>. When the connect button is clicked, the IP address and port entered
 * in the dialog box are passed to the controller. They are then saved in the client model, and a socket is created and bound to that address. A connection
 * is then attempted to be established.
 *
 * @author Joshua Ciffer
 * @version 05/08/2018
 */
public final class ConnectListener implements ActionListener {

	/**
	 * The dialog box that added this listener.
	 */
	private ConnectToServerDialog parentDialog;

	/**
	 * Constructs a new <code>ConnectListener</code>.
	 *
	 * @param parentDialog
	 *        The dialog box that added this listener.
	 */
	public ConnectListener(ConnectToServerDialog parentDialog) {
		this.parentDialog = parentDialog;
	}

	/**
	 * Connects the current client to the server using the IP address and port provided in the <code>ConnectToServerDialog</code>.
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		try {
			parentDialog.getParentFrame().getController().connect(parentDialog.getServerIP(), parentDialog.getServerPort());
			parentDialog.getParentFrame().getController().appendToChatFeed(connectedToServerMessage(parentDialog.getServerIP(), parentDialog.getServerPort()));
			parentDialog.dispose();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(parentDialog, "The IP address you entered is invalid. Please enter a valid IP address.", "Invalid IP Address",
					JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(parentDialog, "The server port you entered is invalid. Please enter a port from the range 0-65535.", "Invalid Server Port",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {	// TODO: Add catch blocks for more specific network exceptions.
			JOptionPane.showMessageDialog(parentDialog, "The connection could not be established. Please try again.", "Connection Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

}