package modules.testModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * 
 * @author r3lacasgu, r3hallejo
 * 
 *         Test entity
 */
public class TestEntity extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public TestEntity() throws Exception
    {
	super();
    }

    public Fields initFields() throws InvalidOperatorException
    {
	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numéro du test", "testID"));
	fieldsInit.add(new FieldString("Nom", "name"));
	fieldsInit.add(new FieldInt("Age", "age"));
	fieldsInit.add(new FieldString("Couleur", "color"));
	return new Fields(fieldsInit);
    }
}
