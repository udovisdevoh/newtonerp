package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDouble;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;

/**
 * @author Gabriel
 * 
 *         Entité du des facture des fournisseur dans le module customerVendor
 */
public class VendorInvoice extends AbstractOrmEntity
{

    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro de la Facture", getPrimaryKeyName()));
	fields.add(new FieldDouble("Total de la Facture", "total"));
	fields.add(new FieldInt("Numéros du client", "vendorID"));

	// FIXME : Tu store une date dans un field int???? et pourquoi cette
	// restriction de format?
	fields.add(new FieldInt("Date de la facture", "date"));
	return new Fields(fields);
    }
}
