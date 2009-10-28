package newtonERP.orm.exceptions;

/**
 * Basic exception class representing an sql exception that can be thrown from
 * the orm
 * 
 * @author r3hallejo
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
