/**
 * 
 */
package newtonERP.module;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.actions.ActionableEntity;
import newtonERP.module.actions.IAction;
import newtonERP.module.moduleGetters.IModuleGetter;
import newtonERP.orm.Orm;
import newtonERP.orm.Ormizable;
import newtonERP.viewers.Viewable;

/**
 * @author Pascal Lemay
 */
public abstract class Module
{
    // TODO: changer object par Orm des que possible
    protected static Orm orm;// reference a l orm

    // Sert a conserver les definitions de tables
    // Genre d'exemple d'une table.
    protected Vector<Ormizable> definitionEntityList;

    // Sert a stocker les actions pouvant etre appelees
    protected Hashtable<String, IAction> actionList;

    protected Hashtable<String, IModuleGetter> moduleGetterList;

    protected final void setDefaultAction(String actionName)
	    throws ModuleException
    {
	IAction defaultAction = getAction(actionName);
	actionList.put("index", defaultAction);
    }

    protected final void setDefaultModuleGetter(String moduleGetterName)
	    throws ModuleException
    {
	IModuleGetter defaultModuleGetter = moduleGetterList
		.get(moduleGetterName);

	if (defaultModuleGetter == null)
	    throw new ModuleException("ModuleGetter " + moduleGetterName
		    + " introuvable");

	moduleGetterList.put("defaultModuleGetter", defaultModuleGetter);
    }

    /**
     * 
     */
    public Module()
    {
	definitionEntityList = new Vector<Ormizable>();
	actionList = new Hashtable<String, IAction>();
	moduleGetterList = new Hashtable<String, IModuleGetter>();
    }

    /**
     * @return the Vector entities
     */
    public final Vector<Ormizable> getDefinitionEntityList()
    {
	return definitionEntityList;
    }

    /**
     * @return the actions HashTable
     */
    public final Hashtable<String, IAction> getActionList()
    {
	return actionList;
    }

    /**
     * @paramactionName=nom de l action
     * @return action de ce nom contenue dans le HashTable
     */
    public final IAction getAction(String actionName) throws ModuleException
    {
	IAction action = null;
	try
	{
	    action = actionList.get(actionName);
	} catch (NullPointerException e)
	{
	    throw new ModuleException("Action: " + actionName + " introuvable");
	}

	if (action == null)
	    throw new ModuleException("Action: " + actionName + " introuvable");

	return action;
    }

    /**
     * @param actionName=Nom de l'action que l'utilisateur veut faire
     * @param moduleGetterName=Nom du module getter permetant d'obtenir l'entité
     *            produite par le module (cette entité servira de paramêtre à
     *            l'action
     * @param parameters=Paramètres de l'action devant être accomplie, exemple,
     *            contenu d'un email
     * @return Entité viewable pour l'output du résultat
     * @throws ModuleException
     */
    public final Viewable doAction(String actionName, String moduleGetterName,
	    Hashtable<String, String> parameters) throws ModuleException
    {
	IAction action = getAction(actionName);

	if (moduleGetterName == null)
	    throw new ModuleException("Aucun moduleGetter spécifié");

	IModuleGetter moduleGetter = moduleGetterList.get(moduleGetterName);

	if (moduleGetter == null)
	    throw new ModuleException("ModuleGetter " + moduleGetterName
		    + " introuvable");

	ActionableEntity entity = moduleGetter
		.getEntityFromParameters(parameters);

	return action.perform(entity, parameters);
    }
}
