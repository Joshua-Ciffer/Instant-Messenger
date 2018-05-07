package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import src.instantMessenger.client.view.ClientFrame;

/**
 * Listens for an event that terminates the program after clean up and disconnecting from the server.
 *
 * @author Joshua Ciffer
 * @version 05/06/2018
 */
public class TerminateListener implements ActionListener, WindowListener {

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

	/**
	 * Terminates the program from closing the GUI window.
	 */
	@Override
	public void windowClosing(WindowEvent a) {
		parentFrame.getParentView().getController().terminate();
	}

	/**
	 * Terminates the program from closing the GUI window.
	 */
	@Override
	public void windowClosed(WindowEvent a) {
		parentFrame.getParentView().getController().terminate();
	}

	@Override
	public void windowActivated(WindowEvent a) {}

	@Override
	public void windowDeactivated(WindowEvent a) {}

	@Override
	public void windowDeiconified(WindowEvent a) {}

	@Override
	public void windowIconified(WindowEvent a) {}

	@Override
	public void windowOpened(WindowEvent a) {}

}