package modules.materialResourcesManagement.actions;

import java.util.Hashtable;

import modules.materialResourcesManagement.entityDefinitions.Shipping;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * Action to cancel a shipping
 * 
 * @author r3hallejo
 */
public class CancelShipping extends AbstractAction
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public CancelShipping() throws Exception
    {
	super(new Shipping());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	return null;
    }
}
