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
 * State
 * 
 * @author r3hallejo, Jonatan Cloutier
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
		Field name = FieldFactory.newField(FieldType.STRING, "name");
		name.addProperty(new NaturalKey());

		Vector<Field> fieldList = new Vector<Field>();
		fieldList.add(name);
		return new Fields(fieldList);
	}

}
