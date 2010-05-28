package modules.expoModule.actions;

import java.util.Hashtable;

import modules.expoModule.entityDefinitions.Floor;
import modules.expoModule.entityDefinitions.KioskCustomer;
import modules.expoModule.entityDefinitions.KioskInvoice;
import modules.expoModule.entityDefinitions.KioskInvoiceItem;
import modules.expoModule.entityDefinitions.Option;
import modules.expoModule.entityDefinitions.Zone;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.ForwardEntity;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.type.FieldBool;

/**
 * Action d'acheter une zone par un clic
 * @author Guillaume Lacasse
 */
public class BuyZone extends AbstractAction
{
    /**
     * @throws Exception is ça fail
     */
    public BuyZone() throws Exception
    {
	super(new Zone());
	setDetailedDescription("Achetter une");
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	KioskInvoice currentActiveTransaction = getCurrentActiveTransaction();
	currentActiveTransaction.save();

	Option option = new Option();
	option.setData("name", "Zone");
	option = (Option) option.get().get(0);

	KioskInvoiceItem invoiceItem = new KioskInvoiceItem();
	invoiceItem.assign(option);
	invoiceItem.assign(currentActiveTransaction);
	invoiceItem.setData("amount", 1);
	invoiceItem.newE();

	Floor floor = new Floor();
	floor.setData(floor.getPrimaryKeyName(), parameters.get(floor
		.getPrimaryKeyName()));
	floor = (Floor) floor.get().get(0);

	Zone zone = new Zone();
	zone.setData("PositionX", parameters.get("PositionX"));
	zone.setData("PositionY", parameters.get("PositionY"));
	zone.setData("isActive", false);
	zone.assign(invoiceItem);
	zone.assign(floor);
	zone.newE();

	// EditShoppingCart editShoppingCart = new EditShoppingCart();
	// return editShoppingCart.doAction(entity, parameters);

	return new ForwardEntity("/ExpoModule/EditShoppingCart");
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
