package src.instantMessenger.client;
import javax.swing.JDialog;
import javax.swing.JLabel;
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

	public static void main(String[] args) {
		new ConnectToServerDialog(null);
	}

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
		setLayout(null);
		serverIPLabel = new JLabel("Server IP:");
		serverPortLabel = new JLabel("Serve Port:");
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
				parentFrame.getClient().setServerPort(Integer.parseInt(serverPortTextField.getText()));
				dispose();
			}
		});
		add(serverIPLabel);
		add(serverPortLabel);
		add(serverIPTextField);
		add(serverPortTextField);
		add(cancelButton);
		add(connectButton);
		
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