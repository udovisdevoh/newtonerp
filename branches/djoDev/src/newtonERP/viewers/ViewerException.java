package newtonERP.viewers;

/**
 * Represents the general viewer exception
 * @author Guillaume Lacasse, Pascal Lemay
 */
public class ViewerException extends RuntimeException
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

	public ViewerException()
	{
		super();
	}

	/**
	 * @param message the message
	 * @param cause the cause
	 */
	public ViewerException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param cause the cause
	 */
	public ViewerException(Throwable cause)
	{
		super(cause);
	}

}
