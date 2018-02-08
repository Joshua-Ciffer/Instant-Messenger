package src.instantMessenger.client;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.instantMessenger.util.Constants;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/08/2018
 */
final class MessageFieldPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextArea messageField;
	
	private JScrollPane messageFieldPane;
	
	private JButton sendButton;
	
	MessageFieldPanel(ClientFrame parentFrame) {
		super();
		setLayout(null);
		messageField = new JTextArea();
		messageField.setEditable(true);
		messageField.setLineWrap(true);
		messageField.setWrapStyleWord(true);
		messageField.setFont(Constants.CHAT_FONT);
		messageFieldPane = new JScrollPane(messageField);
		messageFieldPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		messageFieldPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				parentFrame.sendMessage();
			}
		});
		add(messageFieldPane);
		add(sendButton);
		setSize(500, 100);
		messageFieldPane.setBounds(0, 0, 410, 50);
		sendButton.setBounds(410, 95, 75, 49);
		setVisible(true);
	}
	
	JTextArea getMessageField() {
		return messageField;
	}
	
}