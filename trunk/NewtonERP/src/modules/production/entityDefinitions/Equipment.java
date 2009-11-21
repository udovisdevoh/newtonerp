package modules.production.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

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
	AccessorManager.addAccessor(this, new EquipmentType());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldList.add(new FieldString("Nom", "name"));
	fieldList.add(new FieldInt("Type", new EquipmentType()
		.getForeignKeyName()));
	return new Fields(fieldList);
    }
}
