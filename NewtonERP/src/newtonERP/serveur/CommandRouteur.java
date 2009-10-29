package newtonERP.serveur;

import java.util.Hashtable;

import modules.userRightModule.actions.RightCheck;
import newtonERP.Authentication;
import newtonERP.ListModule;
import newtonERP.module.AbstractEntity;
import newtonERP.module.Module;
import newtonERP.module.generalEntity.AlertEntity;

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
     * @param actionName BaseAction a executer
     * @param entityName nom de l'entite contenant la baseAction
     * @param moduleGetter la nom du modulGetter a utiliser
     * @param parameter parametre a passer a l'action
     * @return une entity viewable
     * @throws Exception remonte
     */
    public AbstractEntity routeCommand(String moduleName, String actionName,
	    String entityName, Hashtable<String, String> parameter)
	    throws Exception
    {

	Module module = ListModule.getModule(moduleName);
	AbstractEntity retView = null;
	Hashtable<String, String> rightParam = new Hashtable<String, String>();
	// on verifi les droit d'acces a l'Action

	rightParam.put("name", Authentication.getCurrentUserName());
	rightParam.put("module", moduleName);
	if (actionName.equals("default"))
	    rightParam.put("action", module.getDefaultAction());
	else
	    rightParam.put("action", actionName);
	if (entityName != null)
	{
	    rightParam.put("entity", entityName);
	    if ((new RightCheck()).perform(rightParam) != null)
	    {
		// on fais l'Action demander par le user si les droit son
		// trouver
		retView = module.doAction(actionName, entityName, parameter);
	    }
	    else
	    {
		return new AlertEntity(
			"Vous n'avez pas les droits pour faire cette action.");
	    }
	}
	else
	{
	    if ((new RightCheck()).perform(rightParam) != null)
	    {
		// on fais l'Action demander par le user si les droit son
		// trouver
		retView = module.doAction(actionName, parameter);
	    }
	    else
	    {
		return new AlertEntity(
			"Vous n'avez pas les droits pour faire cette action.");
	    }
	}

	return retView;
    }
}
