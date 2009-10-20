package modules.testModule.entityDefinitions;

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
 * 
 * @author r3lacasgu, r3hallejo
 * 
 *         Test entity
 */
public class TestEntity extends AbstractEntity implements Ormizable
{

    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("numéro du test", "testID"));
	fields.add(new FieldString("nom", "name"));
	fields.add(new FieldInt("age", "age"));
	fields.add(new FieldString("couleur", "color"));
	return new Fields(fields);
    }

    @Override
    public Hashtable<String, String> getOrmizableData() throws OrmException
    {
	return getFields().getHashTableFrom();
    }

    @Override
    public void setOrmizableData(Hashtable<String, Object> parameters)
    {
	getFields().setFromHashTable(parameters);
    }
}
