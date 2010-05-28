package modules.customerVendor.actions;

import java.util.Hashtable;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.InvoiceTaxLine;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;

/**
 * Updates the invoice total from a tax line
 * 
 * @author r3hallejo
 */
public class GetAndCalculateAssociatedInvoiceFromTax extends AbstractAction
{

	/**
	 * Default constructor
	 * 
	 * @throws Exception a general excetpion
	 */
	public GetAndCalculateAssociatedInvoiceFromTax() throws Exception
	{
		super(new InvoiceTaxLine());
	}

	@Override
	public AbstractEntity doAction(AbstractEntity entity,
			Hashtable<String, String> parameters) throws Exception
	{
		InvoiceTaxLine actionInvoiceTaxLine = (InvoiceTaxLine) entity;
		Invoice searchInvoice = new Invoice();

		searchInvoice
				.setData(searchInvoice.getPrimaryKeyName(),
						actionInvoiceTaxLine.getData(new Invoice()
								.getForeignKeyName()));

		Invoice foundInvoice = (Invoice) Orm.selectUnique(searchInvoice);

		new CalculateInvoiceTotal().doAction(foundInvoice, null);

		return null;
	}

}
