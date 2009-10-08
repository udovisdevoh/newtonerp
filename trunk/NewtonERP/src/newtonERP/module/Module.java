/**
 * 
 */
package newtonERP.module;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.orm.Ormizable;

/**
 * @author Pascal Lemay
 */
public abstract class Module
{
    // Sert a conserver les definitions de tables
    // Genre d'exemple d'une table.
    protected Vector<Ormizable> definitionEntityList;

    // Sert a stocker les actions pouvant etre appelees
    protected Hashtable<String, AbstractAction> actionList;

    /**
     * constructeur
     */
    public Module()
    {
	definitionEntityList = new Vector<Ormizable>();
	actionList = new Hashtable<String, AbstractAction>();
    }

    protected final void setDefaultAction(String actionName)
    {
	AbstractAction defaultAction;
	try
	{
	    defaultAction = getAction(actionName);
	    actionList.put("default", defaultAction);
	} catch (ModuleException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    protected final void addAction(AbstractAction action)
    {
	actionList.put(action.getClass().getSimpleName(), action);
    }

    protected final void addAction(AbstractAction action, boolean isDefault)
    {
	addAction(action);
	if (isDefault)
	    setDefaultAction(action.getClass().getSimpleName());
    }

    protected final void addDefinitinEntity(Ormizable definitinEntity)
    {
	definitionEntityList.add(definitinEntity);
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
    public final Hashtable<String, AbstractAction> getActionList()
    {
	return actionList;
    }

    /**
     * @param actionName nom de l action
     * @return action de ce nom contenue dans le HashTable
     * @throws ModuleException si l'action n'Existe pas
     */
    public final AbstractAction getAction(String actionName)
	    throws ModuleException
    {
	AbstractAction action = null;
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
     * @param actionName Nom de l'action que l'utilisateur veut faire
     * @param moduleGetterName Nom du module getter permetant d'obtenir l'entité
     *            produite par le module (cette entité servira de paramêtre à
     *            l'action
     * @param parameters Paramètres de l'action devant être accomplie, exemple,
     *            contenu d'un email
     * @return Entité viewable pour l'output du résultat
     * @throws ModuleException voir le message...
     */
    public final AbstractEntity doAction(String actionName,
	    Hashtable<String, String> parameters) throws ModuleException
    {
	AbstractAction action = getAction(actionName);
	return action.perform(parameters);
    }
}
