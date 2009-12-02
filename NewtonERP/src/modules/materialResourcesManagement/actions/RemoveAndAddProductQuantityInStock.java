package modules.materialResourcesManagement.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.InvoiceLine;
import modules.materialResourcesManagement.entityDefinitions.Product;
import modules.materialResourcesManagement.entityDefinitions.Shipping;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Action that removes product quantity from reserved stock and adds it in
 * quantity
 * 
 * @author r3hallejo
 */
public class RemoveAndAddProductQuantityInStock extends AbstractAction
{
    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Shipping retShipping = (Shipping) entity;

	Invoice searchInvoice = new Invoice();
	searchInvoice.setData(searchInvoice.getPrimaryKeyName(), retShipping
		.getData(new Invoice().getForeignKeyName()));
	Invoice retInvoice = (Invoice) Orm.selectUnique(searchInvoice);

	InvoiceLine searchInvoiceLine = new InvoiceLine();
	searchInvoiceLine.setData(new Invoice().getForeignKeyName(), retInvoice
		.getPrimaryKeyValue());

	Vector<AbstractOrmEntity> invoiceLines = Orm.select(searchInvoiceLine);

	for (AbstractOrmEntity line : invoiceLines)
	{
	    // The related product
	    Product searchProduct = new Product();
	    searchProduct.setData(searchProduct.getPrimaryKeyName(), line
		    .getData(new Product().getForeignKeyName()));
	    Product retProduct = (Product) Orm.selectUnique(searchProduct);

	    // We remove the line quantity from the reservedStock of the product
	    // and adds it to the quantity in stock
	    retProduct.setData("reservedStock", (Integer) retProduct
		    .getData("reservedStock")
		    - (Integer) line.getData("quantity"));

	    retProduct.setData("quantityInStock", (Integer) retProduct
		    .getData("quantityInStock")
		    + (Integer) line.getData("quantity"));

	    retProduct.save();
	}

	return null;
    }
}
