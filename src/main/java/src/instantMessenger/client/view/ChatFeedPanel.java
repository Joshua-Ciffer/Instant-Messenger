package src.instantMessenger.client.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import static src.instantMessenger.util.Constants.CHAT_FONT;

/**
 * <code>ChatFeedPanel</code> is a panel that displays the chat feed and allows the user to scroll through the text area.
 * 
 * @author Joshua Ciffer
 * @version 05/06/2018
 */
final class ChatFeedPanel extends JPanel {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The area that displays the chat history.
	 */
	private JTextArea chatFeedTextArea;

	/**
	 * Allows the chat feed text area to be scrollable.
	 */
	private JScrollPane chatFeedPane;

	/**
	 * Constructs a new <code>ChatFeedPanel</code> container.
	 */
	ChatFeedPanel() {
		super();
		setLayout(null);
		chatFeedTextArea = new JTextArea();
		chatFeedTextArea.setEditable(false);
		chatFeedTextArea.setLineWrap(true);
		chatFeedTextArea.setWrapStyleWord(true);
		chatFeedTextArea.setFont(CHAT_FONT);
		chatFeedPane = new JScrollPane(chatFeedTextArea);
		chatFeedPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatFeedPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatFeedPane.getVerticalScrollBar().setAutoscrolls(true);
		add(chatFeedPane);
		setSize(485, 195);
		chatFeedPane.setBounds(0, 0, 485, 195);
		setVisible(true);
	}

	/**
	 * @return The text in the chat feed area.
	 */
	String getChatFeedText() {
		return chatFeedTextArea.getText();
	}

	/**
	 * @param message
	 *        The message to append to the chat feed.
	 */
	void appendToChatFeed(String message) {
		chatFeedTextArea.append(message);
	}

}