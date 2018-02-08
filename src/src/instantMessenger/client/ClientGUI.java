package src.instantMessenger.client;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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

//	private JMenuBar clientMenuBar;
	private ClientMenuBar clientMenuBar = new ClientMenuBar(this);

	private JMenu fileMenu;

	private JMenu editMenu;

	private JMenu connectionMenu;

	private JMenuItem exitItem;

	private JMenuItem saveChatLogButton;

	private JTextArea chatFeed;

	private JScrollPane chatFeedPane;

	private JTextArea messageField;

	private JScrollPane messageFieldPane;

	private JButton sendButton;
	
	private JFileChooser saveLogDialog;

	ClientGUI() {
		super("Instant Messenger Client");
		client = new Client();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		saveLogDialog = new JFileChooser();
		
//		clientMenuBar = new JMenuBar();
//
//		fileMenu = new JMenu("File");
//
//		connectionMenu = new JMenu("Connection");
//
//		saveChatLogButton = new JMenuItem("Save Chat Log");
//		saveChatLogButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
//
//		exitItem = new JMenuItem("Exit");
//
//		exitItem.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent a) {
//				System.exit(0);
//			}
//		});
//
//		clientMenuBar.add(fileMenu);
//		clientMenuBar.add(connectionMenu);
//		clientMenuBar.add(exitItem);
//		fileMenu.add(saveChatLogButton);

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
		client.sendMessage(messageField.getText());
		chatFeed.append(client.getUserName() + " " + Constants.getTime() + " - " + messageField.getText() + "\n");
		messageField.setText(null);
	}
	
	String getChatLog() {
		return chatFeed.getText();
	}
	
	void terminate() {
		client.terminateConnection();
		System.exit(0);
	}

	void disconnect() {
		client.terminateConnection();
	}
	
}
