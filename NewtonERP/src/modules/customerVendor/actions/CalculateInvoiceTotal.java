package modules.customerVendor.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.customerVendor.entityDefinitions.InvoiceLine;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;

/**
 * To calculate the total of the invoice
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
	super(new InvoiceLine()); // Travaille avec les invoices line
    }

    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Double totalInvoice = 0.0;
	AbstractOrmEntity invoice = (AbstractOrmEntity) entity;

	Vector<AbstractOrmEntity> invoiceLines = invoice
		.getPluralAccessor("invoiceLineID");

	for (AbstractOrmEntity line : invoiceLines)
	{
	    totalInvoice += (Double) ((InvoiceLine) line).getData("unitPrice");
	}

	// TODO : Update invoice (celle passé en parametre)

	return null;
    }
}
