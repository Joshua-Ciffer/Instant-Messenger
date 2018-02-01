package src.instantMessenger.client;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/01/2018
 */
public final class ClientGUI extends JFrame {

	public static void main(String[] args) {
		new ClientGUI();
	}

	private static final long serialVersionUID = 1L;

	private JMenu clientMenu;
	
	private JTextArea chatFeed;
	
	private JScrollPane chatFeedPane;

	private JTextField messageField;

	private JButton sendButton;

	ClientGUI() {
		super("Instant Messenger Client");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//chatFeedPane = new JScrollPane(chatFeed);
//		add(chatFeedPane);
		
		chatFeed = new JTextArea();
		chatFeed.setEditable(true);
		chatFeedPane = new JScrollPane(chatFeed);
		chatFeedPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatFeedPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatFeed.setLineWrap(true);
		add(chatFeedPane);
		setSize(500, 300);
	}
	
	public void sendMessage(String message) {
		Client.sendMessage(message);
	}


	JTextField getMessageField() {
		return messageField;
	}

	JScrollPane getchatFeedPane() {
		return chatFeedPane;
	}

	JButton getSendButton() {
		return sendButton;
	}

}