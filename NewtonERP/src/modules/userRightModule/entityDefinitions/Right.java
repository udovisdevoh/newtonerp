package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author r3hallejo
 * 
 *         Entity defenition class representing a right
 */
public class Right extends AbstractEntity implements Ormizable
{
    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("numéro du droit", "PKrightID"));
	fields.add(new FieldString("nom de module", "moduleName"));
	fields.add(new FieldString("nom de l'action", "actionName"));
	fields.add(new FieldString("nom de l'entité", "entityName"));
	return new Fields(fields);
    }

    @Override
    public Hashtable<String, String> getOrmizableData() throws OrmException
    {
	return getHashTableFromEntity();
    }

    @Override
    public void setOrmizableData(Hashtable<String, Object> parameters)
    {
	setEntityFromHashTable(parameters);
    }
}
