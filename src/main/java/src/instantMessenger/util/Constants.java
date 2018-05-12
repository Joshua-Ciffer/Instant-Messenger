package src.instantMessenger.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Font;

/**
 * Values and objects used globally in different parts of the project.
 *
 * @author Joshua Ciffer
 * @version 04/12/2018
 */
public interface Constants {

	/**
	 * The version of this program.
	 */
	String PROGRAM_VERSION = "05.08.18";

	/**
	 * @return The current time.
	 */
	static String getTime() {
		return DateTimeFormatter.ofPattern("hh:mm a").format(LocalDateTime.now());
	}

	/**
	 * Font used in the chat screen.
	 */
	Font CHAT_FONT = new Font("Courier new", Font.PLAIN, 12);

	/**
	 * @param serverIP
	 *        The server IP that the client connected to.
	 * @param serverPort
	 *        The server port that the client connected to.
	 * @return A message that displays when the client connects to a server.
	 */
	static String connectedMessage(String serverIP, short serverPort) {
		return getTime() + " - LOG: Connected to server " + serverIP + " on port " + serverPort + ".\n";
	}

	/**
	 * The message displayed to the client when they are disconnected from the server.
	 * @param serverIP 
	 * @param serverPort 
	 * @return 
	 */
	static String serverDisconnectedMessage(String serverIP, short serverPort) {
		return getTime() + " - LOG: Disconnected from server " + serverIP + " on port " + serverPort + ".\n";
	}

	/**
	 * The message displayed to the client when their message is not sent because they are not connected to a server.
	 */
	String MESSAGE_NOT_SENT = getTime() + " - LOG: Message not sent, you are not connected to a server.\n";

	/**
	 * The message routinely displayed to the client when they are left unconnected to a server.
	 */
	String UNCONNECTED_MESSAGE = getTime() + " - LOG: You are not connected to a server.\n";

	/**
	 * The message displayed to the client when they click disconnect, but they already are disconnected from the server.
	 */
	String ALREADY_DISCONNECTED = getTime() + " - LOG: You are already disconnected from the server.\n";

}