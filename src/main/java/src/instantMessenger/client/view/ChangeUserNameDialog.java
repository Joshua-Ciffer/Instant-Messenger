package src.instantMessenger.client.view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import src.instantMessenger.client.controller.CancelDialogListener;
import src.instantMessenger.client.controller.ChangeUserNameListener;

/**
 * A change user name dialog allows the user to set their user name that identifies them in the chat server.
 * 
 * @author Joshua Ciffer
 * @version 05/06/2018
 */
public final class ChangeUserNameDialog extends JDialog {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The parent frame that constructs this change user name dialog.
	 */
	private ClientFrame parentFrame;

	/**
	 * Labels the user name text field.
	 */
	private JLabel changeUserNameLabel;

	/**
	 * Allows the user to enter their user name.
	 */
	private JTextField userNameTextField;

	/**
	 * Closes the dialog box without saving the name entered in the dialog box.
	 */
	private JButton cancelButton;

	/**
	 * Saves the current name entered in the text field.
	 */
	private JButton saveButton;

	/**
	 * Constructs a new <code>ChangeUserNameDialog</code> window.
	 * 
	 * @param parentFrame
	 *        The parent frame that constructs this change user name dialog.
	 */
	ChangeUserNameDialog(ClientFrame parentFrame) {
		super(parentFrame, "Change User Name");
		this.parentFrame = parentFrame;
		setResizable(false);
		setLocationRelativeTo(parentFrame);
		setLayout(null);
		changeUserNameLabel = new JLabel("Enter your user name:");
		userNameTextField = new JTextField();
		userNameTextField.setText(parentFrame.getParentView().getController().getUserName());
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelDialogListener());
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ChangeUserNameListener(this));
		add(changeUserNameLabel);
		add(userNameTextField);
		add(cancelButton);
		add(saveButton);
		changeUserNameLabel.setBounds(90, 0, 135, 50);
		userNameTextField.setBounds(45, 45, 225, 30);
		cancelButton.setBounds(40, 95, 100, 30);
		saveButton.setBounds(170, 95, 100, 30);
		setSize(315, 180);
		setVisible(true);
	}

	/**
	 * @return The parent frame that constructed this dialog box.
	 */
	public ClientFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * @return The user name entered in the user name text field.
	 */
	public String getUserName() {
		return userNameTextField.getText();
	}

}