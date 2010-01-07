package modules.dispatch.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Secteur
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Secteur extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Secteur() throws Exception
    {
	super();
	setVisibleName("Secteur");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKsecteurID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKsecteurID);

	FieldString nom = new FieldString("Nom", "Nom");
	fieldList.add(nom);
	return new Fields(fieldList);
    }
}
