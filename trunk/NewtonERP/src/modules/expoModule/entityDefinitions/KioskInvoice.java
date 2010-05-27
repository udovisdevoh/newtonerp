package modules.expoModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.expoModule.actions.PayInvoice;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldCalcule;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldBool;
import newtonERP.orm.field.type.FieldCurrency;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Facture
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class KioskInvoice extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public KioskInvoice() throws Exception
    {
	super();
	setVisibleName("Facture");
	setDetailedDescription("facture de kiosque");
	AccessorManager.addAccessor(this, new KioskCustomer());
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKkioskInvoiceID = new FieldInt("Numéro", getPrimaryKeyName());
	pKkioskInvoiceID.setNaturalKey(true);
	fieldList.add(pKkioskInvoiceID);

	FieldInt client = new FieldInt("Client", "kioskCustomerID");
	client.setNaturalKey(true);
	fieldList.add(client);

	FieldBool isPaid = new FieldBool("Payée?", "isPaid");
	fieldList.add(isPaid);

	FieldCurrency total = new FieldCurrency("Total", "total");
	total.setCalcul(new FieldCalculeTotal());
	fieldList.add(total);

	return new Fields(fieldList);
    }

    private class FieldCalculeTotal extends FieldCalcule<Double>
    {
	@Override
	protected Double calcul(Fields entityFields) throws Exception
	{
	    double total = 0;

	    KioskInvoiceItem kioskInvoiceItem = new KioskInvoiceItem();
	    kioskInvoiceItem.setData("kioskInvoiceID", entityFields.getField(
		    "PKkioskInvoiceID").getDataString());
	    Vector<AbstractOrmEntity> kioskInvoiceItemList = kioskInvoiceItem
		    .get();

	    for (AbstractOrmEntity invoiceItem : kioskInvoiceItemList)
		total += ((KioskInvoiceItem) invoiceItem).getTotal();

	    return total;
	}
    }

    @Override
    public ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	parameters.put(getPrimaryKeyName(), "&");

	ListViewerData entityList = super.getList(parameters);
	entityList.addSpecificActionButtonList(new ActionLink("Voir",
		new BaseAction("Get", this), parameters));

	entityList.addSpecificActionButtonList(new ActionLink("Payer",
		new PayInvoice(), parameters));

	return entityList;
    }
}
