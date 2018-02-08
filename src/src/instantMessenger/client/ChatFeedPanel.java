package src.instantMessenger.client;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import src.instantMessenger.util.Constants;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/08/2018
 */
final class ChatFeedPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextArea chatFeed;
	
	private JScrollPane chatFeedPane;
	
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
	
	JTextArea getChatFeed() {
		return chatFeed;
	}
	
}