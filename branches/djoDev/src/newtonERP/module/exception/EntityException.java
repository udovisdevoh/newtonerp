package newtonERP.module.exception;

/**
 * 
 * @author r3hallejo
 * 
 *         Exception class representing a global entity exception
 */
public class EntityException extends RuntimeException
{
	/**
	 * Default constructor for the entity exception
	 * 
	 * @param message the message
	 */
	public EntityException(String message)
	{
		super(message);
	}

	public EntityException()
	{
		super();
	}

	/**
	 * @param message the message
	 * @param cause the cause
	 */
	public EntityException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param cause the cause
	 */
	public EntityException(Throwable cause)
	{
		super(cause);
	}

}
