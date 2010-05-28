package modules.expoModule.actions;

import java.util.Hashtable;

import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.PluralAccessor;

/**
 * Action représantant le désabonnement d'un membre
 * @author Guillaume Lacasse
 */
public class Unsubscribe extends AbstractAction
{
    /**
     * Créer instance d'action
     */
    public Unsubscribe()
    {
	setDetailedDescription("Vous désabonner de l'exposition (mais nous croyons que vous ne ferez jamais ça)");
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	User currentUser = Authentication.getCurrentUser();

	Authentication.setCurrentUserName(null);

	PluralAccessor customerList = currentUser
		.getPluralAccessor("KioskCustomer");

	for (AbstractOrmEntity kioskCustomer : customerList)
	{
	    PluralAccessor invoiceList = kioskCustomer
		    .getPluralAccessor("KioskInvoice");

	    for (AbstractOrmEntity invoice : invoiceList)
	    {
		PluralAccessor invoiceItemList = invoice
			.getPluralAccessor("KioskInvoiceItem");

		for (AbstractOrmEntity invoiceItem : invoiceItemList)
		{

		    PluralAccessor zoneList = invoiceItem
			    .getPluralAccessor("Zone");
		    for (AbstractOrmEntity zone : zoneList)
		    {
			PluralAccessor wallList = zone
				.getPluralAccessor("Muret");

			for (AbstractOrmEntity wall : wallList)
			{
			    wall.delete();
			}

			zone.delete();
		    }
		}
	    }

	    // kioskCustomer.delete(); On garde le client pour les factures
	}

	currentUser.delete();

	return null;
    }
}
