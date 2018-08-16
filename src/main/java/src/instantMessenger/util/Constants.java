package src.instantMessenger.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Font;

/**
 * Values and objects used globally in different parts of the project.
 *
 * @author Joshua Ciffer
 * @version 07/04/2018
 */
public interface Constants {

	/**
	 * The version of this program.
	 */
	String PROGRAM_VERSION = "07.04.18";

	/**
	 * Font used in the chat screen.
	 */
	Font CHAT_FONT = new Font("Courier new", Font.PLAIN, 12);

	/**
	 * Returns a formatted string of the current time. Uses the pattern hh:mm a, example: 03:35 PM.
	 * 
	 * @return The current time.
	 */
	static String getTime() {
		return DateTimeFormatter.ofPattern("hh:mm a").format(LocalDateTime.now());
	}

	/**
	 * Returns a formatted message that displays when the client connects to a server.
	 * 
	 * @param serverIP
	 *        The server IP that the client connected to.
	 * @param serverPort
	 *        The server port that the client connected to.
	 * @return A message that displays when the client connects to a server.
	 */
	static String connectedToServerMessage(String serverIP, short serverPort) {
		return getTime() + " - LOG: Connected to server " + serverIP + " on port " + serverPort + ".\n";
	}

	/**
	 * Returns a formatted message that displays when the client disconnects from a server.
	 * 
	 * @param serverIP
	 *        The IP of the server the client disconnected from.
	 * @param serverPort
	 *        The port of the server the client disconnected from.
	 * @return A message that displays when the client disconnects from a server.
	 */
	static String disconnectedFromServerMessage(String serverIP, short serverPort) {
		return getTime() + " - LOG: Disconnected from server " + serverIP + " on port " + serverPort + ".\n";
	}

	/**
	 * Returns a formatted message that displays when the client changes their user name.
	 *
	 * @param userName
	 *        The user name the client changed.
	 * @return A message that displays when the client changes their user name.
	 */
	static String changedUserNameMessage(String userName) {
		return getTime() + " - LOG: Changed user name to \"" + userName + "\".\n";
	}

	/**
	 * The message displayed to the client when their message is not sent because they are not connected to a server.
	 */
	String MESSAGE_NOT_SENT_MESSAGE = getTime() + " - LOG: Message not sent, you are not connected to a server.\n";

	/**
	 * The message displayed to the client when they click disconnect, but they already are disconnected from the server.
	 */
	String ALREADY_DISCONNECTED_MESSAGE = getTime() + " - LOG: You are already disconnected from the server.\n";

	/**
	 * The message displayed to the client when the connection between them and the server becomes interrupted.
	 */
	String SERVER_CONNECTION_LOST_MESSAGE = getTime() + " - LOG: The connection to the server has been lost.\n";

}