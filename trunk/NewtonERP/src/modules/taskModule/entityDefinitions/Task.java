package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldBool;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.Fields;
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
	addNaturalKey(new Specification().getForeignKeyName());
	addNaturalKey(new Effect().getForeignKeyName());
	AccessorManager.addAccessor(this, new Specification());
	AccessorManager.addAccessor(this, new Effect());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldBool("Est active", "isActive"));
	fieldList.add(new FieldInt("Specification", new Specification()
		.getForeignKeyName()));
	fieldList.add(new FieldInt("Effet", new Effect().getForeignKeyName()));
	return new Fields(fieldList);
    }
}
