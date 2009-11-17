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
	Invoice actionInvoice = (Invoice) entity;

	InvoiceLine invoiceLine = new InvoiceLine();
	invoiceLine.setData(new Invoice().getForeignKeyName(), actionInvoice
		.getPrimaryKeyValue());
	Vector<AbstractOrmEntity> invoiceLines = Orm.select(invoiceLine);

	/*
	 * On fait le check pour chaque ligne de facture si jamais il y a un
	 * produit pour lequel on a pas assez d'inventaire le reste de la
	 * facture est bloquée et les produits ne sont pas mis à jour
	 */
	for (AbstractOrmEntity actionInvoiceLine : invoiceLines)
	{
	    Product product = new Product();
	    product.setData(new Product().getPrimaryKeyName(),
		    actionInvoiceLine
			    .getData(new Product().getForeignKeyName()));

	    Product myProduct = (Product) Orm.selectUnique(product);

	    if (((Integer) myProduct.getData("quantityInStock")
		    - (Integer) actionInvoiceLine.getData("quantity") < 0))
	    {
		updatable = false;
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

		myProduct2.setData("quantityInStock", (Integer) myProduct2
			.getData("quantityInStock")
			- (Integer) actionInvoiceLine1.getData("quantity"));

		Vector<String> searchCriterias = new Vector<String>();
		searchCriterias.add(myProduct2.getPrimaryKeyName() + "='"
			+ myProduct2.getPrimaryKeyValue() + "'");
		Orm.update(myProduct2, searchCriterias);
	    }
	}
	else
	{
	    System.out.println("Inventaire insuffisant");
	    /*
	     * FIXME : Does not work ListViewerData invoiceList = new
	     * Invoice().getList(); invoiceList .
	     * addAlertMessage("Impossible de mettre a jour. Manque d'inventaire"
	     * ); return invoiceList;
	     */
	}

	return new Invoice().getList();
    }
}
