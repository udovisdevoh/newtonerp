package newtonERP.orm.exceptions;

/**
 * 
 * @author r3lacasgu, r3hallejo
 * 
 *         Exception class representing an exception for a field that has not
 *         been found
 */
public class OrmFieldNotFoundException extends OrmException
{
    /**
     * @param message the message of the exception
     */
    public OrmFieldNotFoundException(String message)
    {
	super(message);
    }

}
