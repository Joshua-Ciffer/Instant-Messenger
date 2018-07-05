package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import src.instantMessenger.client.view.ClientFrame;

/**
 * Listens for an event that terminates the program after clean up and disconnecting from the server. This is listener is added to both the exit button on the
 * menu bar and the native close button in the upper corner of the window. When the action is performed, the client is disconnected from the server, all threads
 * are stopped and the program terminates.
 *
 * @author Joshua Ciffer
 * @version 05/08/2018
 */
public final class TerminateListener implements ActionListener, WindowListener {

	/**
	 * The parent frame that added this listener.
	 */
	private ClientFrame parentFrame;

	/**
	 * Constructs a new <code>TerminateListener</code>.
	 *
	 * @param parentFrame
	 *        The parent frame that added this listener.
	 */
	public TerminateListener(ClientFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	/**
	 * If the client is connected to a server, they are disconnected. All threads are stopped and the program terminates.
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		parentFrame.getController().terminate();
	}

	/**
	 * If the client is connected to a server, they are disconnected. All threads are stopped and the program terminates.
	 */
	@Override
	public void windowClosing(WindowEvent a) {
		parentFrame.getController().terminate();
	}

	/**
	 * If the client is connected to a server, they are disconnected. All threads are stopped and the program terminates.
	 */
	@Override
	public void windowClosed(WindowEvent a) {
		parentFrame.getController().terminate();
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