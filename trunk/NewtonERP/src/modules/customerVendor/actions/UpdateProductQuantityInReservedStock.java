package modules.customerVendor.actions;

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
public class UpdateProductQuantityInReservedStock extends AbstractAction
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public UpdateProductQuantityInReservedStock() throws Exception
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

	for (AbstractOrmEntity line : invoiceLines)
	{
	    // Get the related product to that invoiceLine
	    Product searchProduct = new Product();
	    searchProduct.setData(searchProduct.getPrimaryKeyName(), line
		    .getData(new Product().getForeignKeyName()));
	    Product retProduct = (Product) Orm.selectUnique(searchProduct);

	    // Now we put the quantity from the invoiceLine into the reserved
	    // stock of the product and removes that same quantity from the
	    // quantityInStock of that product
	    retProduct.setData("quantityInStock", (Integer) retProduct
		    .getData("quantityInStock")
		    - (Integer) line.getData("quantity"));

	    retProduct.setData("reservedStock", (Integer) retProduct
		    .getData("reservedStock")
		    + (Integer) line.getData("quantity"));
	}

	return null;
    }
}
