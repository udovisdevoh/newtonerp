package modules.testModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;

/**
 * 
 * @author r3lacasgu, r3hallejo
 * 
 *         Test entity
 */
public class TestEntity extends AbstractOrmEntity
{

    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro du test", "testID"));
	fields.add(new FieldString("Nom", "name"));
	fields.add(new FieldInt("Age", "age"));
	fields.add(new FieldString("Couleur", "color"));
	return new Fields(fields);
    }
}
