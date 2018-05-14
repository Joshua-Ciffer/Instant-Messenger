package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**
 * Listens for an event created by a cancel button on a dialog box. When the cancel button is pressed, the listener disposes the current dialog without
 * saving any changes.
 *
 * @author Joshua Ciffer
 * @version 05/07/2018
 */
public final class CancelDialogListener implements ActionListener {

	/**
	 * The parent dialog that added this listener.
	 */
	private JDialog parentDialog;

	/**
	 * Constructs a new <code>CancelDialogListener</code>.
	 *
	 * @param parentDialog
	 *        The parent dialog that added this listener.
	 */
	public CancelDialogListener(JDialog parentDialog) {
		this.parentDialog = parentDialog;
	}

	/**
	 * Closes the current dialog without saving any changes upon a button press.
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		parentDialog.dispose();
	}

}