package modules.finances.entityDefinitions;

import java.util.Vector;

import modules.common.entityDefinitions.Address;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Entité Bank du module finances: représente les coordonnées des banques avec
 * lesquelles la compagnie fait affaire.
 * 
 * @author Pascal Lemay
 */
public class Bank extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception if creation fails
     */
    public Bank() throws Exception
    {
	super();
	setVisibleName("Banques");
	addNaturalKey("transit");
	AccessorManager.addAccessor(this, new Address());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom", "name"));
	fieldsInit.add(new FieldInt("Transit", "transit"));
	fieldsInit.add(new FieldInt("Adresse", new Address()
		.getForeignKeyName()));
	return new Fields(fieldsInit);
    }
}
