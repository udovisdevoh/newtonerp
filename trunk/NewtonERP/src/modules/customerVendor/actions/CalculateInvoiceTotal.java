package modules.customerVendor.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.InvoiceLine;
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
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Invoice actionInvoice = (Invoice) entity;
	Double totalInvoice = (Double) entity.getData("total");
	Double taxTotal = (Double) entity.getData("taxTotal");

	taxTotal = 0.0;
	totalInvoice = 0.0;

	// We get the invoiceLines associated
	Vector<AbstractOrmEntity> invoiceLines = actionInvoice
		.getPluralAccessor("InvoiceLine");

	for (AbstractOrmEntity line : invoiceLines)
	{
	    totalInvoice += ((Double) ((InvoiceLine) line).getData("unitPrice") * (Integer) line
		    .getData("quantity"));
	}

	actionInvoice.setData("total", totalInvoice);

	Vector<AbstractOrmEntity> invoiceTaxLines = actionInvoice
		.getPluralAccessor("InvoiceTaxLine");

	// For each tax
	for (AbstractOrmEntity tax : invoiceTaxLines)
	{
	    Tax taxe = new Tax();
	    taxe.setData(new Tax().getPrimaryKeyName(), tax
		    .getPrimaryKeyValue());

	    Tax myTax = (Tax) Orm.selectUnique(taxe);

	    taxTotal += totalInvoice * ((Double) myTax.getData("value") / 100);
	}

	actionInvoice.setData("taxTotal", taxTotal);

	Vector<String> searchCriterias = new Vector<String>();
	searchCriterias.add(actionInvoice.getPrimaryKeyName() + "='"
		+ actionInvoice.getPrimaryKeyValue() + "'");
	Orm.update(actionInvoice, searchCriterias);

	return null;
    }
}
