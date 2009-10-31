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

    public CustomerInvoice() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new Customer());
    }

    public Fields initFields()
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList
		.add(new FieldInt("Numéro de la Facture", getPrimaryKeyName()));
	fieldList.add(new FieldDouble("Total de la Facture", "total"));
	fieldList.add(new FieldInt("Numéros du client", "customerID"));
	fieldList.add(new FieldDate("Date de la facture", "date")); // ddmmyyyy
	return new Fields(fieldList);
    }
}
