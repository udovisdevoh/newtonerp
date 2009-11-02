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
 * @author Gabriel
 * 
 *         Entité du des facture des client dans le module customerVendor
 */
public class CustomerInvoice extends AbstractOrmEntity implements
	PromptViewable
{

    /**
     * @throws Exception si création fail
     */
    public CustomerInvoice() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new Customer());
	addCurrencyFormat("total");
	setVisibleName("Facture de client");
    }

    public Fields initFields()
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldDouble("Total", "total"));
	fieldList.add(new FieldInt("Numéro du client", "customerID"));
	fieldList.add(new FieldDate("Date", "date")); // ddmmyyyy
	return new Fields(fieldList);
    }
}
