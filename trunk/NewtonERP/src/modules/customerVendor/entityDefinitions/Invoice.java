package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDate;
import newtonERP.module.field.FieldDouble;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
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
	AccessorManager.addAccessor(this, new Customer());
	addCurrencyFormat("total");
	setVisibleName("Facture");

	addNaturalKey(getPrimaryKeyName());
	addNaturalKey(new Customer().getForeignKeyName());
	addNaturalKey("date");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldDouble("Total", "total"));
	fieldList.add(new FieldInt("Nom du client", new Customer()
		.getForeignKeyName()));
	fieldList.add(new FieldDate("Date", "date"));
	return new Fields(fieldList);
    }
}
