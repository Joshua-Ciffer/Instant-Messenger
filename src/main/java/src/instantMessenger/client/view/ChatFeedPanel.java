package src.instantMessenger.client.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import static src.instantMessenger.util.Constants.CHAT_FONT;

/**
 * <code>ChatFeedPanel</code> is a panel that displays the chat feed and allows the user to scroll through the text area. The chat feed text area is not editable
 * and it auto-scrolls when new messages arrive.
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
	 * Constructs a new <code>ChatFeedPanel</code> panel.
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
	 * Appends a message to the chat feed.
	 * 
	 * @param message
	 *        The message to append.
	 */
	synchronized void appendToChatFeed(String message) {
		synchronized (chatFeedTextArea) {
			chatFeedTextArea.append(message);
		}
	}

	/**
	 * @return The text in the chat feed area.
	 */
	String getChatFeed() {
		return chatFeedTextArea.getText();
	}

	/**
	 * Clears the text in the chat feed.
	 */
	void clearChatFeed() {
		chatFeedTextArea.setText("");
	}

}