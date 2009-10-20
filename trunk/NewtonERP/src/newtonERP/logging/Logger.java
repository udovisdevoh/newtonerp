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
 *         Class used to log system messages into an external log for purposes
 *         of viewing errors, information messages, warnings and much more that
 *         has happen on a later time.
 * 
 *         HOWTO : (Static class) Logger.log("MY MESSAGE GOES HERE",
 *         Log.State.STATE_OF_MESSAGE)
 * 
 *         See the state enumeration for all the actual states available
 * 
 *         For better results we might have to think a better way of
 *         implementing the logging in all the project so we wont have dozens of
 *         logger objects accessing the log file causing multiple write lines at
 *         the same time
 */
public class Logger
{

    private static FileWriter fstream;
    private static BufferedWriter out;
    private static GregorianCalendar gc = new GregorianCalendar();
    private static SimpleDateFormat sdf = new SimpleDateFormat(
	    "k:mm:ss yyyy.MM.dd");

    /**
     * @author r3hallejo
     * 
     *         States the logger can have
     */
    public enum State
    {
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
     * Used to log a message, passing a message and the desired state
     * 
     * @param message the message
     * @param state the state
     */
    public static void log(String message, State state)
    {
	try
	{
	    fstream = new FileWriter("logs.txt", true);
	    out = new BufferedWriter(fstream);
	} catch (IOException e)
	{
	    e.printStackTrace();
	}

	try
	{
	    switch (state)
	    {
	    default:
	    case INFO:
		out.write('\n' + "[INFO " + sdf.format(gc.getTime()) + "] "
			+ message);
		break;
	    case WARNING:
		out.write('\n' + "[WARNING " + sdf.format(gc.getTime()) + "] "
			+ message);
		break;
	    case ERROR:
		out.write('\n' + "[ERROR " + sdf.format(gc.getTime()) + "] "
			+ message);
		break;
	    }
	} catch (IOException e)
	{
	    e.printStackTrace();
	}

	try
	{
	    out.close();
	} catch (IOException e)
	{
	    e.printStackTrace();
	}

	System.out.println(message);
    }
}
