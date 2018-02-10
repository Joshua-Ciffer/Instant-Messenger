package src.instantMessenger.client;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/08/2018
 */
final class SaveChatLogDialog extends JFileChooser {

	private static final long serialVersionUID = 1L;
	
	private File chatLog;
	
	private FileNameExtensionFilter textFile;
	
	private PrintWriter fileWriter;
	
	private Scanner chatLogParser;
	
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
				JOptionPane.showMessageDialog(this, "The file you specefied could not be found. The chat log was not saved.", "", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "An IO Exception occoured. The chat log was not saved.", "", JOptionPane.ERROR_MESSAGE);
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