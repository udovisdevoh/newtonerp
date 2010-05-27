package modules.expoModule.actions;

import java.util.Hashtable;

import modules.expoModule.entityDefinitions.KioskCustomer;
import modules.expoModule.entityDefinitions.KioskInvoice;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Action de voir ses factures
 * @author Guillaume Lacasse
 */
public class ViewMyInvoices extends AbstractAction
{
    /**
     * Créer instance d'action
     */
    public ViewMyInvoices()
    {
	setDetailedDescription("Voir vos transactions et payer");
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	User currentUser = Authentication.getCurrentUser();

	PluralAccessor customerList = currentUser
		.getPluralAccessor("KioskCustomer");

	KioskCustomer kioskCustomer = (KioskCustomer) customerList.get(0);

	KioskInvoice kioskInvoice = new KioskInvoice();

	parameters.put(kioskInvoice.getPrimaryKeyName(), "&");

	parameters.put(kioskCustomer.getForeignKeyName(), kioskCustomer
		.getPrimaryKeyValue().toString());

	ListViewerData entityList = kioskInvoice.getList(parameters);

	return entityList;
    }
}
