package src.instantMessenger.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import src.instantMessenger.client.controller.ClientListener;

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
	String PROGRAM_VERSION = "04.21.18";
	
	ClientListener LISTENER_THREAD = new ClientListener();
	
	/**
	 * Font used in the chat screen.
	 */
	Font CHAT_FONT = new Font("Courier new", Font.PLAIN, 12);

	/**
	 * Bold font used in the chat screen.
	 */
	Font CHAT_FONT_BOLD = new Font("Courier new", Font.BOLD, 12);

	/**
	 * @return The current time.
	 */
	static String getTime() {
		return DateTimeFormatter.ofPattern("hh:mm a").format(LocalDateTime.now());
	}

}