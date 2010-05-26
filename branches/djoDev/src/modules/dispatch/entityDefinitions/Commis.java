package modules.dispatch.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Commis
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Commis extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Commis() throws Exception
    {
	super();
	setVisibleName("Commis");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKcommisID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKcommisID);

	FieldString nom = new FieldString("Nom", "Nom");
	fieldList.add(nom);
	return new Fields(fieldList);
    }
}
