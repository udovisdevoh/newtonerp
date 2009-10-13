package newtonERP.module;

/**
 * @author r3hallejo
 * 
 * 	Exception class representing a module exception
 */
public class ModuleException extends Exception
{
    /**
     * Default constructor for the module exception
     * 
     * @param message
     */
    public ModuleException(String message)
    {
	super(message);
    }
}
