package src.instantMessenger.client;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.instantMessenger.util.Constants;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/06/2018
 */
public final class ClientGUI extends JFrame {

	public static void main(String[] args) {
		ClientGUI x = new ClientGUI();
	}

	private static final long serialVersionUID = 1L;

	private final Client client;

	private ClientMenuBar clientMenuBar;

	private JTextArea chatFeed;

	private JScrollPane chatFeedPane;

	private JTextArea messageField;

	private JScrollPane messageFieldPane;

	private JButton sendButton;

	ClientGUI() {
		super("Instant Messenger Client");
		client = new Client();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		clientMenuBar = new ClientMenuBar(this);
		
		chatFeed = new JTextArea();
		chatFeed.setEditable(false);
		chatFeed.setLineWrap(true);
		chatFeed.setWrapStyleWord(true);
		chatFeed.setFont(Constants.CHAT_FONT);

		chatFeedPane = new JScrollPane(chatFeed);
		chatFeedPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatFeedPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

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
				sendMessage();
			}
		});

		add(clientMenuBar);
		add(chatFeedPane);
		add(messageFieldPane);
		add(sendButton);
		setSize(500, 300);
		clientMenuBar.setBounds(0, 0, 500, 20);
		chatFeedPane.setBounds(0, 20, 485, 195);
		messageFieldPane.setBounds(0, 215, 410, 50);
		sendButton.setBounds(410, 215, 75, 49);
		setVisible(true);
	}

	private void sendMessage() {
		if (!(messageField.getText().equals(""))) {
			String message = client.getUserName() + " " + Constants.getTime() + " - " + messageField.getText() + "\n";
			client.sendMessage(message);
			chatFeed.append(message);
			messageField.setText(null);
		}
	}

	void disconnect() {
		client.terminateConnection();
	}

	void terminate() {
		disconnect();
		System.exit(0);
	}

	String getChatLog() {
		return chatFeed.getText();
	}

}