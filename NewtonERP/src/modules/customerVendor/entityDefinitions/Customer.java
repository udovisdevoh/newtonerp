package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;

/**
 * @author Gabriel
 * 
 *         Entité du client dans le module customerVendor
 */
public class Customer extends AbstractOrmEntity
{
    @Override
    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro du client", getPrimaryKeyName()));
	fields.add(new FieldString("Nom du client", "customerName"));
	fields.add(new FieldString("Téléphone du client", "customerPhone"));
	fields.add(new FieldString("Adresse du client", "customerAddress"));
	return new Fields(fields);
    }
}
