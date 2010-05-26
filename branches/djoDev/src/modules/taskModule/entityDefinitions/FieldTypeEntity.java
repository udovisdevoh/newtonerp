package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Représente un type de field
 * @author Guillaume
 */
public class FieldTypeEntity extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public FieldTypeEntity() throws Exception
    {
	super();
	setVisibleName("Type de champ");

    }

    protected Fields preInitFields() throws Exception
    {
	// always build the field from initField and not from DB, thats mean
	// that we cannot add a dynamic Field, this should not be done anywhere
	// else
	return initFields();
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldString("Nom système", "systemName"));
	return new Fields(fieldList);
    }
}
