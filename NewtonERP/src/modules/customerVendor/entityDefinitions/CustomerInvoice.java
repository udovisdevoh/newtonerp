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
 *         Entité du des facture des client dans le module customerVendor
 */
public class CustomerInvoice extends AbstractOrmEntity
{

    public Fields initFields()
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro de la Facture", getPrimaryKeyName()));
	fieldsInit.add(new FieldDouble("Total de la Facture", "total"));
	fieldsInit.add(new FieldInt("Numéros du client", "customerID"));

	// FIXME : Tu store une date dans un field int???? et pourquoi cette
	// restriction de format?
	fieldsInit.add(new FieldInt("Date de la facture", "date"));
	return new Fields(fieldsInit);
    }
}
