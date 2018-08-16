package src.instantMessengerTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains resources and values for the test package.
 *
 * @author Joshua Ciffer
 * @version 08/16/2018
 */
public interface Constants {

	/**
	 * The IP address the test server is hosting on.
	 */
	String TEST_SERVER_IP = "192.168.50.151";

	/**
	 * The port the test server is hosting on.
	 */
	short TEST_SERVER_PORT = 27015;

	/**
	 * Returns a formatted string of the current time. Uses the pattern hh:mm a, example: 03:35 PM.
	 * 
	 * @return The current time.
	 */
	static String getTime() {
		return DateTimeFormatter.ofPattern("hh:mm a").format(LocalDateTime.now());
	}

}