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
 * The title says it all...
 * 
 * @author r3hallejo
 */
public class RemoveProductQuantityFromReservedStock extends AbstractAction
{
	/**
	 * Default constructor
	 * 
	 * @throws Exception a general exception
	 */
	public RemoveProductQuantityFromReservedStock() throws Exception
	{
		super(new Invoice());
	}

	@Override
	public AbstractEntity doAction(AbstractEntity entity,
			Hashtable<String, String> parameters) throws Exception
	{
		// The retreived invoice
		Invoice retInvoice = (Invoice) entity;

		// The related invoiceLines
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

			// Now we do productReservedStock - lineQuantity
			retProduct.setData("reservedStock", (Integer) retProduct
					.getData("reservedStock")
					- (Integer) line.getData("quantity"));

			retProduct.save();
		}

		return null;
	}
}
