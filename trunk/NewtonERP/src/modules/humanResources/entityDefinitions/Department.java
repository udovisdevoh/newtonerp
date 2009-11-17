package modules.humanResources.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

/**
 * Représente un département dans une compagnie
 * @author Guillaume
 */
public class Department extends AbstractOrmEntity
{

    /**
     * @throws Exception si création fails
     */
    public Department() throws Exception
    {
	super();
	setVisibleName("Département");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsData = new Vector<Field>();
	fieldsData.add(new FieldInt("Numéro de département",
		getPrimaryKeyName()));
	fieldsData.add(new FieldString("Nom", "departmentName"));
	return new Fields(fieldsData);
    }

}
