package src.instantMessenger.client;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/07/2018
 */
public class ClientMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private final JMenu fileMenu;

	private final JMenu editMenu;

	private final JMenu connectionMenu;

	private final JMenuItem exitItem;

	private final JMenuItem saveChatLogButton;
	
	public static void main(String[] args) {
		javax.swing.JFrame fuck = new javax.swing.JFrame();
		fuck.setSize(500, 300);
		fuck.add(new ClientMenuBar());
		fuck.setVisible(true);
	}
	
	ClientMenuBar() {
		super();
		fileMenu = new JMenu("File");
		saveChatLogButton = new JMenuItem("Save Chat Log...");
		saveChatLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				
			}
		});
		fileMenu.add(saveChatLogButton);
		editMenu = new JMenu("Edit");
		connectionMenu = new JMenu("Connection");
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				ClientGUI.terminate();
			}
		});
		add(fileMenu);
		add(editMenu);
		add(connectionMenu);
		add(exitItem);
		setBounds(0, 0, 500, 20);
		//setVisible(true);
	}
	
}