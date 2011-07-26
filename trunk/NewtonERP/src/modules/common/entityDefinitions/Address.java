package modules.common.entityDefinitions;

// TODO: clean up that file

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.FieldFactory;
import newtonERP.orm.fields.field.FieldType;
import newtonERP.orm.fields.field.property.NaturalKey;
import newtonERP.orm.fields.field.type.FieldInt;

/**
 * A warehouse address
 * 
 * @author r3hallejo, Jonatan Cloutier
 */
public class Address extends AbstractOrmEntity {

	/**
	 * Default constructor
	 */
	public Address() {
		super();
		AccessorManager.addAccessor(this, new Country());
		AccessorManager.addAccessor(this, new State());
		setVisibleName("Adresse");
	}

	@Override
	public Fields initFields() {

		Field streetNumber = FieldFactory.newField(FieldType.INT, "Street Number");
		streetNumber.addProperty(new NaturalKey(1));

		Field streetName = FieldFactory.newField(FieldType.STRING, "Street Name");
		streetName.addProperty(new NaturalKey(2));

		Field city = FieldFactory.newField(FieldType.STRING, "City");
		city.addProperty(new NaturalKey(3));

		Vector<Field> fieldsInit = new Vector<Field>();
		fieldsInit.add(city);
		fieldsInit.add(streetNumber);
		fieldsInit.add(streetName);
		fieldsInit.add(FieldFactory.newField(FieldType.STRING, "Zip Code"));
		fieldsInit.add(FieldFactory.newField(FieldType.STRING, "Phone Number"));
		fieldsInit.add(new FieldInt("Pays", new Country().getForeignKeyName()));
		fieldsInit.add(new FieldInt("Province / État", new State().getForeignKeyName()));
		return new Fields(fieldsInit);
	}
}
