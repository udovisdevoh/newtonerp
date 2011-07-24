package modules.common.entityDefinitions; 
 // TODO: clean up that file

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.type.FieldInt;
import newtonERP.orm.fields.field.type.FieldString;

/**
 * A country in the common module
 * 
 * @author r3hallejo
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
		FieldString name = new FieldString("Nom", "name");
		name.setNaturalKey(true);

		Vector<Field<?>> fieldList = new Vector<Field<?>>();
		fieldList.add(new FieldInt("Numero de pays", getPrimaryKeyName()));
		fieldList.add(name);
		return new Fields(fieldList);
	}

}
