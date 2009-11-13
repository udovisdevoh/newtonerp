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

/**
 * Entité représentant une action
 * @author Guillaume Lacasse
 */
public class TaskEffect extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception si création fail
     */
    public TaskEffect() throws Exception
    {
	super();
	setVisibleName("Effet");
	addNaturalKey("name");
	AccessorManager.addAccessor(this, new TaskSearchEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldString("Description courte", "name"));
	fieldList.add(new FieldText("Description longue", "description"));
	fieldList.add(new FieldString("Nom système d'action",
		"actionSystemName"));

	fieldList.add(new FieldInt("Entité de recherche",
		new TaskSearchEntity().getForeignKeyName()));

	return new Fields(fieldList);
    }

}
