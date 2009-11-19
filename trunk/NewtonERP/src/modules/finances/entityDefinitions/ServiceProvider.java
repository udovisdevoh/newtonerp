package modules.finances.entityDefinitions;

import java.util.Vector;

import modules.common.entityDefinitions.Address;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

/**
 * Entité ServiceProvider du module finances: représente les coordonnées des
 * fournisseurs de services (internet, électricité...)
 * 
 * Pour avoir les info à l'extérieur de ServiceProviderAccount
 * 
 * @author Pascal Lemay
 */

public class ServiceProvider extends AbstractOrmEntity
{
    /**
     * @throws Exception if creation fails
     */
    public ServiceProvider() throws Exception
    {
	super();
	setVisibleName("Fournisseurs de services");
	AccessorManager.addAccessor(this, new Address());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom", "name"));
	fieldsInit.add(new FieldInt("Adresse", new Address()
		.getForeignKeyName()));

	return new Fields(fieldsInit);
    }

}
