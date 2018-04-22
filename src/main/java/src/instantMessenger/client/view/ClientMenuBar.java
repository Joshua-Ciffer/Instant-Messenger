package src.instantMessenger.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * 
 * 
 * @author Joshua Ciffer
 * @version 04/21/2018
 */
final class ClientMenuBar extends JMenuBar {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

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
	 * Edit submenu.
	 */
	private JMenu editMenu;

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
	 * Constructs a new client menu bar.
	 *
	 * @param parentFrame
	 *        The parent frame that this menu bar is placed on.
	 */
	ClientMenuBar(ClientFrame parentFrame) {
		super();
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
		fileMenu.add(changeUserNameItem);
		fileMenu.add(saveChatLogItem);
		editMenu = new JMenu("Edit");
		connectionMenu = new JMenu("Connection");
		connectToServerItem = new JMenuItem("Connect to Server...");
		connectToServerItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				new ConnectToServerDialog(parentFrame);
			}
		});
		disconnectItem = new JMenuItem("Disconnect");
		disconnectItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				parentFrame.getParentView().getController().getModel().disconnect();
			}
		});
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
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				parentFrame.getParentView().getController().getModel().disconnect();
			}
		});
		add(fileMenu);
		add(editMenu);
		add(connectionMenu);
		add(helpMenu);
		add(exitItem);
		setBounds(0, 0, 500, 20);
	}

}