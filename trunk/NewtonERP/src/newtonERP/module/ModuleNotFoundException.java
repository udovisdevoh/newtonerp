package newtonERP.module;

/**
 * @author cloutierJo
 * 
 *         exception lancé lorsque le module ne peut etre trouvé
 * 
 */
public class ModuleNotFoundException extends Exception
{
    /**
     * @param moduleName nom du module
     */
    public ModuleNotFoundException(String moduleName)
    {
	super(moduleName + " module doesn't exist");
    }
}
