package src.instantMessenger.client;
import javax.swing.JFileChooser;
import javax.swing.JDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Joshua Ciffer
 * @version 02/07/2018
 */
public class SaveChatLogDialog extends JFileChooser {

	private static final long serialVersionUID = 1L;
	
	private File chatLog;
	
	private PrintWriter fileWriter;
	
	private Scanner chatLogParser;
	
	public static void main(String[] args) {
		
	}
	
	SaveChatLogDialog(ClientGUI parentGUI) {
		super();
		setDialogTitle("Save Chat Log");
		setDialogType(JFileChooser.SAVE_DIALOG);
		
		setApproveButtonText("Save");
		setApproveButtonToolTipText("Save the log of your chat history.");
		
		if (showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			chatLog = new File(getCurrentDirectory().getAbsolutePath() + "\\ChatLog.txt");
			try {
				fileWriter = new PrintWriter(chatLog);
				chatLog.createNewFile();
				chatLog.setWritable(true);
				chatLogParser = new Scanner(parentGUI.getChatLog());
				chatLogParser.useDelimiter("\n");
				while (chatLogParser.hasNext()) {
					fileWriter.println(chatLogParser.next());
				}
			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
				
			} finally {
				fileWriter.close();
				fileWriter = null;
				chatLog = null;
			}
		}
		setVisible(true);
	}
	
}