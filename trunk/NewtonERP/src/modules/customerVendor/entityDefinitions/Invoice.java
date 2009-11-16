package modules.customerVendor.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.customerVendor.actions.CalculateInvoiceTotal;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.EntityList;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldBool;
import newtonERP.orm.field.FieldCurrency;
import newtonERP.orm.field.FieldDate;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Invoice working for customers dans vendors
 * 
 * @author r3hallejo
 */
public class Invoice extends AbstractOrmEntity implements PromptViewable
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

	addNaturalKey(getPrimaryKeyName());
	addNaturalKey(new Merchant().getForeignKeyName());
	addNaturalKey("date");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	FieldCurrency total = new FieldCurrency("Total", "total");
	total.setReadOnly(true);
	fieldList.add(total);
	FieldCurrency taxTotal = new FieldCurrency("Total taxes", "taxTotal");
	taxTotal.setReadOnly(true);
	fieldList.add(taxTotal);
	fieldList.add(new FieldInt("Nom du client", new Merchant()
		.getForeignKeyName()));
	fieldList.add(new FieldDate("Date", "date"));
	fieldList.add(new FieldBool("Pour client", "isForCustomer"));
	fieldList.add(new FieldInt("Status", new InvoiceStatus()
		.getForeignKeyName()));
	return new Fields(fieldList);
    }

    @Override
    public final EntityList getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	CalculateInvoiceTotal calculerInvoiceTotal = new CalculateInvoiceTotal();
	calculerInvoiceTotal.setOwnedByModul(getCurrentModule());

	EntityList entityList = super.getList(parameters);
	entityList.addSpecificActionButtonList(new ActionLink(
		"Calculer facture", calculerInvoiceTotal));
	return entityList;
    }
}
