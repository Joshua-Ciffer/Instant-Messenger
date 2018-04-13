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
	 * Font used in the chat screen.
	 */
	Font CHAT_FONT = new Font("Courier new", Font.PLAIN, 12);

	/**
	 * Bold font used in the chat screen.
	 */
	Font CHAT_FONT_BOLD = new Font("Courier new", Font.BOLD, 12);

	/**
	 * ASCII value for the enter key.
	 */
	int ENTER_KEY = 13;

	/**
	 * @return A randomly generated username.
	 */
	static String generateUserName() {
		return "User" + (int)(Math.random() * 1_000);
	}

	/**
	 * @return The current time.
	 */
	static String getTime() {
		return DateTimeFormatter.ofPattern("hh:mm a").format(LocalDateTime.now());
	}

}