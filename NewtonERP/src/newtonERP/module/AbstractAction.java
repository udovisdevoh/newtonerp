package newtonERP.module;

import java.util.Hashtable;

/**
 * @author r3lacasgu cloutierJo
 * 
 */
public abstract class AbstractAction
{
    private AbstractEntity entityUsable;

    /**
     * @param entity
     * @param parameters
     * @return
     */
    public AbstractEntity perform(Hashtable<String, String> parameters)
    {
	// AbstractEntity entity = getEntityFromParameters(parameters);
	return null;
	// TODO: heu a faire
    }

    public abstract AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters);

    /**
     * @return the entityUsable
     */
    public AbstractEntity getEntityUsable()
    {
	return entityUsable;
    }

    /**
     * @param entityUsable the entityUsable to set
     */
    public void setEntityUsable(AbstractEntity entityUsable)
    {
	this.entityUsable = entityUsable;
    }

}