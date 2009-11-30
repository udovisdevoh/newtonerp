package modules.materialResourcesManagement.actions;

import java.util.Hashtable;

import modules.customerVendor.actions.UpdateProductQuantityInReservedStock;
import modules.customerVendor.entityDefinitions.Invoice;
import modules.materialResourcesManagement.entityDefinitions.Shipping;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;

/**
 * Action called at the opening of a shipping which updates the product
 * quantities in database in the reserved stock and at the closing of the
 * shipping it removes the product quantity from the reserved stock and from the
 * quantity in stock
 * 
 * @author r3hallejo
 */
public class OpenShipping extends AbstractAction
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public OpenShipping() throws Exception
    {
	super(new Shipping());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	// We retreive the shipping
	Shipping searchShipping = (Shipping) entity;
	Shipping retShipping = (Shipping) Orm.selectUnique(searchShipping);

	// We retreive the invoice related to the shipping
	Invoice searchInvoice = new Invoice();
	searchInvoice.setData(searchInvoice.getPrimaryKeyName(), retShipping
		.getData(new Invoice().getForeignKeyName()));
	Invoice retInvoice = (Invoice) Orm.selectUnique(searchInvoice);

	new UpdateProductQuantityInReservedStock().doAction(retInvoice, null);

	return null;
    }
}
