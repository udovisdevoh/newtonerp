package modules.common.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * State
 * 
 * @author r3hallejo
 */
public class State extends AbstractOrmEntity implements PromptViewable
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
