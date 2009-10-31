package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
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
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit
		.add(new FieldInt("Numero de l'adresse", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Ville", "city"));
	fieldsInit.add(new FieldString("Nom de rue", "streetName"));
	fieldsInit.add(new FieldInt("Numero de rue", "streetNumber"));
	fieldsInit.add(new FieldString("Code postal", "postalCode"));
	fieldsInit
		.add(new FieldString("Numero de telephone", "telephoneNumber"));
	return new Fields(fieldsInit);
    }

}
