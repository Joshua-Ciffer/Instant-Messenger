package src.instantMessenger.util;
import java.time.format.DateTimeFormatter;
import java.awt.Font;

public interface Constants {

	public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("hh:mm a");
	
	public static final Font CHAT_FONT = new Font("Courier new", Font.PLAIN, 12);
	
	public static final Font CHAT_FONT_BOLD = new Font("Courier new", Font.BOLD, 12);
	
	public static final int ENTER_KEY = 13;
	
}