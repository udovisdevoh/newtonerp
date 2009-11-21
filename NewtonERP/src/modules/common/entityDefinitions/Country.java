package modules.common.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * A country in the common module
 * 
 * @author r3hallejo
 */
public class Country extends AbstractOrmEntity
{

    /**
     * @throws Exception a general exception
     */
    public Country() throws Exception
    {
	super();
	setVisibleName("Pays");
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldString name = new FieldString("Nom", "name");
	name.setNaturalKey(true);

	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numero de pays", getPrimaryKeyName()));
	fieldList.add(name);
	return new Fields(fieldList);
    }

}
