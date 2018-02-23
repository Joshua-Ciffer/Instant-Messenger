package src.instantMessenger.client;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A change user name dialog allows the user to set their user name that identifies them in the chat server.
 * 
 * @author Joshua Ciffer
 * @version 02/22/2018
 */
final class ChangeUserNameDialog extends JDialog {

	public static void main(String[] args) {
		new ChangeUserNameDialog(null);
	}
	
	private static final long serialVersionUID = 1L;

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
	 * Constructs a new ChangeUserNameDialog window.
	 * 
	 * @param parentFrame - The parent frame that constructs this change user name dialog.
	 */
	ChangeUserNameDialog(ClientFrame parentFrame) {
		super(parentFrame, "Change User Name");
		setResizable(false);
		setLocationRelativeTo(parentFrame);
		setLayout(null);
		changeUserNameLabel = new JLabel("Enter your user name:");
		userNameTextField = new JTextField();
		userNameTextField.setText(parentFrame.getClient().getUserName());
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				dispose();
			}
		});
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				parentFrame.getClient().setUserName(userNameTextField.getText());
				dispose();
			}
		});
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
	
}