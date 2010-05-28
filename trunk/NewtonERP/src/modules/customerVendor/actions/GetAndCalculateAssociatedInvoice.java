package modules.customerVendor.actions;

import java.util.Hashtable;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.InvoiceLine;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;

/**
 * Used on an invoiceLine to calculate only the total of the specified Invoice
 * 
 * @author r3hallejo
 */
public class GetAndCalculateAssociatedInvoice extends AbstractAction
{
	/**
	 * Default constructor
	 * 
	 * @throws Exception a general exception
	 */
	public GetAndCalculateAssociatedInvoice() throws Exception
	{
		super(new InvoiceLine());
	}

	@Override
	public AbstractEntity doAction(AbstractEntity entity,
			Hashtable<String, String> parameters) throws Exception
	{
		InvoiceLine actionInvoiceLine = (InvoiceLine) entity;
		Invoice searchInvoice = new Invoice();

		searchInvoice.setData(searchInvoice.getPrimaryKeyName(),
				actionInvoiceLine.getData(new Invoice().getForeignKeyName()));

		Invoice foundInvoice = (Invoice) Orm.selectUnique(searchInvoice);

		new CalculateInvoiceTotal().doAction(foundInvoice, null);

		return null;
	}

}
