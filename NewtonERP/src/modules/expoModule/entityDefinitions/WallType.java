package modules.expoModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Type de muret
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class WallType extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public WallType() throws Exception
    {
	super();
	setVisibleName("Emplacement du muret");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKwallTypeID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKwallTypeID);

	FieldString name = new FieldString("Description", "Name");
	fieldList.add(name);
	return new Fields(fieldList);
    }
}
