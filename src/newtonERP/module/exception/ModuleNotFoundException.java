package newtonERP.module.exception;

/**
 * @author cloutierJo
 * 
 *         exception lancé lorsque le module ne peut etre trouvé
 */
public class ModuleNotFoundException extends ModuleException
{
	/**
	 * Constructeur par default pour cette exception
	 * 
	 * @param moduleName nom du module
	 */
	public ModuleNotFoundException(String moduleName)
	{
		super(moduleName + " module doesn't exist");
	}

	/**
	 * 
	 */
	public ModuleNotFoundException()
	{
		super();
	}

	/**
	 * @param moduleName the module name
	 * @param cause the cause
	 */
	public ModuleNotFoundException(String moduleName, Throwable cause)
	{
		super(moduleName + " module doesn't exist", cause);
	}

	/**
	 * @param cause the cause
	 */
	public ModuleNotFoundException(Throwable cause)
	{
		super(cause);
	}
}
