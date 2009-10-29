/**
 * 
 */
package newtonERP.module;

import java.util.Hashtable;

/**
 * @author djo
 * 
 */
public class BaseAction extends AbstractAction
{

    private String actionName;
    private AbstractOrmEntity entity;

    /**
     * @param actionName nom de l'Action (Get, Edit New Delete)
     * @param entity l'entity a utiliser
     */
    public BaseAction(String actionName, AbstractOrmEntity entity)
    {
	this.actionName = actionName;
	this.entity = entity;
    }

    /**
     * @return the actionName
     */
    public String getActionName()
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
    protected AbstractEntity doAction(
	    @SuppressWarnings("hiding") AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	throw new Exception(
		"doAction ne doit pas etre appelé sur un baseAction");

    }

}
