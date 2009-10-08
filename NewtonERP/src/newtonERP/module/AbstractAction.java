package newtonERP.module;

import java.util.Hashtable;

/**
 * @author r3lacasgu cloutierJo
 * 
 */
public abstract class AbstractAction
{
    private AbstractEntity entityUsable;

    public AbstractAction()
    {
	entityUsable = null;
    }

    /**
     * @param entityUsable le type d'entité qui sera utiliser par la methode
     */
    public AbstractAction(AbstractEntity entityUsable)
    {
	this.entityUsable = entityUsable;
    }

    /**
     * @param parameters list de parametre utilisable par l'action, une partie
     *            d'entre eux sera transforme en entite
     * @return l,entite resultante de l'action
     * @throws ModuleException
     */
    public AbstractEntity perform(Hashtable<String, String> parameters)
	    throws ModuleException
    {

	AbstractEntity entity = null;
	try
	{
	    entity = entityUsable.getClass().newInstance();
	    entity.setEntityFromHashTable(parameters);
	} catch (NullPointerException e)
	{
	    // TODO: handle exception
	} catch (Exception e)
	{
	    e.printStackTrace();
	}
	return doAction(entity, parameters);
    }

    /**
     * @param entity entite a utiliser a l'intérieur du doAction
     * @param parameters list de parametre autre que l'entite
     * @return l'entite resultante
     */
    public abstract AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters);

}