package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * Listens for an event created by the save button on a <code>ChangeUserNameDialog</code> window.
 *
 * @author Joshua Ciffer
 * @version 04/21/2018
 */
public class ChangeUserNameListener implements ActionListener {

	/**
	 * The controller associated with the current view.
	 */
	private ClientController controller;

	/**
	 * Constructs a new instance of <code>ChangeUserNameListener</code>. This listener needs a reference to the controller object to make user name changes to the client
	 * model.
	 *
	 * @param controller
	 *        The controller associated with this view.
	 */
	public ChangeUserNameListener(ClientController controller) {
		this.controller = controller;
	}

	/**
	 * Updates the user name stored in the client model associated with this view.
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		controller.changeUserName(((JTextField)a.getSource()).getText());
	}

}