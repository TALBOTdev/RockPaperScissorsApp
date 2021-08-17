package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Ben Talbot
 * Proves formatted message strings based on incoming message criteria
 *
 */
public class LoggerUtil {
	
	private Message msg;

	public LoggerUtil () {
		
	}
	
	public LoggerUtil (Message msg) {
		this.msg = msg;
	}
	
	//https://stackoverflow.com/questions/16592493/getting-time-from-a-date-object
	/**
	 * Returns a message to signify the initialization of a client
	 * @param msg
	 * @return Message string for display
	 */
	public static String getInitializationMessage(Message msg) {
		Date date = new Date();
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
		String time = localDateFormat.format(date);
		return "User " + msg.getUser() + " connected at: " + time + "\n";
	}
	
	/**
	 * Returns a message to signify a client has disconnected
	 * @param msg Message for display
	 * @return
	 */
	public static String getDisconnectMessage(Message msg) {
		Date date = msg.getTimeStamp();
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
		String time = localDateFormat.format(date);
		return "User " + msg.getUser() + " disconnected at: " + time + "\n";
	}
	
	/**
	 * Generic message used for client chat
	 * @param msg Message for display
	 * @return
	 */
	public static String getMessage(Message msg) {
		return msg.getUser() + ": " + msg.getMsg() + "\n";
	}

	/**
	 * Generic message to signify a players choice
	 * @param msg
	 * @return
	 */
	public static String getPlayerChoiceMessage(Message msg) {
		
		return msg.getUser() + " chooses " + msg.getMsg() + "\n";
	}
}
