package modules.customerVendor.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.customerVendor.entityDefinitions.Invoice;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Will be called by tasks. Used to calculate total + taxes
 * 
 * @author r3hallejo
 */
public class CalculateTaxes extends AbstractAction
{

    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Invoice actionInvoice = (Invoice) entity;
	Double totalInvoice = (Double) actionInvoice.getData("total");

	Vector<AbstractOrmEntity> invoiceTaxLines = actionInvoice
		.getPluralAccessor("InvoiceTaxLine");

	// For each tax
	for (AbstractOrmEntity tax : invoiceTaxLines)
	{
	    totalInvoice += totalInvoice * (Double) tax.getData("value");
	}

	actionInvoice.setData("total", totalInvoice);

	Orm.updateUnique((AbstractOrmEntity) entity, actionInvoice);

	return null;
    }

}
