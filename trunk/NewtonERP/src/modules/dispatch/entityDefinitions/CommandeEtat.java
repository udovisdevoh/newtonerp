package modules.dispatch.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * État de commande
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class CommandeEtat extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public CommandeEtat() throws Exception
    {
	super();
	setVisibleName("État de commande");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKcommandeEtatID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKcommandeEtatID);

	FieldString nom = new FieldString("Description", "Description");
	nom.setNaturalKey(true);
	fieldList.add(nom);
	return new Fields(fieldList);
    }
}
