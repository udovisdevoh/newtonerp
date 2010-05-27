package modules.expoModule.actions;

import java.util.Hashtable;

import modules.expoModule.entityDefinitions.KioskInvoice;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.PluralAccessor;

/**
 * Action de payer une facture et d'effectuer les changements concernés par
 * l'achat
 * @author Guillaume Lacasse
 */
public class PayInvoice extends AbstractAction
{
    /**
     * @throws Exception is ça fail
     */
    public PayInvoice() throws Exception
    {
	super(new KioskInvoice());
	setDetailedDescription("Effectuer le paiement d'une");
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	KioskInvoice invoice = (KioskInvoice) entity;
	invoice = (KioskInvoice) invoice.get().get(0);
	invoice.setData("isPaid", true);
	invoice.save();

	activateZones(invoice);

	return invoice.getUI(parameters);
    }

    private void activateZones(KioskInvoice invoice) throws Exception
    {
	PluralAccessor invoiceItemList = invoice
		.getPluralAccessor("KioskInvoiceItem");

	for (AbstractOrmEntity invoiceItem : invoiceItemList)
	{
	    PluralAccessor zoneList = invoiceItem.getPluralAccessor("Zone");

	    for (AbstractOrmEntity zone : zoneList)
	    {
		zone.setData("isActive", true);
		zone.save();
	    }
	}
    }
}
