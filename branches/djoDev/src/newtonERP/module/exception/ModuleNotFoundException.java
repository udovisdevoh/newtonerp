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
}
