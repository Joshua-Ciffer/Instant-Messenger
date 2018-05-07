package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.instantMessenger.client.view.ClientFrame;

/**
 * Listens for an event that terminates the program after clean up and disconnecting from the server.
 *
 * @author Joshua Ciffer
 * @version 05/06/2018
 */
public class TerminateListener implements ActionListener {

	/**
	 * The controller associated with the current program.
	 */
	private ClientFrame parentFrame;

	/**
	 * Constructs a new <code>TerminateListener</code>.
	 *
	 * @param parentFrame
	 *        The parent frame associated with this listener.
	 */
	public TerminateListener(ClientFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	/**
	 * Terminates the program after clean up and disconnection.
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		parentFrame.getParentView().getController().terminate();
	}

}