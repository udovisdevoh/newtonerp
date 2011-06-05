package newtonERP.logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * @author r3hallejo Class used to log system messages into an external log for purposes of viewing errors, information
 *         messages, warnings and much more that has happen on a later time. HOWTO : (Static class)
 *         Logger.log("MY MESSAGE GOES HERE", Log.State.STATE_OF_MESSAGE) See the state enumeration for all the actual
 *         states available For better results we might have to think a better way of implementing the logging in all
 *         the project so we wont have dozens of logger objects accessing the log file causing multiple write lines at
 *         the same time
 */
public class Logger {

	private static FileWriter fstream = null;
	private static BufferedWriter out = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy.MM.dd");

	/**
	 * States the logger can have
	 */
	public enum State {
		/**
		 * An debug message
		 */
		DEBUG,

		/**
		 * An information message
		 */
		INFO,

		/**
		 * A warning message
		 */
		WARNING,

		/**
		 * An error message
		 */
		ERROR;
	}

	/**
	 * initialize the loging system if not already done
	 */
	public static void init() {
		try{
			if(out == null){
				if(fstream != null){
					fstream.close();
					fstream = null;
				}
				fstream = new FileWriter("logs.txt", true);
				out = new BufferedWriter(fstream);
			}
		}catch(IOException e){
			e.printStackTrace(); // we do not want the logging system to crash
			// the app
		}
	}

	/**
	 * properly close all File writer
	 */
	public static void close() {
		try{
			out.close();
		}catch(IOException e){
			e.printStackTrace(); // we do not want the logging system to crash
			// the app
		}
	}

	/**
	 * Used to log a message, passing a message and the desired state
	 * 
	 * @param message the message
	 * @param state the state
	 */
	public static void log(String message, State state) {
		String strState;
		init();

		switch(state){
			case DEBUG:
				strState = "DEBUG";
				break;
			default:
			case INFO:
				strState = "INFO";
				break;
			case WARNING:
				strState = "WARNING";
				break;
			case ERROR:
				strState = "ERROR";
				break;
		}
		message = sdf.format(new GregorianCalendar().getTime()) + " [" + strState + "]  \t" + message;
		try{
			out.write('\n' + message);
		}catch(IOException e){
			e.printStackTrace(); // we do not want the logging system to crash
			// the app
		}
		switch(state){
			case DEBUG:
			case INFO:
				System.out.println(message);
				break;
			case WARNING:
			case ERROR:
			default:
				System.err.println(message);
				break;
		}
	}

	/**
	 * helper function to log a Debug message
	 * 
	 * @param message the message
	 */
	public static void debug(String message) {
		log(message, State.DEBUG);
	}

	/**
	 * helper function to log an Info message
	 * 
	 * @param message the message
	 */
	public static void info(String message) {
		log(message, State.INFO);
	}

	/**
	 * helper function to log a Warning message
	 * 
	 * @param message the message
	 */
	public static void warning(String message) {
		log(message, State.WARNING);
	}

	/**
	 * helper function to log an Error message
	 * 
	 * @param message the message
	 */
	public static void error(String message) {
		log(message, State.ERROR);
	}
}
