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
    public Customer() throws Exception
    {
	super();
    }

    @Override
    public Fields initFields()
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro du client", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom du client", "customerName"));
	fieldsInit.add(new FieldString("Téléphone du client", "customerPhone"));
	fieldsInit.add(new FieldString("Adresse du client", "customerAddress"));
	return new Fields(fieldsInit);
    }
}
