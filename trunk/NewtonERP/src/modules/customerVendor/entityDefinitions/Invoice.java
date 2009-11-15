package modules.customerVendor.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.customerVendor.actions.CalculateInvoiceTotal;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.EntityList;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
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
	setVisibleName("Facture");

	addNaturalKey(getPrimaryKeyName());
	addNaturalKey(new Merchant().getForeignKeyName());
	addNaturalKey("date");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldCurrency("Total", "total"));
	fieldList.add(new FieldInt("Nom du client", new Merchant()
		.getForeignKeyName()));
	fieldList.add(new FieldDate("Date", "date"));
	return new Fields(fieldList);
    }

    @Override
    public final AbstractEntity getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	EntityList entityList = (EntityList) super.getList(parameters);
	entityList.addSpecificActionButtonList("Calculer facture",
		new CalculateInvoiceTotal());
	return entityList;
    }
}
