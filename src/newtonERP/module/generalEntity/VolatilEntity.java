package newtonERP.module.generalEntity;

import newtonERP.module.AbstractEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.VolatileFields;
import newtonERP.orm.field.type.FieldString;

/**
 * @author CloutierJo
 * 
 */
public class VolatilEntity extends AbstractEntity
{

	/**
	 * default constructor
	 */
	public VolatilEntity()
	{
		super();
	}

	public Fields initFields()
	{
		return new VolatileFields();
	}

	/**
	 * @param label étiquette du champ
	 * @param fieldName nom du champ
	 * @param currentValue valeur courante
	 */
	public void addField(String label, String fieldName, String currentValue)

	{
		Field<?> field = new FieldString(label, fieldName);
		field.setData(currentValue);
		addField(field);
	}

	/**
	 * @param label étiquette du champ
	 * @param fieldName nom du champ
	 */
	public void addField(String label, String fieldName)
	{
		addField(label, fieldName, "");
	}

	/**
	 * @param field un field a ajouter
	 */
	public void addField(Field<?> field)
	{
		((VolatileFields) (getFields())).add(field);
	}
}
