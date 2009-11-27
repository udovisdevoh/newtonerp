/**
 * 
 */
package newtonERP.module;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.exception.ActionNotFoundException;

/**
 * @author CloutierJo
 * 
 */
public class BaseAction extends AbstractAction
{

    private String actionName;
    private AbstractOrmEntity entity;
    static Vector<String> validActionName;

    {
	validActionName = new Vector<String>();
	validActionName.add("New");
	validActionName.add("Edit");
	validActionName.add("Delete");
	validActionName.add("Get");
	validActionName.add("GetList");
    }

    /**
     * @param actionName nom de l'Action (Get, Edit New Delete)
     * @param entity l'entity a utiliser
     * @throws ActionNotFoundException si l'actionName fournie n'Est pas un nom
     *             d'Action valide
     */
    public BaseAction(String actionName, AbstractOrmEntity entity)
	    throws ActionNotFoundException
    {
	if (!validActionName.contains(actionName))
	    throw new ActionNotFoundException("BaseAction:" + actionName);
	this.actionName = actionName;
	this.entity = entity;
    }

    /**
     * @return nom de l'action de base (override le nom de la classe car
     *         BaseAction n'a pas de sens)
     */
    @Override
    public String getSystemName()
    {
	return actionName;
    }

    /**
     * @param actionName the actionName to set
     */
    public void setActionName(String actionName)
    {
	this.actionName = actionName;
    }

    /**
     * @return the entity
     */
    public AbstractOrmEntity getEntity()
    {
	return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(AbstractOrmEntity entity)
    {
	this.entity = entity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * newtonERP.module.AbstractAction#doAction(newtonERP.module.AbstractEntity,
     * java.util.Hashtable)
     */
    public AbstractEntity doAction(
	    @SuppressWarnings("hiding") AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	throw new Exception(
		"doAction ne doit pas etre appelé sur un baseAction");

    }

    /**
     * @param name nom de l'action
     * @return true si le nom est un nom de base action
     */
    public static boolean isNameMatchesBaseAction(String name)
    {
	if (name.equals("Get") || name.equals("Edit") || name.equals("GetList")
		|| name.equals("Delete") || name.equals("New"))
	    return true;
	return false;
    }
}
