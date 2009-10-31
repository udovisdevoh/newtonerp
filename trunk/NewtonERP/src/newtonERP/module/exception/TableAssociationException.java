package newtonERP.module.exception;

/**
 * Exception thrown when table association fails
 * @author Guillaume Lacasse
 */
public class TableAssociationException extends Exception
{
    /**
     * @param message message de l'esception
     */
    public TableAssociationException(String message)
    {
	super(message);
    }
}
