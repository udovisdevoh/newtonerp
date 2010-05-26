package modules.dispatch.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Priorité
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class LivraisonEtat extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public LivraisonEtat() throws Exception
    {
	super();
	setVisibleName("État de livraison");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKprioriteID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKprioriteID);

	FieldString nom = new FieldString("Description", "Description");
	fieldList.add(nom);
	return new Fields(fieldList);
    }
}
