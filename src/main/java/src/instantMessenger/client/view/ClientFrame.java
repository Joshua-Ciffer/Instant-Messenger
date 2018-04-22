package src.instantMessenger.client.view;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import src.instantMessenger.client.model.Client;
import src.instantMessenger.util.Constants;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/26/2018
 */
public final class ClientFrame extends JFrame {

	/**
	 *
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
	}

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	private ClientView view;
	
	private ClientMenuBar clientMenuBar;

	private ChatFeedPanel chatFeedPanel;

	private MessageFieldPanel messageFieldPanel;

	public ClientFrame(ClientView view) {
		super("Instant Messenger Client");
		this.view = view;
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
	}

	
	public void appendToScreen(String message) {
		chatFeedPanel.getChatFeedTextArea().append(message);
	}

	String getChatLog() {
		return chatFeedPanel.getChatFeedTextArea().getText();
	}
	
	String getMessage() {
		return messageFieldPanel.getMessageTextField().getText();
	}
	
	ClientView getView() {
		return view;
	}

}