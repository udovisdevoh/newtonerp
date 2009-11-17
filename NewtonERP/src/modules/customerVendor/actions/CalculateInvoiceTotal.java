package modules.customerVendor.actions;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.InvoiceLine;
import modules.customerVendor.entityDefinitions.InvoiceTaxLine;
import modules.customerVendor.entityDefinitions.Tax;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Will be called by tasks. To calculate the total of the invoice
 * 
 * @author r3hallejo
 */
public class CalculateInvoiceTotal extends AbstractAction
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public CalculateInvoiceTotal() throws Exception
    {
	super(new Invoice());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Vector<Tax> taxContainer = new Vector<Tax>();
	Invoice actionInvoice = (Invoice) entity;
	Double totalInvoice = (Double) entity.getData("total");
	Double taxTotal = (Double) entity.getData("taxTotal");

	taxTotal = 0.0;
	totalInvoice = 0.0;

	// INVOICE LINES
	InvoiceLine invoiceLine = new InvoiceLine();
	invoiceLine.setData(new Invoice().getForeignKeyName(), actionInvoice
		.getPrimaryKeyValue());
	Vector<AbstractOrmEntity> invoiceLines = Orm.select(invoiceLine);

	for (AbstractOrmEntity line : invoiceLines)
	{
	    totalInvoice += ((Double) ((InvoiceLine) line).getData("unitPrice") * (Integer) line
		    .getData("quantity"));
	}

	actionInvoice.setData("total", totalInvoice);

	InvoiceTaxLine invoiceTaxLine = new InvoiceTaxLine();
	invoiceTaxLine.setData(new Invoice().getForeignKeyName(), actionInvoice
		.getPrimaryKeyValue());
	Vector<AbstractOrmEntity> invoiceTaxLines = Orm.select(invoiceTaxLine);

	// TAX LINES
	for (AbstractOrmEntity tax : invoiceTaxLines)
	{
	    Tax taxe = new Tax();
	    taxe.setData(new Tax().getPrimaryKeyName(), tax.getData(new Tax()
		    .getForeignKeyName()));

	    Tax myTax = (Tax) Orm.selectUnique(taxe);

	    taxContainer.add(myTax);
	}

	/*
	 * On swap le fédéral et provincial si il y a lieu ainsi on calcule
	 * toujours la taxe provinciale avant la fédérale
	 */
	for (int i = 0; i < taxContainer.size(); i++)
	{
	    if (taxContainer.get(i).getData("isStateTax").equals(true))
		Collections.swap(taxContainer, i, 1);
	}

	for (Tax myTax : taxContainer)
	{
	    taxTotal += totalInvoice * ((Double) myTax.getData("value") / 100);
	    totalInvoice += taxTotal;
	}

	actionInvoice.setData("taxTotal", taxTotal);

	Vector<String> searchCriterias = new Vector<String>();
	searchCriterias.add(actionInvoice.getPrimaryKeyName() + "='"
		+ actionInvoice.getPrimaryKeyValue() + "'");
	Orm.update(actionInvoice, searchCriterias);

	return new Invoice().getList();
    }
}
