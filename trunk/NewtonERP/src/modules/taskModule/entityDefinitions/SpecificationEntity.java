package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

public class SpecificationEntity extends AbstractOrmEntity implements
	PromptViewable
{
    public SpecificationEntity() throws Exception
    {
	super();
	setVisibleName("Spécification");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldString("Description", "name"));
	fieldList.add(new FieldString("Nom de classe", "className"));
	return new Fields(fieldList);
    }

}
