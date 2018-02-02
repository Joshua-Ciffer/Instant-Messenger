package src.instantMessenger.client;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

	private final Client client = new Client();
	
	private JMenuBar clientMenuBar;

	private JMenu fileMenu;
	
	private JMenu editMenu;
	
	private JMenu connectionMenu;
	
	private JMenuItem exitItem;
	
	private JMenuItem saveLogButton;
	
	private JTextArea chatFeed;

	private JScrollPane chatFeedPane;

	private JTextArea messageField;

	private JScrollPane messageFieldPane;

	private JButton sendButton;
	

	ClientGUI() {
		super("Instant Messenger Client");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		clientMenuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		
		connectionMenu = new JMenu("Connection");
		
		saveLogButton = new JMenuItem("Save Chat Log");
		
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.exit(0);
			}
		});
		clientMenuBar.add(fileMenu);
		clientMenuBar.add(connectionMenu);
		clientMenuBar.add(exitItem);
		fileMenu.add(saveLogButton);
		
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
		
	}

	public void sendMessage() {
		client.sendMessage(messageField.getText());
		chatFeed.append(messageField.getText() + "\n");
		messageField.setText(null);
	}
	
}