package modules.finances.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Entité ServiceProvider du module finances: représente les coordonnées des
 * fournisseurs de services (internet, électricité...)
 * 
 * Pour avoir les info à l'extérieur de ServiceProviderAccount
 * 
 * @author Pascal Lemay
 */

public class ServiceProvider extends AbstractOrmEntity implements
	PromptViewable
{
    /**
     * @throws Exception if creation fails
     */
    public ServiceProvider() throws Exception
    {
	super();

	setVisibleName("Fournisseurs de services");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom", "name"));
	fieldsInit.add(new FieldString("Téléphone", "phone"));
	fieldsInit.add(new FieldInt("Numéro civique", "civicNumber"));
	fieldsInit.add(new FieldString("Rue", "streetName"));
	fieldsInit.add(new FieldString("Ville", "city"));
	fieldsInit.add(new FieldString("Code postal", "zipCode"));

	return new Fields(fieldsInit);
    }

}
