package modules.expoModule.actions;

import java.util.Hashtable;

import modules.expoModule.entityDefinitions.KioskCustomer;
import modules.expoModule.entityDefinitions.KioskInvoice;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.type.FieldBool;
import newtonERP.viewers.viewerData.BaseViewerData;
import newtonERP.viewers.viewerData.PromptViewerData;

/**
 * Action de modifier les transactions qui ne sont pas encore payées
 * @author Guillaume Lacasse
 */
public class EditShoppingCart extends AbstractAction
{
    /**
     * Créer instance d'action
     */
    public EditShoppingCart()
    {
	setDetailedDescription("Acheter des produits");
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	KioskInvoice currentActiveTransaction = getCurrentActiveTransaction();

	BaseViewerData baseViewerData = currentActiveTransaction
		.editUI(parameters);

	PromptViewerData promptViewerData = (PromptViewerData) baseViewerData;

	promptViewerData.getData().getFields().getField("isPaid").setReadOnly(
		true);

	promptViewerData.getData().getFields().getField("kioskCustomerID")
		.setReadOnly(true);

	promptViewerData.setTitle("Panier d'achat");

	return baseViewerData;
    }

    private KioskInvoice getCurrentActiveTransaction() throws Exception
    {
	User currentUser = Authentication.getCurrentUser();

	PluralAccessor customerList = currentUser
		.getPluralAccessor("KioskCustomer");

	KioskCustomer kioskCustomer = (KioskCustomer) customerList.get(0);

	PluralAccessor invoiceList = kioskCustomer
		.getPluralAccessor("KioskInvoice");

	KioskInvoice selectedInvoice = null;
	for (AbstractOrmEntity invoice : invoiceList)
	{
	    FieldBool isPaid = (FieldBool) invoice.getFields().getField(
		    "isPaid");
	    if (isPaid.getData() == null || isPaid.getData() != true)
	    {
		selectedInvoice = (KioskInvoice) invoice;
	    }
	}

	if (selectedInvoice == null)
	{
	    selectedInvoice = new KioskInvoice();
	    selectedInvoice.assign(kioskCustomer);
	    selectedInvoice.setData("isPaid", false);
	    selectedInvoice.newE();
	}

	return selectedInvoice;
    }

}
