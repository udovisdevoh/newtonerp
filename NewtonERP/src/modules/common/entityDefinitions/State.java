package modules.common.entityDefinitions; 
 // TODO: clean up that file

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.type.FieldInt;
import newtonERP.orm.fields.field.type.FieldString;

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
