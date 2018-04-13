package src.instantMessenger.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/22/2018
 */
@SuppressWarnings("javadoc")
final class ClientMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private JMenu fileMenu;

	private JMenuItem changeUserNameItem;

	private JMenuItem saveChatLogItem;

	private JMenu editMenu;

	private JMenu connectionMenu;

	private JMenuItem connectToServerItem;

	private JMenuItem disconnectItem;

	private JMenuItem exitItem;

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
				parentFrame.disconnect();
			}
		});
		connectionMenu.add(connectToServerItem);
		connectionMenu.add(disconnectItem);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				parentFrame.terminate();
			}
		});
		add(fileMenu);
		add(editMenu);
		add(connectionMenu);
		add(exitItem);
		setBounds(0, 0, 500, 20);
	}

}