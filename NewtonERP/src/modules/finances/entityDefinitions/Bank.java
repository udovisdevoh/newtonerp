package modules.finances.entityDefinitions;

import java.util.Vector;

import modules.common.entityDefinitions.Address;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.Type.FieldInt;
import newtonERP.orm.field.Type.FieldString;

/**
 * Entité Bank du module finances: représente les coordonnées des banques avec
 * lesquelles la compagnie fait affaire.
 * 
 * @author Pascal Lemay
 */
public class Bank extends AbstractOrmEntity
{
    /**
     * @throws Exception if creation fails
     */
    public Bank() throws Exception
    {
	super();
	setVisibleName("Banques");
	AccessorManager.addAccessor(this, new Address());
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldInt transit = new FieldInt("Transit", "transit");
	transit.setNaturalKey(true);

	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom", "name"));
	fieldsInit.add(transit);
	fieldsInit.add(new FieldInt("Adresse", new Address()
		.getForeignKeyName()));
	return new Fields(fieldsInit);
    }
}
