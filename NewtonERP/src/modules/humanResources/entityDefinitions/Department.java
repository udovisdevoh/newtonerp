package modules.humanResources.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

public class Department extends AbstractOrmEntity implements PromptViewable
{

    public Department() throws Exception
    {
	super();
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsData = new Vector<Field>();// Renamé en fieldsData
	fieldsData.add(new FieldInt("Numéro de département",
		getPrimaryKeyName()));
	fieldsData.add(new FieldString("Nom", "departmentName"));
	return new Fields(fieldsData);
    }

}
