package modules.dispatch.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Livreur
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Livreur extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Livreur() throws Exception
    {
	super();
	setVisibleName("Livreur");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKlivreurID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKlivreurID);

	FieldString nom = new FieldString("Nom", "Nom");
	fieldList.add(nom);
	return new Fields(fieldList);
    }
}
