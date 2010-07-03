package newtonERP.orm.exceptions;

/**
 * OrmException super class for all the other classes
 * 
 * @author r3lacasgu, r3hallejo
 */
public class OrmException extends RuntimeException
{
	/**
	 * @param message the message of the exception
	 */
	public OrmException(String message)
	{
		super(message);
	}

}
