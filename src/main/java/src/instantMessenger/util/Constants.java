package src.instantMessenger.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Font;

/**
 * Values and objects used globally in different parts of the project.
 *
 * @author Joshua Ciffer
 * @version 03/31/2018
 */
public interface Constants {

	/**
	 * Font used in the chat screen.
	 */
	public static final Font CHAT_FONT = new Font("Courier new", Font.PLAIN, 12);

	/**
	 * Bold font used in the chat screen.
	 */
	public static final Font CHAT_FONT_BOLD = new Font("Courier new", Font.BOLD, 12);

	/**
	 * ASCII value for the enter key.
	 */
	public static final int ENTER_KEY = 13;

	/**
	 * @return The current time.
	 */
	public static String getTime() {
		return DateTimeFormatter.ofPattern("hh:mm a").format(LocalDateTime.now());
	}

}