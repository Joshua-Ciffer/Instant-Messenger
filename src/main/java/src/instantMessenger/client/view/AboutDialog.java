package src.instantMessenger.client.view;

import javax.swing.JDialog;
import javax.swing.JLabel;

import static src.instantMessenger.util.Constants.PROGRAM_VERSION;

/**
 * Simple dialog box showing the program version, author, and GitHub repo.
 *
 * @author Joshua Ciffer
 * @version 04/21/2018
 */
final class AboutDialog extends JDialog {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Displays the program version.
	 */
	private JLabel versionLabel;

	/**
	 * Displays my name because I wrote this whole damn thing.
	 */
	private JLabel authorLabel;

	/**
	 * Plugs my GitHub repo.
	 */
	private JLabel gitHubLabel;

	/**
	 * Constructs a new instance of <code>AboutDialog</code>.
	 *
	 * @param parentFrame
	 *        The parent frame that this instance was created from.
	 */
	AboutDialog(ClientFrame parentFrame) {
		super(parentFrame, "About");
		setResizable(false);
		setLocationRelativeTo(parentFrame);
		setLayout(null);
		versionLabel = new JLabel("Instant Messenger Version: " + PROGRAM_VERSION);
		authorLabel = new JLabel("Written By: Joshua Ciffer");	// Thats right, I hard coded my name into here.
		gitHubLabel = new JLabel("GitHub Repo: https://github.com/Joshua-Ciffer/Instant-Messenger");
		add(versionLabel);
		add(authorLabel);
		add(gitHubLabel);
		versionLabel.setBounds(1, 35, 385, 15);
		authorLabel.setBounds(1, 55, 385, 15);
		gitHubLabel.setBounds(1, 75, 385, 15);
		setSize(385, 180);
		setVisible(true);
	}

}