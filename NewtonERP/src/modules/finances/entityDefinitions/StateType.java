package modules.finances.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

/**
 * Représente un type d'état de compte, par exemple: en souffrance, payé, etc...
 * @author Guillaume Lacasse
 */
public class StateType extends AbstractOrmEntity
{

    /**
     * @throws Exception si création fail
     */
    public StateType() throws Exception
    {
	super();
	// addNaturalKey("name");
	setVisibleName("Type d'état");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Description", "name"));
	return new Fields(fieldsInit);
    }
}
