package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.instantMessenger.client.view.ChangeUserNameDialog;

/**
 * Listens for an event created by the save button on a <code>ChangeUserNameDialog</code> window.
 *
 * @author Joshua Ciffer
 * @version 05/06/2018
 */
public final class ChangeUserNameListener implements ActionListener {

	/**
	 * The <code>ChangeUserNameDialog</code> object that contains this listener.
	 */
	private ChangeUserNameDialog parentDialog;

	/**
	 * Constructs a new instance of <code>ChangeUserNameListener</code>.
	 *
	 * @param parentDialog
	 *        The <code>ChangeUserNameDialog</code> object that contains this listener.
	 */
	public ChangeUserNameListener(ChangeUserNameDialog parentDialog) {
		this.parentDialog = parentDialog;
	}

	/**
	 * Updates the user name stored in the client model with the user name entered in the dialog box.
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		parentDialog.getParentFrame().getParentView().getController().changeUserName(parentDialog.getUserName());
		parentDialog.dispose();
	}

}