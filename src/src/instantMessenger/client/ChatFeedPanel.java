package src.instantMessenger.client;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import src.instantMessenger.util.Constants;

/**
 * ChatFeedPanel is a container for the functionality that displays the chat feed and allows the user to scroll through that text area.
 * 
 * @author Joshua Ciffer
 * @version 02/22/2018
 */
final class ChatFeedPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * The area that displays the chat history.
	 */
	private JTextArea chatFeed;
	
	/**
	 * Allows the chat feed text area to be scrollable.
	 */
	private JScrollPane chatFeedPane;
	
	/**
	 * Constructs a new ChatFeedPanel container.
	 */
	ChatFeedPanel() {
		super();
		setLayout(null);
		chatFeed = new JTextArea();
		chatFeed.setEditable(false);
		chatFeed.setLineWrap(true);
		chatFeed.setWrapStyleWord(true);
		chatFeed.setFont(Constants.CHAT_FONT);
		chatFeedPane = new JScrollPane(chatFeed);
		chatFeedPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatFeedPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(chatFeedPane);
		setSize(485, 195);
		chatFeedPane.setBounds(0, 0, 485, 195);
		setVisible(true);
	}
	
	/**
	 * Returns a reference to the chat feed text pane.
	 * 
	 * @return The chat feed text pane.
	 */
	JTextArea getChatFeed() {
		return chatFeed;
	}
	
}