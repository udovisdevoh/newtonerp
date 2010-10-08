package newtonERP.module.exception;

/**
 * @author r3hallejo
 * 
 *         When an operator for a field is not valid
 */
public class InvalidOperatorException extends ModuleException
{
	/**
	 * Default constructor
	 * 
	 * @param message the message
	 */
	public InvalidOperatorException(String message)
	{
		super(message);
	}

	/**
	 * 
	 */
	public InvalidOperatorException()
	{
		super();
	}

	/**
	 * @param message the message
	 * @param cause the cause
	 */
	public InvalidOperatorException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param cause the cause
	 */
	public InvalidOperatorException(Throwable cause)
	{
		super(cause);
	}
}
