package modules.finances.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.finances.entityDefinitions.ServiceProviderAccount;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.EntityList;
import newtonERP.orm.Orm;

/**
 * Action DisplayUnpaidServices: represente l'action d'afficher les services à
 * payer.
 * 
 * p.s. cette classe me sert de test pour l'instant...
 * @author Pascal Lemay
 */

public class DisplayUnpaidServices extends AbstractAction
{
    /**
     * constructeur
     * @throws Exception si création fail
     */
    public DisplayUnpaidServices() throws Exception
    {
	super(null);
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	ServiceProviderAccount account = new ServiceProviderAccount();
	EntityList list = new EntityList(account);

	/*
	 * Another way of getting all the state types if the getPluralAccessor
	 * does not work for a reason we don't understand.
	 * 
	 * The way would be to do. Warning -> Following lines has not been
	 * tested. I'm only telling another way of doing it
	 * 
	 * Vector<AbstractOrmEntity> types =
	 * Orm.executeCustomQuery("SELECT * FROM StateType");
	 * 
	 * And this would return all the StateType contained in the database
	 * 
	 * Added by Kovalev on November 11 @ 19h17
	 */
	Vector<AbstractOrmEntity> types = account
		.getPluralAccessor("StateType");

	for (AbstractOrmEntity type : types)
	{
	    if (!type.getDataString("name").equals("Non-paye"))
		types.remove(type);
	}

	Vector<AbstractOrmEntity> accounts = Orm.select(account);
	for (AbstractOrmEntity ent : accounts)

	    if (ent.getData("stateTypeID").equals(
		    types.get(0).getPrimaryKeyValue()))

		list.addEntity(ent);

	return list;
    }
}
