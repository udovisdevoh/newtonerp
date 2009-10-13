package newtonERP.orm.exceptions;

/**
 * @author r3hallejo
 * 
 *         Basic exception class representing an sql exception that can be
 *         thrown from the orm
 */
public class OrmSqlException extends OrmException
{
    /**
     * @param message the message of the exception
     */
    public OrmSqlException(String message)
    {
	super(message);
    }
}
