package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;

/**
 * * Entité de la firme publicitaire// les pub, le prix date de debut date de
 * fin
 * @author Gabriel
 */
public class Firm extends AbstractOrmEntity
{

    public Firm() throws Exception
    {
	super();
	// TODO Auto-generated constructor stub
    }

    public Fields initFields()
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom", "name"));
	fieldsInit.add(new FieldString("Téléphone", "phone"));
	fieldsInit.add(new FieldString("Adresse", "address"));
	return new Fields(fieldsInit);
    }
}