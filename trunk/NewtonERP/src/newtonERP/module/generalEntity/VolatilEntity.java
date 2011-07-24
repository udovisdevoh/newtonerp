package newtonERP.module.generalEntity; 
 // TODO: clean up that file

import newtonERP.module.AbstractEntity;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.VolatileFields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.type.FieldString;

/**
 * @author CloutierJo
 */
public class VolatilEntity extends AbstractEntity {

	/**
	 * default constructor
	 */
	public VolatilEntity() {
		super();
	}

	@Override
	public Fields initFields() {
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
	public void addField(String label, String fieldName) {
		addField(label, fieldName, "");
	}

	/**
	 * @param field un field a ajouter
	 */
	public void addField(Field<?> field) {
		((VolatileFields) (getFields())).add(field);
	}
}
