package newtonERP.logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * @author r3hallejo
 * 
 * 
 * Class used to log system messages into an external log for purposes
 * of viewing errors, information messages, warnings and much more 
 * that has happen on a later time.
 * 
 * HOWTO :
 * 
 * 1. Create a Log object
 * 2. Use the "log" method like this :
 * 		logger.log("MY MESSAGE GOES HERE", Log.State.STATE_OF_MESSAGE)
 * 
 * See the state enumeration for all the actual states available
 */
public class Log {
	
	private FileWriter fstream;
	private BufferedWriter out;
	private GregorianCalendar gc = new GregorianCalendar();
	private SimpleDateFormat sdf = new SimpleDateFormat("k:mm:ss yyyy.MM.dd");
	
	// States for a log message, we can potentially add more
	public enum State {
		INFO, WARNING, ERROR
	}
	
	/**
	 * Used to log a message, passing a message and the desired state
	 * 
	 * @param message
	 * @param state
	 */
	public void log(String message, State state) {
		try {
			fstream = new FileWriter("logs.txt",true);
			out = new BufferedWriter(fstream);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		try {
			switch (state) {
				default :
				case INFO:
					out.write('\n'+"[INFO "+ sdf.format(gc.getTime())+"] "+message);
					break;
				case WARNING:
					out.write('\n'+"[WARNING "+ sdf.format(gc.getTime())+"] +"+message);
					break;
				case ERROR:
					out.write('\n'+"[ERROR "+ sdf.format(gc.getTime())+"] +"+message);
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(message);
	}
}
