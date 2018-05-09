package src.instantMessenger.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import src.instantMessenger.client.view.MessageFieldPanel;

/**
 * Listens for an event to send a message to the server.
 *
 * @author Joshua Ciffer
 * @version 05/07/2018
 */
public final class SendMessageListener implements ActionListener {

	/**
	 * The component that this listener is added to.
	 */
	private MessageFieldPanel parentComponent;

	/**
	 * Constructs a new <code>SendMessageListener</code>.
	 *
	 * @param parentComponent
	 *        The component that this listener is added to.
	 */
	public SendMessageListener(MessageFieldPanel parentComponent) {
		this.parentComponent = parentComponent;
	}

	/**
	 * Sends the message entered in the message text field to the server.
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		try {
			String message = parentComponent.getMessageFieldText();
			if ((message != null) && (message.length() >= 1)) {
				parentComponent.getParentFrame().getParentView().getController().sendMessage(message);
				parentComponent.clearMessageTextField();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}