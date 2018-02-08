package src.instantMessenger.client;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/08/2018
 */
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
		fileMenu.setVerticalAlignment(SwingConstants.CENTER);
		fileMenu.setHorizontalAlignment(SwingConstants.LEFT);
		changeUserNameItem = new JMenuItem("Change User Name...");
		changeUserNameItem.setVerticalAlignment(SwingConstants.CENTER);
		changeUserNameItem.setHorizontalAlignment(SwingConstants.LEFT);
		changeUserNameItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				new ChangeUserNameDialog(parentFrame);
			}
		});
		saveChatLogItem = new JMenuItem("Save Chat Log...");
		saveChatLogItem.setVerticalAlignment(SwingConstants.CENTER);
		saveChatLogItem.setHorizontalAlignment(SwingConstants.LEFT);
		saveChatLogItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				new SaveChatLogDialog(parentFrame);
			}
		});
		fileMenu.add(changeUserNameItem);
		fileMenu.add(saveChatLogItem);
		editMenu = new JMenu("Edit");
		editMenu.setVerticalAlignment(SwingConstants.CENTER);
		editMenu.setHorizontalAlignment(SwingConstants.LEFT);
		connectionMenu = new JMenu("Connection");
		connectionMenu.setVerticalAlignment(SwingConstants.CENTER);
		connectionMenu.setHorizontalAlignment(SwingConstants.LEFT);
		connectToServerItem = new JMenuItem("Connect to Server...");
		connectToServerItem.setVerticalAlignment(SwingConstants.CENTER);
		connectToServerItem.setHorizontalAlignment(SwingConstants.LEFT);
		connectToServerItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				
			}
		});
		disconnectItem = new JMenuItem("Disconnect");
		disconnectItem.setVerticalAlignment(SwingConstants.CENTER);
		disconnectItem.setHorizontalAlignment(SwingConstants.LEFT);
		disconnectItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				parentFrame.disconnect();
			}
		});
		connectionMenu.add(connectToServerItem);
		connectionMenu.add(disconnectItem);
		exitItem = new JMenuItem("Exit");
		exitItem.setVerticalAlignment(SwingConstants.CENTER);
		exitItem.setHorizontalAlignment(SwingConstants.LEFT);
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