package src.instantMessenger.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import static src.instantMessenger.util.Constants.LISTENER_THREAD;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/22/2018
 */
@SuppressWarnings("javadoc")
final class ConnectToServerDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JLabel serverIPLabel;

	private JLabel serverPortLabel;

	private JTextField serverIPTextField;

	private JTextField serverPortTextField;

	private JButton cancelButton;

	private JButton connectButton;
	
	
	ConnectToServerDialog(ClientFrame parentFrame) {
		super(parentFrame, "Connect to Server");
		setResizable(false);
		setLocationRelativeTo(parentFrame);
		setLayout(null);
		serverIPLabel = new JLabel("Server IP:");
		serverPortLabel = new JLabel("Server Port:");
		serverIPTextField = new JTextField();
		try {
			serverIPTextField.setText(parentFrame.getClient().getServerIP());
		} catch (NullPointerException e) {}
		serverPortTextField = new JTextField();
		serverPortTextField.setText(Integer.toString(parentFrame.getClient().getServerPort()));
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				dispose();
			}
		});
		connectButton = new JButton("Connect");
		connectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					parentFrame.getClient().setServerIP(serverIPTextField.getText());
					parentFrame.getClient().setServerPort(Short.parseShort(serverPortTextField.getText()));
					parentFrame.getClient().connect();
					LISTENER_THREAD.run();
					dispose();
				} catch (UnknownHostException e) {
					JOptionPane.showMessageDialog(parentFrame, "The IP address you entered is invalid. Please enter a valid IPV4 address.", "Invalid IP Address",
							JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(parentFrame, "The server port you entered is invalid. Please enter a port from the range 0-65535.", "Invalid Server Port",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(parentFrame, "The connection could not be established. Please try again.", "Connection Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		getContentPane().add(serverIPLabel);
		getContentPane().add(serverPortLabel);
		getContentPane().add(serverIPTextField);
		getContentPane().add(serverPortTextField);
		getContentPane().add(cancelButton);
		getContentPane().add(connectButton);
		serverIPLabel.setBounds(36, 12, 75, 35);
		serverPortLabel.setBounds(36, 53, 75, 35);
		serverIPTextField.setBounds(137, 12, 150, 35);
		serverPortTextField.setBounds(137, 53, 150, 35);
		cancelButton.setBounds(36, 100, 100, 35);
		connectButton.setBounds(176, 100, 100, 35);
		setSize(315, 180);
		setVisible(true);
	}

}