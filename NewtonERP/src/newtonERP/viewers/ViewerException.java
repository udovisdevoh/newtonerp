package newtonERP.viewers;

/**
 * 
 * @author Guillaume Lacasse, Pascal Lemay
 * 
 *         Represents the general viewer exception
 */
public class ViewerException extends Exception
{
    /**
     * Default constructor for the exception
     * 
     * @param message the message of exception
     */
    public ViewerException(String message)
    {
	super(message);
    }
}
