package src.instantMessenger.client;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/08/2018
 */
public class ClientMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private final JMenu fileMenu;
	
	private final JMenuItem saveChatLogButton;
	
	private final JMenu editMenu;

	private final JMenu connectionMenu;
	
	private final JMenuItem connectToServerItem;
	
	private final JMenuItem disconnectItem;

	private final JMenuItem exitItem;
	
	ClientMenuBar(ClientGUI parentGUI) {
		super();
		fileMenu = new JMenu("File");
		saveChatLogButton = new JMenuItem("Save Chat Log...");
		saveChatLogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				new SaveChatLogDialog(parentGUI);
			}
		});
		fileMenu.add(saveChatLogButton);
		editMenu = new JMenu("Edit");
		connectionMenu = new JMenu("Connection");
		connectToServerItem = new JMenuItem("Connect to Server...");
		connectToServerItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				
			}
		});
		disconnectItem = new JMenuItem("Disconnect");
		disconnectItem.addActionListener(new ActionListener() {
			@Override
			parrentGUI.disconnect();
		});
		connectionMenu.add(connectToServerItem);
		connectionMenu.add(disconnectItem);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				parentGUI.terminate();
			}
		});
		add(fileMenu);
		add(editMenu);
		add(connectionMenu);
		add(exitItem);
		setBounds(0, 0, 500, 20);
	}
	
}
