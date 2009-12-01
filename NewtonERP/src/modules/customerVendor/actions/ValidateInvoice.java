package modules.customerVendor.actions;

import java.util.Hashtable;

import modules.customerVendor.entityDefinitions.Invoice;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * Validates an invoice
 * 
 * @author r3hallejo
 */
public class ValidateInvoice extends AbstractAction
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public ValidateInvoice() throws Exception
    {
	super(new Invoice());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Invoice retInvoice = (Invoice) entity;

	if (retInvoice.getData("isForCustomer").equals(
		retInvoice.getData("isForSupplier")))
	{
	    // TODO : Mettre un alert message sur la facture en cours?
	}

	return null;
    }
}
