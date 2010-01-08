package modules.dispatch.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;

/**
 * Commande
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Commande extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Commande() throws Exception
    {
	super();
	setVisibleName("Commande");
	AccessorManager.addAccessor(this, new CommandeEtat());
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKcommandeID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKcommandeID);

	FieldInt commandeEtatID = new FieldInt("État", "commandeEtatID");
	commandeEtatID.setColored(true);
	fieldList.add(commandeEtatID);

	return new Fields(fieldList);
    }
}
