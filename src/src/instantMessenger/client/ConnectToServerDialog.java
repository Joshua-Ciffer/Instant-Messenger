package src.instantMessenger.client;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/22/2018
 */
final class ConnectToServerDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JLabel serverIPLabel;

	private JLabel serverPortLabel;

	private JTextField serverIPTextField;

	private JTextField serverPortTextField;

	private JButton cancelButton;
	
	private JButton connectButton;

	ConnectToServerDialog(ClientFrame parentFrame) {
		super(parentFrame, "Conenct to Server");
		setResizable(false);
		setLocationRelativeTo(parentFrame);
		getContentPane().setLayout(null);
		serverIPLabel = new JLabel("Server IP:");
		serverPortLabel = new JLabel("Server Port:");
		serverIPTextField = new JTextField();
		serverIPTextField.setText(parentFrame.getClient().getServerIP());
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
				parentFrame.getClient().setServerIP(serverIPTextField.getText());
				try {
					parentFrame.getClient().setServerPort(Integer.parseInt(serverPortTextField.getText()));
					dispose();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(parentFrame, "The server port you entered is invalid.", "Invalid Server Port", JOptionPane.ERROR_MESSAGE);
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
	
	JLabel getServerIPLabel() {
		return serverIPLabel;
	}
	
	JLabel getServerPortLabel() {
		return serverPortLabel;
	}
	
	JTextField getServerIPTextField() {
		return serverIPTextField;
	}
	
	JTextField getServerPortTextField() {
		return serverPortTextField;
	}
	
	JButton getCancelButton() {
		return cancelButton;
	}
	
	JButton getConnectButton() {
		return connectButton;
	}

}