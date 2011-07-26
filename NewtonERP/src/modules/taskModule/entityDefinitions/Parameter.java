package modules.taskModule.entityDefinitions;

// TODO: clean up that file

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.FieldFactory;
import newtonERP.orm.fields.field.FieldType;
import newtonERP.orm.fields.field.property.NaturalKey;

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
		Field key = FieldFactory.newField(FieldType.STRING, "key");
		key.addProperty(new NaturalKey());

		Field value = FieldFactory.newField(FieldType.TEXT, "value", false);
		value.addProperty(new NaturalKey());

		Vector<Field> fieldList = new Vector<Field>();
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
