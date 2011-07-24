package newtonERP.orm.fields.field;

import newtonERP.orm.fields.field.type.FieldBool;
import newtonERP.orm.fields.field.type.FieldCurrency;
import newtonERP.orm.fields.field.type.FieldDate;
import newtonERP.orm.fields.field.type.FieldDateTime;
import newtonERP.orm.fields.field.type.FieldDouble;
import newtonERP.orm.fields.field.type.FieldInt;
import newtonERP.orm.fields.field.type.FieldString;
import newtonERP.orm.fields.field.type.FieldText;
import newtonERP.orm.fields.field.type.FieldTime;

/**
 * A factory for creating Field objects.
 * 
 * @author Jonatan Cloutier
 */
public final class FieldFactory {

	/**
	 * New field.
	 * 
	 * @param fieldType the field type
	 * @param fieldName the field name
	 * @return the field
	 */
	public static Field newField(FieldType fieldType, String fieldName) {
		InnerField<?> innerField = null;
		switch(fieldType){
			case BOOL:
				innerField = new FieldBool();
				break;
			case CURRENCY:
				innerField = new FieldCurrency();
				break;
			case DATE:
				innerField = new FieldDate();
				break;
			case DATE_TIME:
				innerField = new FieldDateTime();
				break;
			case DOUBLE:
				innerField = new FieldDouble();
				break;
			case INT:
				innerField = new FieldInt();
				break;
			case STRING:
				innerField = new FieldString();
				break;
			case TEXT:
				innerField = new FieldText();
				break;
			case TIME:
				innerField = new FieldTime();
				break;

		}

		return new Field(fieldName, innerField);
	}

	/**
	 * New field.
	 * 
	 * @param fieldType the field type
	 * @param fieldName the field name
	 * @param data the data
	 * @return the field
	 */
	public static Field newField(FieldType fieldType, String fieldName, Object data) {
		Field field = newField(fieldType, fieldName);
		field.setData(data);
		return field;

	}
}
