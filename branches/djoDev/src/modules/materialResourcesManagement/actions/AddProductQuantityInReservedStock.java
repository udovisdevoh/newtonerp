package modules.materialResourcesManagement.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.InvoiceLine;
import modules.materialResourcesManagement.entityDefinitions.Product;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Takes the product quantities in the invoice, puts them in the reserved stock
 * and removes them from the quantity in stock. In the event of a delete or if a
 * client decides to cancel his invoice we will just take the reserved stock and
 * put it in the quantityInStock
 * 
 * @author r3hallejo
 */
public class AddProductQuantityInReservedStock extends AbstractAction
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public AddProductQuantityInReservedStock() throws Exception
    {
	super(new Invoice());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	// The invoice
	Invoice retInvoice = (Invoice) entity;

	// The related invoiceLines
	InvoiceLine searchInvoiceLine = new InvoiceLine();
	searchInvoiceLine.setData(new Invoice().getForeignKeyName(), retInvoice
		.getPrimaryKeyValue());
	Vector<AbstractOrmEntity> invoiceLines = Orm.select(searchInvoiceLine);

	if (isUpdatable(invoiceLines))
	{
	    for (AbstractOrmEntity line : invoiceLines)
	    {
		// Get the related product to that invoiceLine
		Product searchProduct = new Product();
		searchProduct.setData(searchProduct.getPrimaryKeyName(), line
			.getData(new Product().getForeignKeyName()));
		Product retProduct = (Product) Orm.selectUnique(searchProduct);

		// Now we put the quantity from the invoiceLine into the
		// reserved
		// stock of the product and removes that same quantity from the
		// quantityInStock of that product
		retProduct.setData("quantityInStock", (Integer) retProduct
			.getData("quantityInStock")
			- (Integer) line.getData("quantity"));

		retProduct.setData("reservedStock", (Integer) retProduct
			.getData("reservedStock")
			+ (Integer) line.getData("quantity"));

		retProduct.save();
	    }
	}
	else
	{
	    // TODO : Put an alert message on the shipping?
	}

	return null;
    }

    private boolean isUpdatable(Vector<AbstractOrmEntity> invoiceLines)
	    throws Exception
    {
	boolean isUpdatable = true;

	for (AbstractOrmEntity line : invoiceLines)
	{
	    Product searchProduct = new Product();
	    searchProduct.setData(searchProduct.getPrimaryKeyName(), line
		    .getData(new Product().getForeignKeyName()));
	    Product retProduct = (Product) Orm.selectUnique(searchProduct);

	    if ((Integer) retProduct.getData("quantityInStock")
		    - (Integer) line.getData("quantity") < 0)
		isUpdatable = false;
	}

	return isUpdatable;
    }
}
