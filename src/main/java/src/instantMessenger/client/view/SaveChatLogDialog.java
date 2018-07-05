package src.instantMessenger.client.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.LookAndFeel;

/**
 * A <code>SaveChatLogDialog</code> is a file saver dialog box that the user can select a file or directory to save their chat history from the chat feed panel.
 * 
 * @author Joshua Ciffer
 * @version 07/04/2018
 */
final class SaveChatLogDialog extends JFileChooser {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The text file that is written to with the chat log.
	 */
	private File chatLog;

	/**
	 * The file extension of the chat log (.txt).
	 */
	private FileNameExtensionFilter textFile;

	/**
	 * Writes to the chat log file.
	 */
	private PrintWriter fileWriter;

	/**
	 * Constructs a new <code>SaveChatLogDialog</code>.
	 * 
	 * @param parentFrame
	 *        The parent frame that constructed this instance.
	 */
	SaveChatLogDialog(ClientFrame parentFrame) {
		super();
		try {
			LookAndFeel temp = UIManager.getLookAndFeel();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			updateUI();
			UIManager.setLookAndFeel(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDialogTitle("Save Chat Log");
		setDialogType(JFileChooser.SAVE_DIALOG);
		setFileSelectionMode(JFileChooser.FILES_ONLY);
		setFileFilter(textFile = new FileNameExtensionFilter("Text file", "txt"));
		setApproveButtonText("Save");
		setApproveButtonToolTipText("Save the log of your chat history.");
		if (showSaveDialog(parentFrame) == JFileChooser.APPROVE_OPTION) {
			chatLog = getSelectedFile();
			if (!textFile.accept(chatLog)) {
				chatLog = new File(chatLog.getPath() + ".txt");
			}
			try {
				fileWriter = new PrintWriter(chatLog);
				chatLog.createNewFile();
				chatLog.setWritable(true);
				fileWriter.write(parentFrame.getChatFeed());
				fileWriter.flush();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(this, "The file you specified could not be found. The chat log was not saved.", "File Not Found", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "The file you specified could not be written to.", "IO Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} finally {
				fileWriter.close();
				chatLog = null;
				textFile = null;
				fileWriter = null;
			}
		}
		setVisible(true);
	}

}