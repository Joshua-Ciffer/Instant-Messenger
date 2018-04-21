package src.instantMessenger.client.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;

/**
 * A save chat log dialog is a file saver dialog box that the user can select a file or directory to save their chat history from the chat feed panel.
 * 
 * @author Joshua Ciffer
 * @version 04/21/2018
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
	 * Reads in the chat log in the form of a string from the chat feed panel, and sends it to the file writer.
	 */
	private Scanner chatLogParser;

	/**
	 * Constructs a new save chat dialog.
	 * 
	 * @param parentFrame
	 *        The parent frame that constructed this save chat log dialog.
	 */
	SaveChatLogDialog(ClientFrame parentFrame) {
		super();
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
				chatLogParser = new Scanner(parentFrame.getChatLog());
				chatLogParser.useDelimiter("\n");
				while (chatLogParser.hasNext()) {
					fileWriter.println(chatLogParser.next());
				}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(this, "The file you specified could not be found. The chat log was not saved.", "", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "An IO Exception occurred. The chat log was not saved.", "", JOptionPane.ERROR_MESSAGE);
			} finally {
				fileWriter.close();
				chatLogParser.close();
				chatLog = null;
				textFile = null;
				fileWriter = null;
				chatLogParser = null;
			}
		}
		setVisible(true);
	}

}