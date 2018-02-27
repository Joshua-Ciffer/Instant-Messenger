package src.instantMessenger.client;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import src.instantMessenger.util.Constants;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/26/2018
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
		setVisible(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	void sendMessage(String message) {
		if (!message.equalsIgnoreCase("")) {
			message = client.getUserName() + " " + Constants.getTime() + " - " + message + "\n";
			client.sendMessage(message);
			chatFeedPanel.getChatFeedTextArea().append(message);
			messageFieldPanel.getMessageTextField().setText(null);
		}
	}

	void disconnect() {
		client.disconnect();
	}

	void terminate() {
		disconnect();
		System.exit(0);
	}

	String getChatLog() {
		return chatFeedPanel.getChatFeedTextArea().getText();
	}
	
	String getMessage() {
		return messageFieldPanel.getMessageTextField().getText();
	}

	Client getClient() {
		return client;
	}

}