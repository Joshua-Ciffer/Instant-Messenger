package src.instantMessenger.client;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import src.instantMessenger.util.Constants;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/01/2018
 */
public final class ClientGUI extends JFrame {

	public static void main(String[] args) {
		ClientGUI x = new ClientGUI();
	}

	private static final long serialVersionUID = 1L;

	private JMenu clientMenu;

	private JTextArea chatFeed;

	private JScrollPane chatFeedPane;

	private JTextArea messageField;

	private JScrollPane messageFieldPane;

	private JButton sendButton;
	
	private KeyListener enterKeyListener;

	ClientGUI() {
		super("Instant Messenger Client");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setLayout(null);

		chatFeed = new JTextArea();
		chatFeed.setEditable(true);
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
			public void actionPerformed(ActionEvent a) {
				sendMessage();
			}
		});
		
		enterKeyListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent k) {
				if (k.getKeyCode() == Constants.ENTER_KEY) {
					sendMessage();
				}
			}

			@Override
			public void keyReleased(KeyEvent k) {}

			@Override
			public void keyTyped(KeyEvent k) {}
		};
		
		addKeyListener(enterKeyListener);
		chatFeed.addKeyListener(enterKeyListener);
		messageField.addKeyListener(enterKeyListener);
		sendButton.addKeyListener(enterKeyListener);
		
		add(chatFeedPane);
		add(messageFieldPane);
		add(sendButton);
		setSize(500, 300);
		chatFeedPane.setBounds(0, 0, 485, 200);
		messageFieldPane.setBounds(0, 200, 410, 65);
		sendButton.setBounds(410, 200, 75, 64);

	}

	public void sendMessage() {
		Client.sendMessage(messageField.getText());
		System.out.println(messageField.getText());
		chatFeed.append(messageField.getText() + "\n");
		messageField.setText("");
	}

	JMenu getCientMenu() {
		return clientMenu;
	}

	JTextArea getChatFeed() {
		return chatFeed;
	}

	JScrollPane getChatFeedPane() {
		return chatFeedPane;
	}

	JTextArea getMessageField() {
		return messageField;
	}

	JButton getSendButton() {
		return sendButton;
	}
}