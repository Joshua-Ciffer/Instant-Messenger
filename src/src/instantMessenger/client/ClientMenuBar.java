package src.instantMessenger.client;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
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
		fileMenu.setVerticalAlignment(SwingConstants.CENTER);
		fileMenu.setHorizontalAlignment(SwingConstants.LEFT);
		saveChatLogButton = new JMenuItem("Save Chat Log...");
		saveChatLogButton.setVerticalAlignment(SwingConstants.CENTER);
		saveChatLogButton.setHorizontalAlignment(SwingConstants.LEFT);
		saveChatLogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				new SaveChatLogDialog(parentGUI);
			}
		});
		fileMenu.add(saveChatLogButton);
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
				parentGUI.disconnect();
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