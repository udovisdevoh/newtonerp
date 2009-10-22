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
}
