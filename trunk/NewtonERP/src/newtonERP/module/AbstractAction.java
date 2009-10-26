package newtonERP.module;

import java.util.Hashtable;

/**
 * @author r3lacasgu cloutierJo
 * 
 *         Class representing an abstract action
 */
public abstract class AbstractAction
{
    private AbstractEntity entityUsable;

    protected AbstractAction()
    {
	entityUsable = null;
    }

    /**
     * Default constructor for the action
     * 
     * @param entityUsable le type d'entité qui sera utiliser par la methode
     */
    protected AbstractAction(AbstractEntity entityUsable)
    {
	this.entityUsable = entityUsable;
    }

    /**
     * Abstract method that will be used to perofrm the actions FIXME : Why is
     * it throwing a ModuleException when it shooting this exception?
     * 
     * @param parameters list de parametre utilisable par l'action, une partie
     *            d'entre eux sera transforme en entite
     * @return l,entite resultante de l'action
     * @throws Exception remonte
     */
    public AbstractEntity perform(Hashtable<String, String> parameters)
	    throws Exception
    {

	AbstractEntity entity = null;
	try
	{
	    entity = entityUsable.getClass().newInstance();
	    entity.getFields().setFromHashTable(parameters);
	} catch (NullPointerException e)
	{
	    // ne rien faire si cette exception est lancé, laissé le bloc
	    // présent
	}
	return doAction(entity, parameters);
    }

    /**
     * Abstract method that will be reused by all the actions
     * 
     * @param entity entite a utiliser a l'intérieur du doAction
     * @param parameters list de parametre autre que l'entite
     * @return l'entite resultante
     * @throws Exception
     */
    protected abstract AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception;

    /**
     * @return the entityUsable
     */
    public AbstractEntity getEntityUsable()
    {
	return entityUsable;
    }

}