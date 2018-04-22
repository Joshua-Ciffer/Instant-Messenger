package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * 
 *
 * @author Joshua Ciffer
 * @version 04/21/2018
 */
public class ChangeUserNameListener implements ActionListener {

	private ClientController controller;

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