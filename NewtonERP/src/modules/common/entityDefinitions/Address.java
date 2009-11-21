package modules.common.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * A warehouse address
 * 
 * @author r3hallejo
 */
public class Address extends AbstractOrmEntity
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public Address() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new Country());
	AccessorManager.addAccessor(this, new State());
	setVisibleName("Adresse");
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldString streetName = new FieldString("Rue", "streetName");
	streetName.setNaturalKey(true);

	FieldInt streetNumber = new FieldInt("Numero de rue", "streetNumber");
	streetNumber.setNaturalKey(true);

	FieldString city = new FieldString("Ville", "city");
	city.setNaturalKey(true);

	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit
		.add(new FieldInt("Numero de l'adresse", getPrimaryKeyName()));
	fieldsInit.add(city);
	fieldsInit.add(streetNumber);
	fieldsInit.add(streetName);
	fieldsInit.add(new FieldString("Code postal", "postalCode"));
	fieldsInit
		.add(new FieldString("Numero de telephone", "telephoneNumber"));
	fieldsInit.add(new FieldInt("Pays", new Country().getForeignKeyName()));
	fieldsInit.add(new FieldInt("Province / État", new State()
		.getForeignKeyName()));
	return new Fields(fieldsInit);
    }

}
