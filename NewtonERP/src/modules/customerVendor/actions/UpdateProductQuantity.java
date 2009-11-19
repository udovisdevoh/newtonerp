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
import newtonERP.viewers.viewerData.ListViewerData;

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
	super(new Invoice());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Boolean updatable = true;
	Invoice actionInvoice = (Invoice) Orm.selectUnique((Invoice) entity);

	InvoiceLine invoiceLine = new InvoiceLine();
	invoiceLine.setData(new Invoice().getForeignKeyName(), actionInvoice
		.getPrimaryKeyValue());
	Vector<AbstractOrmEntity> invoiceLines = Orm.select(invoiceLine);

	if (actionInvoice.getData("isForCustomer").equals(true)
		&& actionInvoice.getData("isForSupplier").equals(true))
	{
	    ListViewerData invoiceList = new Invoice().getList();
	    invoiceList
		    .addAlertMessage("Facture invalide. Veuillez mettre le type de commerçant");
	    return invoiceList;
	}

	if (actionInvoice.getData("isForCustomer").equals(true))
	{
	    for (AbstractOrmEntity actionInvoiceLine : invoiceLines)
	    {
		Product product = new Product();
		product.setData(new Product().getPrimaryKeyName(),
			actionInvoiceLine.getData(new Product()
				.getForeignKeyName()));

		Product myProduct = (Product) Orm.selectUnique(product);

		if (((Integer) myProduct.getData("quantityInStock")
			- (Integer) actionInvoiceLine.getData("quantity") < 0))
		{
		    updatable = false;
		}
	    }
	}

	if (updatable)
	{
	    for (AbstractOrmEntity actionInvoiceLine1 : invoiceLines)
	    {
		Product product2 = new Product();
		product2.setData(new Product().getPrimaryKeyName(),
			actionInvoiceLine1.getData(new Product()
				.getForeignKeyName()));

		Product myProduct2 = (Product) Orm.selectUnique(product2);

		if (actionInvoice.getData("isForCustomer").equals(true))
		{
		    myProduct2.setData("quantityInStock", (Integer) myProduct2
			    .getData("quantityInStock")
			    - (Integer) actionInvoiceLine1.getData("quantity"));
		}
		else if (actionInvoice.getData("isForSupplier").equals(true))
		    myProduct2.setData("quantityInStock", (Integer) myProduct2
			    .getData("quantityInStock")
			    + (Integer) actionInvoiceLine1.getData("quantity"));

		myProduct2.save();
	    }
	}
	else
	{
	    ListViewerData invoiceList = new Invoice().getList();
	    invoiceList
		    .addAlertMessage("Impossible de mettre a jour. Manque d'inventaire");
	    return invoiceList;
	}

	return new Invoice().getList();
    }
}
