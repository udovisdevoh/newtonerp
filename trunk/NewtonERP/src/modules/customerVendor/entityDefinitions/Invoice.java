package modules.customerVendor.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.customerVendor.actions.CalculateInvoiceTotal;
import modules.customerVendor.actions.UpdateProductQuantity;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldBool;
import newtonERP.orm.field.FieldCurrency;
import newtonERP.orm.field.FieldDate;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Invoice working for customers dans vendors
 * 
 * @author r3hallejo
 */
public class Invoice extends AbstractOrmEntity
{

    /**
     * @throws Exception si création fail
     */
    public Invoice() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new Merchant());
	AccessorManager.addAccessor(this, new InvoiceStatus());
	setVisibleName("Facture");
    }

    public Fields initFields() throws Exception
    {
	FieldInt primaryKey = new FieldInt("Numéro", getPrimaryKeyName());
	primaryKey.setNaturalKey(true);

	FieldInt merchant = new FieldInt("Nom du client", new Merchant()
		.getForeignKeyName());
	merchant.setNaturalKey(true);

	FieldDate date = new FieldDate("Date", "date");
	date.setNaturalKey(true);

	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(primaryKey);
	FieldCurrency total = new FieldCurrency("Total", "total");
	total.setReadOnly(true);
	fieldList.add(total);
	FieldCurrency taxTotal = new FieldCurrency("Total taxes", "taxTotal");
	taxTotal.setReadOnly(true);
	fieldList.add(taxTotal);
	fieldList.add(merchant);
	fieldList.add(date);
	fieldList.add(new FieldBool("Pour client", "isForCustomer"));
	fieldList.add(new FieldBool("Pour Fournisseur", "isForSupplier"));
	fieldList.add(new FieldInt("Status", new InvoiceStatus()
		.getForeignKeyName()));
	return new Fields(fieldList);
    }

    @Override
    public final ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	Hashtable<String, String> actionParameters = new Hashtable<String, String>();
	actionParameters.put(getPrimaryKeyName(), "&");

	ListViewerData entityList = super.getList(parameters);
	entityList.addSpecificActionButtonList(new ActionLink(
		"Calculer facture", new CalculateInvoiceTotal(),
		actionParameters));
	entityList.addSpecificActionButtonList(new ActionLink(
		"Mettre a jour produits", new UpdateProductQuantity(),
		actionParameters));

	return entityList;
    }
}
