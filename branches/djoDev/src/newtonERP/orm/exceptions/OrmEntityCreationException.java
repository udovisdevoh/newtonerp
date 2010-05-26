package newtonERP.orm.exceptions;

/**
 * Class used in the entity creator
 * 
 * @author r3hallejo
 */
public class OrmEntityCreationException extends OrmException
{
    /**
     * @param message the message of the exception
     */
    public OrmEntityCreationException(String message)
    {
	super(message);
    }

}
