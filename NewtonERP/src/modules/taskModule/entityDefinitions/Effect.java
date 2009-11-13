package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.FieldText;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

public class Effect extends AbstractOrmEntity implements PromptViewable
{
    public Effect() throws Exception
    {
	super();
	setVisibleName("Effet");
	addNaturalKey("name");
	AccessorManager.addAccessor(this, new SearchEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldText("Description", "name"));
	fieldList.add(new FieldInt("Entité de recherche", new SearchEntity()
		.getForeignKeyName()));
	fieldList.add(new FieldString("Nom système d'action",
		"actionSystemName"));
	return new Fields(fieldList);
    }

}
