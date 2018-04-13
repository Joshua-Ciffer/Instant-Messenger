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

	/**
	 * 
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
	 * Constructs a new ChatFeedPanel container.
	 */
	ChatFeedPanel() {
		super();
		setLayout(null);
		chatFeedTextArea = new JTextArea();
		chatFeedTextArea.setEditable(false);
		chatFeedTextArea.setLineWrap(true);
		chatFeedTextArea.setWrapStyleWord(true);
		chatFeedTextArea.setFont(Constants.CHAT_FONT);
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
	 * @return The chat feed text area.
	 */
	JTextArea getChatFeedTextArea() {
		return chatFeedTextArea;
	}

}