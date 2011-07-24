package modules.taskModule.entityDefinitions; 
 // TODO: clean up that file

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.type.FieldInt;
import newtonERP.orm.fields.field.type.FieldString;

/**
 * Représente un type de field
 * 
 * @author Guillaume
 */
public class FieldTypeEntity extends AbstractOrmEntity {
	/**
	 */
	public FieldTypeEntity() {
		super();
		setVisibleName("Type de champ");

	}

	@Override
	protected Fields preInitFields() {
		// always build the field from initField and not from DB, thats mean
		// that we cannot add a dynamic Field, this should not be done anywhere
		// else
		return initFields();
	}

	@Override
	public Fields initFields() {
		Vector<Field<?>> fieldList = new Vector<Field<?>>();
		fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
		fieldList.add(new FieldString("Nom système", "systemName"));
		return new Fields(fieldList);
	}
}
