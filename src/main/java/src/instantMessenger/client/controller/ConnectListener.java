package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import src.instantMessenger.client.view.ConnectToServerDialog;

/**
 * Listens for the connect button to be clicked on a <code>ConnectToServerDialog</code>.
 *
 * @author Joshua Ciffer
 * @version 05/06/2018
 */
public class ConnectListener extends JDialog implements ActionListener {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The dialog box that contains this listener.
	 */
	private ConnectToServerDialog parentDialog;

	/**
	 * Constructs a new <code>ConnectListener</code>.
	 *
	 * @param parentDialog
	 *        The dialog box that created this listener.
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
			parentDialog.getParentFrame().getParentView().getController().connect(parentDialog.getServerIP(), parentDialog.getServerPort());
			dispose();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(parentDialog, "The IP address you entered is invalid. Please enter a valid IP address.", "Invalid IP Address",
					JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(parentDialog, "The server port you entered is invalid. Please enter a port from the range 0-65535.", "Invalid Server Port",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(parentDialog, "The connection could not be established. Please try again.", "Connection Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

}