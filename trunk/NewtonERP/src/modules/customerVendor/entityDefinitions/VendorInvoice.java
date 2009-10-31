package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDate;
import newtonERP.module.field.FieldDouble;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;
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
    }

    public Fields initFields()
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList
		.add(new FieldInt("Numéro de la Facture", getPrimaryKeyName()));
	fieldList.add(new FieldDouble("Total de la Facture", "total"));
	fieldList.add(new FieldInt("Numéros du client", "vendorID"));
	fieldList.add(new FieldDate("Date de la facture", "date")); // ddmmyyyy
	return new Fields(fieldList);
    }
}
