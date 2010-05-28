package modules.production.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * An equipment type
 * 
 * @author r3hallejo
 */
public class EquipmentType extends AbstractOrmEntity
{
	/**
	 * Default constructor
	 * 
	 * @throws Exception a general exception
	 */
	public EquipmentType() throws Exception
	{
		setVisibleName("Type d'équipements");
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
