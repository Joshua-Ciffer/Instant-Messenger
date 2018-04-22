package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**
 * Listens for an event created by a cancel button on a dialog box.
 *
 * @author Joshua Ciffer
 * @version 04/21/2018
 */
public class CancelDialogListener extends JDialog implements ActionListener {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Closes the current dialog without saving any changes.
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		dispose();
	}

}