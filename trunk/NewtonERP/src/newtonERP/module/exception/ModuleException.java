package newtonERP.module.exception;

/**
 * @author r3hallejo
 * 
 *         Exception class representing a module exception
 */
public class ModuleException extends RuntimeException
{
	/**
	 * Default constructor for the module exception
	 * 
	 * @param message the message
	 */
	public ModuleException(String message)
	{
		super(message);
	}

	/**
	 * 
	 */
	public ModuleException()
	{
		super();
	}

	/**
	 * @param message the message
	 * @param cause the cause
	 */
	public ModuleException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param cause the cause
	 */
	public ModuleException(Throwable cause)
	{
		super(cause);
	}
}
