package src.instantMessenger.client.view;

import javax.swing.JFrame;

import src.instantMessenger.client.controller.TerminateListener;

/**
 * The main GUI frame class that all other components are added to. The components of the interface are grouped into panels which are placed upon this frame.
 * The chat feed panel contains the scrollable text area for the chat feed, and the message field panel contains the text feed for sending message and the
 * send button. The menu bar contains the sub-menus and menu buttons for opening different dialog boxes and such.
 * 
 * @author Joshua Ciffer
 * @version 05/13/2018
 */
public final class ClientFrame extends JFrame {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The view object that contains this frame object.
	 */
	private ClientView parentView;

	/**
	 * The menu bar placed on this frame.
	 */
	private ClientMenuBar clientMenuBar;

	/**
	 * Panel that contains text areas for the chat feed.
	 */
	private ChatFeedPanel chatFeedPanel;

	/**
	 * Panel that contains the text field for sending messages.
	 */
	private MessageFieldPanel messageFieldPanel;

	/**
	 * Constructs a new <code>ClientFrame</code>.
	 *
	 * @param parentView
	 *        The view object that contains this frame.
	 */
	public ClientFrame(ClientView parentView) {
		super("Instant Messenger Client");
		this.parentView = parentView;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		clientMenuBar = new ClientMenuBar(this);
		chatFeedPanel = new ChatFeedPanel();
		messageFieldPanel = new MessageFieldPanel(this);
		addWindowListener(new TerminateListener(this));
		add(clientMenuBar);
		add(chatFeedPanel);
		add(messageFieldPanel);
		setSize(500, 300);
		clientMenuBar.setBounds(0, 0, 500, 20);
		chatFeedPanel.setBounds(0, 20, 485, 195);
		messageFieldPanel.setBounds(0, 215, 500, 100);
		setVisible(true);
	}

	/**
	 * @return The parent view that contains this frame.
	 */
	public ClientView getParentView() {
		return parentView;
	}

	/**
	 * Appends a message to the chat feed.
	 *
	 * @param message
	 *        The message to append.
	 */
	void appendToChatFeed(String message) {
		chatFeedPanel.appendToChatFeed(message);
	}

	/**
	 * @return The text contained in the chat feed.
	 */
	String getChatFeed() {
		return chatFeedPanel.getChatFeedText();
	}

	/**
	 * Clears the text in the chat feed text area.
	 */
	void clearChatFeed() {
		chatFeedPanel.clearChatFeed();
	}

}