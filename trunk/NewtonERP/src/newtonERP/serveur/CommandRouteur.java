package newtonERP.serveur;

import java.util.Hashtable;

import newtonERP.ListModule;
import newtonERP.module.Module;
import newtonERP.viewers.Viewable;

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
    public Viewable routeCommand(String moduleName, String action,
	    String moduleGetter, Hashtable<String, String> parameter)
    {
	// TODO: changer Object par viewable quand il va y en avoir
	Module module;
	Viewable retView = null;
	try
	{
	    module = ListModule.getModule(moduleName);
	    retView = module.doAction(action, moduleGetter, parameter);
	} catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return retView;
    }
}
