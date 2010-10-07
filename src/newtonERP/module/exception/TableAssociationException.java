package newtonERP.module.exception;

/**
 * Exception thrown when table association fails
 * @author Guillaume Lacasse
 */
public class TableAssociationException extends RuntimeException
{
	/**
	 * @param message message de l'esception
	 */
	public TableAssociationException(String message)
	{
		super(message);
	}

	/**
	 * 
	 */
	public TableAssociationException()
	{
		super();
	}

	/**
	 * @param message the message
	 * @param cause the cause
	 */
	public TableAssociationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param cause the cause
	 */
	public TableAssociationException(Throwable cause)
	{
		super(cause);
	}
}
