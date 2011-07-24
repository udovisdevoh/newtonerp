package modules.taskModule.entityDefinitions; 
 // TODO: clean up that file

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.type.FieldInt;
import newtonERP.orm.fields.field.type.FieldString;
import newtonERP.orm.fields.field.type.FieldText;

/**
 * Paramètres custom utilisé par effet de task
 * 
 * @author Guillaume Lacasse
 */
public class Parameter extends AbstractOrmEntity {
	/**
	 */
	public Parameter() {
		super();
		setVisibleName("Paramètre générique");
	}

	@Override
	public Fields initFields() {
		FieldString key = new FieldString("Nom de clef", "key");
		key.setNaturalKey(true);

		FieldText value = new FieldText("Valeur", "value", false);
		value.setNaturalKey(true);

		Vector<Field<?>> fieldList = new Vector<Field<?>>();
		fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
		fieldList.add(key);
		fieldList.add(value);

		return new Fields(fieldList);
	}

	/**
	 * @return clef du paramètre
	 */
	public String getKey() {
		return getDataString("key");
	}

	/**
	 * @return valeur du paramètre
	 */
	public String getValue() {
		return getDataString("value");
	}
}
