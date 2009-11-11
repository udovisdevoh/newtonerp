package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldBool;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * entité représentant une task
 * @author Guillaume Lacasse
 * 
 */
public class Task extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception si création fail
     */
    public Task() throws Exception
    {
	super();
	setVisibleName("Tâche automatisée");
	addNaturalKey(getPrimaryKeyName());
	addNaturalKey("name");
	addLongText("description");
	AccessorManager.addAccessor(this, new SpecificationEntity());
	AccessorManager.addAccessor(this, new TaskAction());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();

	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldBool("Est active", "isActive"));

	new FieldBool("Est active", "isActive", true);

	fieldList.add(new FieldString("Description courte", "name"));
	fieldList.add(new FieldString("Description longue", "description"));
	fieldList.add(new FieldInt("Spécification", new SpecificationEntity()
		.getForeignKeyName()));
	fieldList.add(new FieldInt("Action", new TaskAction()
		.getForeignKeyName()));
	return new Fields(fieldList);
    }
}
