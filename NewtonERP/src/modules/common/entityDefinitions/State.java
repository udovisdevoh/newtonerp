package modules.common.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * State
 * 
 * @author r3hallejo
 */
public class State extends AbstractOrmEntity {

	/**
	 */
	public State() {
		super();
		setVisibleName("Provinces / États");
	}

	@Override
	public Fields initFields() {
		FieldString name = new FieldString("Nom", "name");
		name.setNaturalKey(true);

		Vector<Field<?>> fieldList = new Vector<Field<?>>();
		fieldList.add(new FieldInt("Numero de province / état", getPrimaryKeyName()));
		fieldList.add(name);
		return new Fields(fieldList);
	}

}
