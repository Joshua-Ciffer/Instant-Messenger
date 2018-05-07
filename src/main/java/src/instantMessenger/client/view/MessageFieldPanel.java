package src.instantMessenger.client.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import src.instantMessenger.client.controller.SendMessageListener;

import static src.instantMessenger.util.Constants.CHAT_FONT;

/**
 * <code>MessageFieldPanel</code> is a panel that contains functionality that allows the user to type and send messages.
 * 
 * @author Joshua Ciffer
 * @version 05/07/2018
 */
public final class MessageFieldPanel extends JPanel {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The parent frame that this panel is added to.
	 */
	private ClientFrame parentFrame;

	/**
	 * Allows the user to type their message.
	 */
	private JTextArea messageTextField;

	/**
	 * Allows the message text field to be scrollable.
	 */
	private JScrollPane messageFieldPane;

	/**
	 * Sends the message that is entered in the message text field.
	 */
	private JButton sendButton;

	/**
	 * Constructs a new <code>MessageFieldPanel</code> container.
	 * 
	 * @param parentFrame
	 *        The parent frame that this panel is placed on.
	 */
	MessageFieldPanel(ClientFrame parentFrame) {
		super();
		this.parentFrame = parentFrame;
		setLayout(null);
		messageTextField = new JTextArea();
		messageTextField.setEditable(true);
		messageTextField.setLineWrap(true);
		messageTextField.setWrapStyleWord(true);
		messageTextField.setFont(CHAT_FONT);
		messageFieldPane = new JScrollPane(messageTextField);
		messageFieldPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		messageFieldPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sendButton = new JButton("Send");
		sendButton.addActionListener(new SendMessageListener(this));
		add(messageFieldPane);
		add(sendButton);
		setSize(500, 100);
		messageFieldPane.setBounds(0, 0, 410, 50);
		sendButton.setBounds(410, 0, 75, 49);
		setVisible(true);
	}

	/**
	 * @return The parent frame that this panel is placed on.
	 */
	public ClientFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * @return The message entered in the message text field.
	 */
	public String getMessageFieldText() {
		return messageTextField.getText();
	}

}