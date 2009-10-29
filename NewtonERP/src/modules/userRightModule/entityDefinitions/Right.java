package modules.userRightModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author r3hallejo
 * 
 *         Entity defenition class representing a right
 */
public class Right extends AbstractOrmEntity implements PromptViewable
{
    public Right() throws Exception
    {
	super();
    }

    public Fields initFields()
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro du droit", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom de module", "moduleName"));
	fieldsInit.add(new FieldString("Nom de l'action", "actionName"));
	fieldsInit.add(new FieldString("Nom de l'entité", "entityName"));
	return new Fields(fieldsInit);
    }
}
