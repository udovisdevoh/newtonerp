package newtonERP.orm.exceptions;

/**
 * @author r3lacasgu, r3hallejo
 * 
 *         OrmException super class for all the other classes
 */
public class OrmException extends Exception
{
    /**
     * @param message the message of the exception
     */
    public OrmException(String message)
    {
	super(message);
    }

}
