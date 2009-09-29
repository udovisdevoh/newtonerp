package newtonERP.serveur;

import java.util.Hashtable;

import newtonERP.ListModule;
import newtonERP.module.Module;

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
     * @param parameter parametre a passer a l'action
     * @return une entity viewable
     */
    public Object routeCommand(String moduleName, String action,
	    Hashtable<String, String> parameter)
    {
	// TODO: changer Object par viewable quand il va y en avoir
	Module module;
	try
	{
	    module = ListModule.getModule(moduleName);
	    module.doAction(action, parameter);
	} catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return 0;
    }
}
