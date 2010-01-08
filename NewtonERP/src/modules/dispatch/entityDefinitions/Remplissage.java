package modules.dispatch.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldDateTime;
import newtonERP.orm.field.type.FieldDouble;
import newtonERP.orm.field.type.FieldInt;

/**
 * Remplissage
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Remplissage extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Remplissage() throws Exception
    {
	super();
	setVisibleName("Remplissage");
	AccessorManager.addAccessor(this, new Vehicule());
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKremplissageID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKremplissageID);

	FieldInt vehiculeID = new FieldInt("Véhicule", "vehiculeID");
	vehiculeID.setNaturalKey(true);
	fieldList.add(vehiculeID);

	FieldDouble essence = new FieldDouble(
		"L d`essence (70.13 pour 70,13 L):", "Essence");
	fieldList.add(essence);

	FieldDateTime heure = new FieldDateTime("Heure d'inscription", "heure");
	heure.setNaturalKey(true);
	heure.setReadOnly(true);
	fieldList.add(heure);

	FieldInt kilometrage = new FieldInt(
		"Kilométrage au moment du remplissage", "Kilometrage");
	fieldList.add(kilometrage);

	FieldInt cout = new FieldInt("Coût", "Cout");
	fieldList.add(cout);
	return new Fields(fieldList);
    }
}
