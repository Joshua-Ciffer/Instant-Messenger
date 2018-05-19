package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.instantMessenger.client.view.ChangeUserNameDialog;

import static src.instantMessenger.util.Constants.changedUserNameMessage;;

/**
 * This listener waits for an event created by the save button on a <code>ChangeUserNameDialog</code> window. When the save button is pressed, the user name
 * entered in the text field of the dialog is passed to the controller to then be saved in the model.
 *
 * @author Joshua Ciffer
 * @version 05/06/2018
 */
public final class ChangeUserNameListener implements ActionListener {

	/**
	 * The <code>ChangeUserNameDialog</code> object that added this listener.
	 */
	private ChangeUserNameDialog parentDialog;

	/**
	 * Constructs a new <code>ChangeUserNameListener</code>.
	 *
	 * @param parentDialog
	 *        The <code>ChangeUserNameDialog</code> object that added this listener.
	 */
	public ChangeUserNameListener(ChangeUserNameDialog parentDialog) {
		this.parentDialog = parentDialog;
	}

	/**
	 * Updates the user name stored in the client model with the user name entered in the dialog box.
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		parentDialog.getParentFrame().getController().changeUserName(parentDialog.getUserName());
		parentDialog.getParentFrame().getController().appendToChatFeed(changedUserNameMessage(parentDialog.getUserName()));
		parentDialog.dispose();
	}

}