package modules.common.entityDefinitions;

// TODO: clean up that file

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.FieldFactory;
import newtonERP.orm.fields.field.FieldType;
import newtonERP.orm.fields.field.property.NaturalKey;

/**
 * A country in the common module
 * 
 * @author r3hallejo Jonatan Cloutier
 */
public class Country extends AbstractOrmEntity {

	/**
	 */
	public Country() {
		super();
		setVisibleName("Pays");
	}

	@Override
	public Fields initFields() {
		Field name = FieldFactory.newField(FieldType.STRING, "Name");
		name.addProperty(new NaturalKey());

		Vector<Field> fieldList = new Vector<Field>();
		fieldList.add(name);
		return new Fields(fieldList);
	}

}
