package newtonERP.module.exception;

/**
 * @author r3hallejo
 * 
 *         Exception class representing a module exception
 */
public class ModuleException extends Exception
{
    /**
     * Default constructor for the module exception
     * 
     * @param message the message
     */
    public ModuleException(String message)
    {
	super(message);
    }
}
