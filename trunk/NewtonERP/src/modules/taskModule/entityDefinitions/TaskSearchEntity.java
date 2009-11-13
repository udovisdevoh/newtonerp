package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

public class TaskSearchEntity extends AbstractOrmEntity implements
	PromptViewable
{
    public TaskSearchEntity() throws Exception
    {
	super();
	setVisibleName("Entité de recherche");
	addNaturalKey("name");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldString("Description courte", "name"));
	fieldList.add(new FieldString("Nom système", "entitySystemName"));
	return new Fields(fieldList);
    }

}
