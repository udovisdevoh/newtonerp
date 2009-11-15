package modules.customerVendor.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.InvoiceLine;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Will be called by tasks. To calculate the total of the invoice
 * 
 * TODO : Add the taxes (from another action?)
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

	// We get the invoiceLines associated
	Vector<AbstractOrmEntity> invoiceLines = actionInvoice
		.getPluralAccessor("InvoiceLine");

	for (AbstractOrmEntity line : invoiceLines)
	{
	    if (totalInvoice == null)
		totalInvoice = 0.0;

	    totalInvoice += ((Double) ((InvoiceLine) line).getData("unitPrice") * (Integer) line
		    .getData("quantity"));
	}

	actionInvoice.setData("total", totalInvoice);

	Vector<String> searchCriterias = new Vector<String>();
	searchCriterias.add(actionInvoice.getPrimaryKeyName() + "='"
		+ actionInvoice.getPrimaryKeyValue() + "'");
	Orm.update(actionInvoice, searchCriterias);

	return null;
    }
}
