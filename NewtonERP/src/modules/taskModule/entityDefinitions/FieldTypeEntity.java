package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

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

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldString("Nom système", "systemName"));
	return new Fields(fieldList);
    }
}
