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
 * @version 02/08/2018
 */
final class ChangeUserNameDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JLabel changeUserNameLabel;
	
	private JTextField userNameTextField;
	
	private JButton cancelButton;
	
	private JButton saveButton;
	
	ChangeUserNameDialog(ClientFrame parentFrame) {
		super(parentFrame, "Change User Name");
		setLocationRelativeTo(parentFrame);
		setResizable(false);
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