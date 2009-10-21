package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;

/**
 * @author r3hallejo
 * 
 *         Entity defenition class representing a right
 */
public class Right extends AbstractOrmEntity
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
    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity editUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity getUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity newUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }
}
