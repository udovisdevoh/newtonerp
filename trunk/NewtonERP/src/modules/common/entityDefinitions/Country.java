package modules.common.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * A country in the common module
 * 
 * @author r3hallejo
 */
public class Country extends AbstractOrmEntity implements PromptViewable
{

    /**
     * @throws Exception a general exception
     */
    public Country() throws Exception
    {
	super();
	setVisibleName("Pays");
	addNaturalKey("name");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numero de pays", getPrimaryKeyName()));
	fieldList.add(new FieldString("Nom", "name"));
	return new Fields(fieldList);
    }

}
