package newtonERP.serveur;

import java.util.Hashtable;

import newtonERP.ListModule;
import newtonERP.module.AbstractEntity;
import newtonERP.module.Module;
import newtonERP.module.ModuleException;
import newtonERP.module.ModuleNotFoundException;

/**
 * @author Gabriel Therrien JoCloutier
 * 
 */
public class CommandRouteur
{
    /**
     * dirige une demande client vers une action d'un module
     * 
     * @param moduleName nom du module
     * @param action action a executer
     * @param moduleGetter la nom du modulGetter a utiliser
     * @param parameter parametre a passer a l'action
     * @return une entity viewable
     */
    public AbstractEntity routeCommand(String moduleName, String action,
	    Hashtable<String, String> parameter)
    {

	Module module;
	AbstractEntity retView = null;

	Hashtable<String, String> rightParam = new Hashtable<String, String>();
	// on verifi les droit d'acces a l'Action
	try
	{
	    module = ListModule.getModule("userRightModule");
	    rightParam.put("name", parameter.get("user"));
	    rightParam.put("module", moduleName);
	    rightParam.put("action", action);
	    if (module.doAction("checkRight", rightParam) != null)
	    {
		// on fais l'Action demander par le user si les droit son
		// trouver
		module = ListModule.getModule(moduleName);
		retView = module.doAction(action, parameter);
	    }
	} catch (ModuleNotFoundException e1)
	{
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	} catch (ModuleException e1)
	{
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	return retView;
    }
}
