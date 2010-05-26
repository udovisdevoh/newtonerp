package modules.dispatch.entityDefinitions;

import java.util.Vector;

import modules.common.entityDefinitions.Address;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Client
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Client extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Client() throws Exception
    {
	super();
	setVisibleName("Client");
	AccessorManager.addAccessor(this, new Secteur());
	AccessorManager.addAccessor(this, new Address());
	AccessorManager.addAccessor(this, new ClientType());
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKclientID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKclientID);

	FieldString nom = new FieldString("Nom", "Nom");
	fieldList.add(nom);

	FieldInt secteurID = new FieldInt("Secteur", "secteurID");
	fieldList.add(secteurID);

	FieldInt addressID = new FieldInt("Adresse", "addressID");
	fieldList.add(addressID);

	FieldInt clientTypeID = new FieldInt("Type", "clientTypeID");
	clientTypeID.setColored(true);
	fieldList.add(clientTypeID);

	secteurID.setColored(true);
	return new Fields(fieldList);
    }
}
