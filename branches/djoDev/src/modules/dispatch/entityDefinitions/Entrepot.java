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
 * Entrepôt
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Entrepot extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Entrepot() throws Exception
    {
	super();
	setVisibleName("Entrepôt");
	AccessorManager.addAccessor(this, new Secteur());
	AccessorManager.addAccessor(this, new Address());
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKentrepotID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKentrepotID);

	FieldString nom = new FieldString("Nom", "Nom");
	fieldList.add(nom);

	FieldInt secteurID = new FieldInt("Secteur", "secteurID");
	fieldList.add(secteurID);

	FieldInt addressID = new FieldInt("Adresse", "addressID");
	fieldList.add(addressID);

	return new Fields(fieldList);
    }
}
