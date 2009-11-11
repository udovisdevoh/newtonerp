package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Entité représentant une action
 * @author Guillaume Lacasse
 */
public class TaskAction extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception si création fail
     */
    public TaskAction() throws Exception
    {
	super();
	setVisibleName("Action");
	addNaturalKey("name");
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
