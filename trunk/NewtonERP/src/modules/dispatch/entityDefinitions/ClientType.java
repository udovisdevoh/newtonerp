package modules.dispatch.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Type de client
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class ClientType extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public ClientType() throws Exception
    {
	super();
	setVisibleName("Type de client");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKclientTypeID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKclientTypeID);

	FieldString nom = new FieldString("Nom", "Nom");
	fieldList.add(nom);
	return new Fields(fieldList);
    }
}
