package src.instantMessenger.client;
import javax.swing.JFrame;
import src.instantMessenger.util.Constants;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/08/2018
 */
final class ClientFrame extends JFrame {

	public static void main(String[] args) {
		ClientFrame x = new ClientFrame();
	}

	private static final long serialVersionUID = 1L;

	private Client client;

	private ClientMenuBar clientMenuBar;

	private ChatFeedPanel chatFeedPanel;
	
	private MessageFieldPanel messageFieldPanel;
	
	ClientFrame() {
		super("Instant Messenger Client");
		client = new Client();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		clientMenuBar = new ClientMenuBar(this);
		chatFeedPanel = new ChatFeedPanel();
		messageFieldPanel = new MessageFieldPanel(this);

		add(clientMenuBar);
		add(chatFeedPanel);
		add(messageFieldPanel);
		setSize(500, 300);
		clientMenuBar.setBounds(0, 0, 500, 20);
		chatFeedPanel.setBounds(0, 20, 485, 195);
		messageFieldPanel.setBounds(0, 215, 500, 100);
//		messageFieldPane.setBounds(0, 215, 410, 50);
//		sendButton.setBounds(410, 215, 75, 49);
		setVisible(true);
	}

	void sendMessage(String message) {
		if (!(messageFieldPanel.getMessageTextField().getText().equals(""))) {
			String message1 = client.getUserName() + " " + Constants.getTime() + " - " + messageFieldPanel.getMessageTextField().getText() + "\n";
			client.sendMessage(message);
			chatFeedPanel.getChatFeedTextArea().append(message);
			messageFieldPanel.getMessageTextField().setText(null);
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
		return chatFeedPanel.getChatFeedTextArea().getText();
	}
	
	Client getClient() {
		return client;
	}
	
}