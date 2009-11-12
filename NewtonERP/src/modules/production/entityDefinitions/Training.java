package modules.production.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * A general training
 * 
 * @author r3hallejo
 */
public class Training extends AbstractOrmEntity implements PromptViewable
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public Training() throws Exception
    {
	super();
	setVisibleName("Formation");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
	return new Fields(fieldsInit);
    }

}
