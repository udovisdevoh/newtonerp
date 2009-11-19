package modules.production.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

/**
 * An equipment for maintenance or other
 * 
 * @author r3hallejo
 */
public class Equipment extends AbstractOrmEntity
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public Equipment() throws Exception
    {
	setVisibleName("Équipement");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldList.add(new FieldString("Nom", "name"));
	return new Fields(fieldList);
    }
}
