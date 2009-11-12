package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldDate;
import newtonERP.orm.field.FieldDouble;
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
	addCurrencyFormat("total");
	setVisibleName("Facture");

	addNaturalKey(getPrimaryKeyName());
	addNaturalKey(new Merchant().getForeignKeyName());
	addNaturalKey("date");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldDouble("Total", "total"));
	fieldList.add(new FieldInt("Nom du client", new Merchant()
		.getForeignKeyName()));
	fieldList.add(new FieldDate("Date", "date"));
	return new Fields(fieldList);
    }
}
