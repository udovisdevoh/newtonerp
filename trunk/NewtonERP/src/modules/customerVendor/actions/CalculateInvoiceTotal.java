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
	Double totalInvoice = 0.0;
	Invoice actionInvoice = (Invoice) entity;

	Vector<AbstractOrmEntity> invoiceLines = actionInvoice
		.getPluralAccessor("InvoiceLine");

	for (AbstractOrmEntity line : invoiceLines)
	{
	    totalInvoice += (Double) ((InvoiceLine) line).getData("unitPrice");
	}

	actionInvoice.setData("total", totalInvoice);

	// TODO : Is this the right way to do it?
	Orm.update((AbstractOrmEntity) entity, null);

	return null;
    }
}
