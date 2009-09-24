/**
 * 
 */
package newtonERP.module;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.ActionableEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.Ormizable;

/**
 * @author Pascal Lemay
 * 
 */
public abstract class Module
{
    // TODO: changer object par Orm des que possible
    protected static Orm orm;// reference a l orm

    // Sert à conserver les definitions de tables
    // Genre d'exemple d'une table.
    private Vector<Ormizable> tableDefinitionList;

    // Sert à stocker les actions pouvant être appelées
    private Hashtable<String, IAction> actionList;

    /**
     * @return the Vector entities
     */
    public Vector<Ormizable> getTableDefinitionList()
    {
	return tableDefinitionList;
    }

    /**
     * @return the actions HashTable
     */
    public Hashtable<String, IAction> getActionListAndNames()
    {
	return actionList;
    }

    /**
     * @paramactionName=nom de l action
     * @return action de ce nom contenue dans le HashTable
     */
    public IAction getAction(String actionName) throws ModuleException
    {
	IAction action = null;
	try
	{
	    action = actionList.get(actionName);
	} catch (Exception e)
	{
	    throw new ModuleException();
	}

	if (action == null)
	    throw new ModuleException();

	return action;
    }

    /**
     * 
     * @param parameters
     *            =parametre d'execution dans le module concret
     * @return entite actionable fournie par le module concret
     */
    public abstract ActionableEntity getEntityFromParameters(
	    Hashtable<String, String> parameters);

    /**
     * @call l action et retourne le resultat de l action param action=nom de l
     *       action param parametres=liste de parametres (cle,parametre)
     */
    public IEntity doAction(String actionName,
	    Hashtable<String, String> parameters) throws ModuleException
    {
	IAction action = getAction(actionName);
	ActionableEntity entity = getEntityFromParameters(parameters);
	action.perform(entity, parameters);
	return entity;
    }
}
