package newtonERP.viewers;

/**
 * Represents the general viewer exception
 * @author Guillaume Lacasse, Pascal Lemay
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
