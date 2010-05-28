package modules.expoModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Connexion internet
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class InternetConnectionType extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public InternetConnectionType() throws Exception
    {
	super();
	setVisibleName("Connexion internet");
	setDetailedDescription("type de connection internet");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKinternetConnectionTypeID = new FieldInt("Numéro",
		getPrimaryKeyName());
	fieldList.add(pKinternetConnectionTypeID);

	FieldString name = new FieldString("Description", "Name");
	fieldList.add(name);
	return new Fields(fieldList);
    }
}
