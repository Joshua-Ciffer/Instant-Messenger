package src.instantMessenger.client;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.instantMessenger.util.Constants;

/**
 * MessageFieldPanel is a container for the functionality that allows the user to type and send messages.
 * 
 * @author Joshua Ciffer
 * @version 02/22/2018
 */
final class MessageFieldPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Allows the user to type their message.
	 */
	private JTextArea messageTextField;
	
	/**
	 * Allows the message field text field to be scrollable.
	 */
	private JScrollPane messageFieldPane;
	
	/**
	 * Sends the message that is entered in the message text field.
	 */
	private JButton sendButton;
	
	/**
	 * Constructs a new MessageFieldPanel container.
	 * 
	 * @param parentFrame - The parent frame that this container is placed on.
	 */
	MessageFieldPanel(ClientFrame parentFrame) {
		super();
		setLayout(null);
		messageTextField = new JTextArea();
		messageTextField.setEditable(true);
		messageTextField.setLineWrap(true);
		messageTextField.setWrapStyleWord(true);
		messageTextField.setFont(Constants.CHAT_FONT);
		messageFieldPane = new JScrollPane(messageTextField);
		messageFieldPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		messageFieldPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				parentFrame.sendMessage(messageTextField.getText());
			}
		});
		add(messageFieldPane);
		add(sendButton);
		setSize(500, 100);
		messageFieldPane.setBounds(0, 0, 410, 50);
		sendButton.setBounds(410, 0, 75, 49);
		setVisible(true);
	}
	
	/**
	 * Returns a reference to the message field text pane.
	 * 
	 * @return The message field text pane.
	 */
	JTextArea getMessageTextField() {
		return messageTextField;
	}
	
}