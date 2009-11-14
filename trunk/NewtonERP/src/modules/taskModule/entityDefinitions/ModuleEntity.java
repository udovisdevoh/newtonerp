package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Entité représentant un module
 * @author Guillaume Lacasse
 */
public class ModuleEntity extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception si création fail
     */
    public ModuleEntity() throws Exception
    {
	super();
	setVisibleName("Module");
	addNaturalKey("systemName");
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
