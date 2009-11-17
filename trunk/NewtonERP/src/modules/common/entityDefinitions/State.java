package modules.common.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

/**
 * State
 * 
 * @author r3hallejo
 */
public class State extends AbstractOrmEntity
{

    /**
     * @throws Exception a general exception
     */
    public State() throws Exception
    {
	super();
	addNaturalKey("name");
	setVisibleName("Provinces / États");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numero de province / état",
		getPrimaryKeyName()));
	fieldList.add(new FieldString("Nom", "name"));
	return new Fields(fieldList);
    }

}
