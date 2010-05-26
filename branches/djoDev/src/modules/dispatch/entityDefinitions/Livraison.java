package modules.dispatch.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldDateTime;
import newtonERP.orm.field.type.FieldInt;

/**
 * Livraison
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Livraison extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Livraison() throws Exception
    {
	super();
	setVisibleName("Livraison");
	AccessorManager.addAccessor(this, new Route());
	AccessorManager.addAccessor(this, new Client());
	AccessorManager.addAccessor(this, new LivraisonEtat());
	// AccessorManager.addGateWay(this, new Client(), new ClientType());
	AccessorManager.addGateWay(this, new Client(), new Secteur());
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKlivraisonID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKlivraisonID);

	FieldInt routeID = new FieldInt("Route", "routeID");
	fieldList.add(routeID);

	FieldInt clientID = new FieldInt("Client", "clientID");
	clientID.setNaturalKey(true);
	fieldList.add(clientID);

	FieldInt factureID = new FieldInt("# Facture", "facture");
	factureID.setNaturalKey(true);
	fieldList.add(factureID);

	FieldDateTime heure = new FieldDateTime("Heure", "heure");
	heure.setNaturalKey(true);
	heure.setReadOnly(true);
	fieldList.add(heure);

	FieldInt prioriteID = new FieldInt("Priorité", "livraisonEtatID");
	prioriteID.setColored(true);
	fieldList.add(prioriteID);
	return new Fields(fieldList);
    }
}
