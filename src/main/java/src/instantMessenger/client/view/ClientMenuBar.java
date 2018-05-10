package src.instantMessenger.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import src.instantMessenger.client.controller.ClearChatFeedListener;
import src.instantMessenger.client.controller.DisconnectListener;
import src.instantMessenger.client.controller.TerminateListener;

/**
 * This class represents the menu bar that contains drop down menus and options for the client program.
 * 
 * @author Joshua Ciffer
 * @version 05/09/2018
 */
public final class ClientMenuBar extends JMenuBar {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The parent frame that this menu bar is placed on.
	 */
	private ClientFrame parentFrame;

	/**
	 * File submenu.
	 */
	private JMenu fileMenu;

	/**
	 * Opens a change user name dialog.
	 */
	private JMenuItem changeUserNameItem;

	/**
	 * Opens a save chat log dialog.
	 */
	private JMenuItem saveChatLogItem;

	/**
	 * Clears the text currently displayed in the chat feed pane.
	 */
	private JMenuItem clearChatFeedItem;

	/**
	 * Edit submenu.
	 */
	private JMenu editMenu;

	/**
	 * Reverts the last change the user typed in the message text field.
	 */
	private JMenuItem undoItem;

	/**
	 * Un-reverts the last change the user typed in the message text field.
	 */
	private JMenuItem redoItem;

	/**
	 * Cuts the selected text to the system clip board.
	 */
	private JMenuItem cutItem;

	/**
	 * Copies the selected text to the system clip board.
	 */
	private JMenuItem copyItem;

	/**
	 * Pastes the current text from the system clip board.
	 */
	private JMenuItem pasteItem;

	/**
	 * Clears the current text typed in the message text field.
	 */
	private JMenuItem deleteItem;
	// TODO: Write menu items for edit menu.

	/**
	 * Connection submenu.
	 */
	private JMenu connectionMenu;

	/**
	 * Opens a connect to server dialog.
	 */
	private JMenuItem connectToServerItem;

	/**
	 * Disconnects the client from the current server.
	 */
	private JMenuItem disconnectItem;

	/**
	 * Help submenu.
	 */
	private JMenu helpMenu;

	/**
	 * Opens a help dialog.
	 */
	private JMenuItem helpItem;

	/**
	 * Opens a about dialog.
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
		this.parentFrame = parentFrame;
		fileMenu = new JMenu("File");
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
		editMenu = new JMenu("Edit");
		undoItem = new JMenuItem("Undo");
		undoItem.addActionListener(null);
		redoItem = new JMenuItem("Redo");
		redoItem.addActionListener(null);
		cutItem = new JMenuItem("Cut");
		cutItem.addActionListener(null);
		copyItem = new JMenuItem("Copy");
		copyItem.addActionListener(null);
		pasteItem = new JMenuItem("Paste");
		pasteItem.addActionListener(null);
		deleteItem = new JMenuItem("Delete");
		deleteItem.addActionListener(null);
		editMenu.add(undoItem);
		editMenu.add(redoItem);
		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(deleteItem);
		connectionMenu = new JMenu("Connection");
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
		helpMenu = new JMenu("Help");
		helpItem = new JMenuItem("Help...");
		helpItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				new HelpDialog();
			}
		});
		aboutItem = new JMenuItem("About...");
		aboutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				new AboutDialog(parentFrame);
			}
		});
		helpMenu.add(helpItem);
		helpMenu.add(aboutItem);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new TerminateListener(parentFrame));
		add(fileMenu);
		add(editMenu);
		add(connectionMenu);
		add(helpMenu);
		add(exitItem);
		setBounds(0, 0, 500, 20);
	}

	/**
	 * @return A reference to this menu bar's parent frame.
	 */
	public ClientFrame getParentFrame() {
		return parentFrame;
	}

}