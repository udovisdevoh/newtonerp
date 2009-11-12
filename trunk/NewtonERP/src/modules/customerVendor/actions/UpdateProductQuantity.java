package modules.customerVendor.actions;

import java.util.Hashtable;

import modules.customerVendor.entityDefinitions.InvoiceLine;
import modules.materialResourcesManagement.entityDefinitions.Product;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;

/**
 * Will be called by tasks. Used to update product quantities in database each
 * time a new InvoiceLine is produced so the quantities are "reserved" for that
 * invoice. This ensures there is always enough products in the warehouse
 * 
 * @author r3hallejo
 */
public class UpdateProductQuantity extends AbstractAction
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public UpdateProductQuantity() throws Exception
    {
	super(new InvoiceLine());
    }

    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	// InvoiceLine that has just been created
	InvoiceLine actionInvoiceLine = (InvoiceLine) entity;

	// The product associated to that invoiceLine
	Product productInvoiceLine = (Product) actionInvoiceLine
		.getSingleAccessor(new Product().getForeignKeyName());

	// We remove the quantity from this product. (quantityInStock -
	// invoiceLineQuantity)
	productInvoiceLine.setData("quantityInStock",
		(Integer) productInvoiceLine.getData("quantityInStock")
			- (Integer) actionInvoiceLine.getData("quantity"));

	Orm.update(productInvoiceLine, null);

	return null;
    }

}
