package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;

/**
 * * Entité du marketing// les pub, le prix date de debut date de fin
 * @author Gabriel
 */
public class Marketing extends AbstractOrmEntity
{

    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro de la pub", getPrimaryKeyName()));
	fields.add(new FieldString("Nom de la pub", "pubName"));
	fields.add(new FieldInt("Date de début", "startingDate"));// ddmmyyyy
	fields.add(new FieldInt("Date de fin", "endingDate"));
	fields.add(new FieldInt("Prix de la pub", "customerID"));
	return new Fields(fields);
    }
}