package newtonERP.module.exception;

/**
 * 
 * @author r3hallejo
 * 
 *         Exception class representing a global entity exception
 */
public class EntityException extends Exception
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
}
