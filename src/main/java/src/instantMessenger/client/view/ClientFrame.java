package src.instantMessenger.client.view;

import javax.swing.JFrame;

import src.instantMessenger.client.controller.TerminateListener;

/**
 * The main GUI frame class that all other components are added to.
 * 
 * @author Joshua Ciffer
 * @version 05/06/2018
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
	 * The menu bar associated with this frame.
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
	 * @return The parent view.
	 */
	public ClientView getParentView() {
		return parentView;
	}

	/**
	 * @return The text contained in the chat feed panel.
	 */
	public String getChatFeedText() {
		return chatFeedPanel.getChatFeedText();
	}

}