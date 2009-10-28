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
    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro du droit", getPrimaryKeyName()));
	fields.add(new FieldString("Nom de module", "moduleName"));
	fields.add(new FieldString("Nom de l'action", "actionName"));
	fields.add(new FieldString("Nom de l'entité", "entityName"));
	return new Fields(fields);
    }
}
