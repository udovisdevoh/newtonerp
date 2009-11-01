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
 *         Entité du des facture des fournisseur dans le module customerVendor
 */
public class VendorInvoice extends AbstractOrmEntity implements PromptViewable
{

    public VendorInvoice() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new Vendor());
	addCurrencyFormat("total");
    }

    public Fields initFields()
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldDouble("Total", "total"));
	fieldList.add(new FieldInt("Numéro du fournisseur", "vendorID"));
	fieldList.add(new FieldDate("Date", "date")); // ddmmyyyy
	return new Fields(fieldList);
    }
}
