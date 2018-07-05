package src.instantMessenger.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import src.instantMessenger.client.controller.ClearChatFeedListener;
import src.instantMessenger.client.controller.DisconnectListener;
import src.instantMessenger.client.controller.TerminateListener;

/**
 * This class represents the menu bar that contains drop down menus and options for the client program. The file sub-menu contains a change user name, save
 * chat log, and clear chat feed buttons. The change user name button displays a <code>ChangeUserNameDialog</code> where the user can enter a new user name.
 * The save chat log button displays a new <code>SaveChatLogDialog</code> where the user specifies a file to save their chat log. The clear chat feed button
 * clears the chat feed. The connection sub-menu contains a connect to server, and disconnect button. The connect to server button displays a new
 * <code>ConnectToServerDialog</code> where the user enters an IP and port of a server to connect to. The disconnect button terminates the connection with
 * the current server. The about button displays a <code>AboutDialog</code>. The exit button gracefully terminates the program.
 * 
 * @author Joshua Ciffer
 * @version 07/04/2018
 */
public final class ClientMenuBar extends JMenuBar {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * File submenu. Contains the change user name, save chat log, and clear chat feed buttons.
	 */
	private JMenu fileMenu;

	/**
	 * Displays a <code>ChangeUserNameDialog</code>.
	 */
	private JMenuItem changeUserNameItem;

	/**
	 * Displays a <code>SaveChatLogDialog</code>.
	 */
	private JMenuItem saveChatLogItem;

	/**
	 * Clears the text currently displayed in the chat feed pane.
	 */
	private JMenuItem clearChatFeedItem;

	/**
	 * Connection submenu. Contains the connect to server button and disconnect buttons.
	 */
	private JMenu connectionMenu;

	/**
	 * Displays a <code>ConnectToServerDialog</code>.
	 */
	private JMenuItem connectToServerItem;

	/**
	 * Disconnects the client from the current server.
	 */
	private JMenuItem disconnectItem;

	/**
	 * Displays a <code>AboutDialog</code>.
	 */
	private JMenuItem aboutItem;

	/**
	 * Terminates the program.
	 */
	private JMenuItem exitItem;

	/**
	 * Constructs a new <code>ClientMenuBar</code>.
	 *
	 * @param parentFrame
	 *        The parent frame that this menu bar is placed on.
	 */
	ClientMenuBar(ClientFrame parentFrame) {
		super();
		setLayout(null);
		fileMenu = new JMenu("File");
		fileMenu.setHorizontalAlignment(SwingConstants.LEFT);
		changeUserNameItem = new JMenuItem("Change User Name...");
		changeUserNameItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				new ChangeUserNameDialog(parentFrame);
			}
		});
		saveChatLogItem = new JMenuItem("Save Chat Log...");
		saveChatLogItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				new SaveChatLogDialog(parentFrame);
			}
		});
		clearChatFeedItem = new JMenuItem("Clear Chat Feed");
		clearChatFeedItem.addActionListener(new ClearChatFeedListener(parentFrame));
		fileMenu.add(changeUserNameItem);
		fileMenu.add(saveChatLogItem);
		fileMenu.add(clearChatFeedItem);
		connectionMenu = new JMenu("Connection");
		connectionMenu.setHorizontalAlignment(SwingConstants.LEFT);
		connectToServerItem = new JMenuItem("Connect to Server...");
		connectToServerItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				new ConnectToServerDialog(parentFrame);
			}
		});
		disconnectItem = new JMenuItem("Disconnect");
		disconnectItem.addActionListener(new DisconnectListener(parentFrame));
		connectionMenu.add(connectToServerItem);
		connectionMenu.add(disconnectItem);
		aboutItem = new JMenuItem("About");
		aboutItem.setHorizontalAlignment(SwingConstants.LEFT);
		aboutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				new AboutDialog(parentFrame);
			}
		});
		exitItem = new JMenuItem("Exit");
		exitItem.setHorizontalAlignment(SwingConstants.LEFT);
		exitItem.addActionListener(new TerminateListener(parentFrame));
		add(fileMenu);
		add(connectionMenu);
		add(aboutItem);
		add(exitItem);
		fileMenu.setBounds(0, 0, 35, 20);
		connectionMenu.setBounds(35, 0, 80, 20);
		aboutItem.setBounds(115, 0, 45, 20);
		exitItem.setBounds(160, 0, 35, 20);
		setBounds(0, 0, 500, 20);
	}

}