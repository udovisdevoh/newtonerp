package modules.materialResourcesManagement.actions;

import java.util.Hashtable;

import modules.materialResourcesManagement.entityDefinitions.Shipping;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * Closes a shipping by remove the quantities from the stock of the warehouse
 * 
 * @author r3hallejo
 */
public class CloseShipping extends AbstractAction
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public CloseShipping() throws Exception
    {
	super(new Shipping());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	// TODO : To be completed
	return null;
    }
}
