package src.instantMessenger.client.view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import src.instantMessenger.client.controller.CancelDialogListener;
import src.instantMessenger.client.controller.ConnectListener;

/**
 * Dialog box that allows the user to enter the server IP address and port.
 * 
 * @author Joshua Ciffer
 * @version 05/06/2018
 */
public final class ConnectToServerDialog extends JDialog {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The parent frame that created this dialog box.
	 */
	private ClientFrame parentFrame;

	/**
	 * Labels the server IP text field.
	 */
	private JLabel serverIPLabel;

	/**
	 * Labels the server port text field.
	 */
	private JLabel serverPortLabel;

	/**
	 * Text field for the server IP address.
	 */
	private JTextField serverIPTextField;

	/**
	 * Text field for the server port.
	 */
	private JTextField serverPortTextField;

	/**
	 * Closes the dialog box without saving any changes.
	 */
	private JButton cancelButton;

	/**
	 * Saves the server IP address and port entered in the dialog boxes.
	 */
	private JButton connectButton;

	/**
	 * Constructs and displays a new <code>ConnectToServerDialog</code>.
	 *
	 * @param parentFrame
	 *        The parent frame that created this dialog.
	 */
	ConnectToServerDialog(ClientFrame parentFrame) {
		super(parentFrame, "Connect to Server");
		this.parentFrame = parentFrame;
		setResizable(false);
		setLocationRelativeTo(parentFrame);
		setLayout(null);
		serverIPLabel = new JLabel("Server IP:");
		serverPortLabel = new JLabel("Server Port:");
		serverIPTextField = new JTextField();
		try {
			serverIPTextField.setText(parentFrame.getParentView().getController().getServerIP());
		} catch (NullPointerException e) {}
		serverPortTextField = new JTextField();
		serverPortTextField.setText(Short.toString(parentFrame.getParentView().getController().getServerPort()));
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelDialogListener(this));
		connectButton = new JButton("Connect");
		connectButton.addActionListener(new ConnectListener(this));
		add(serverPortLabel);
		add(serverIPTextField);
		add(serverPortTextField);
		add(cancelButton);
		add(connectButton);
		serverIPLabel.setBounds(36, 12, 75, 35);
		serverPortLabel.setBounds(36, 53, 75, 35);
		serverIPTextField.setBounds(137, 12, 150, 35);
		serverPortTextField.setBounds(137, 53, 150, 35);
		cancelButton.setBounds(36, 100, 100, 35);
		connectButton.setBounds(176, 100, 100, 35);
		setSize(315, 180);
		setVisible(true);
	}

	/**
	 * @return A reference to the parent frame that created this dialog box.
	 */
	public ClientFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * @return The server IP entered in the text field.
	 */
	public String getServerIP() {
		return serverIPTextField.getText();
	}

	/**
	 * @return The server port entered in the text field.
	 * @throws NumberFormatException
	 *         Thrown if the server port entered is not a number.
	 */
	public short getServerPort() throws NumberFormatException {
		return Short.parseShort(serverPortTextField.getText());
	}

}