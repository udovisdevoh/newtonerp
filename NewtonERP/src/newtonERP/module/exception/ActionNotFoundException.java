package newtonERP.module.exception;

/**
 * @author cloutierJo
 * 
 *         exception lancé lorsque le module ne peut etre trouvé
 */
public class ActionNotFoundException extends ModuleException
{
    /**
     * Constructeur par default pour cette exception
     * 
     * @param actionName nom de l'action non trouvé
     * @param moduleName nom du module
     */
    public ActionNotFoundException(String actionName)
    {
	super(actionName + " action doesn't exist");
    }
}
