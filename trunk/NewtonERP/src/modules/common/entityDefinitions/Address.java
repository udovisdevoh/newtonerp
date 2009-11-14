package modules.common.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * A warehouse address
 * 
 * @author r3hallejo
 */
public class Address extends AbstractOrmEntity implements PromptViewable
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
	addNaturalKey("streetNumber");
	addNaturalKey("streetName");
	addNaturalKey("city");
	setVisibleName("Adresse");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit
		.add(new FieldInt("Numero de l'adresse", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Ville", "city"));
	fieldsInit.add(new FieldString("Rue", "streetName"));
	fieldsInit.add(new FieldInt("Numero de rue", "streetNumber"));
	fieldsInit.add(new FieldString("Code postal", "postalCode"));
	fieldsInit
		.add(new FieldString("Numero de telephone", "telephoneNumber"));
	fieldsInit.add(new FieldInt("Pays", new Country().getForeignKeyName()));
	fieldsInit.add(new FieldInt("Province / État", new State()
		.getForeignKeyName()));
	return new Fields(fieldsInit);
    }

}