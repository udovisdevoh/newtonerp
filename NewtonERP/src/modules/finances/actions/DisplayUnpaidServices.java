package modules.finances.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.finances.entityDefinitions.ServiceProviderAccount;
import modules.finances.entityDefinitions.StateType;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.EntityList;
import newtonERP.orm.Orm;

/**
 * Action DisplayUnpaidServices: représente l'action d'afficher les services à
 * payer.
 * 
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

	StateType searchEntity = new StateType();
	/*
	 * Pourquoi pas mettre le non payé sous forme de flag plutôt? Ainsi ca
	 * épargnerait le fait que si jamais ils décident de mettre d'autre
	 * chose que non-payé ca va marcher pareil... A voir Pascal.
	 * 
	 * Ajouté par Kovalev le 13 Novembre a 21h
	 */
	searchEntity.setData("name", "Non-paye");
	Vector<AbstractOrmEntity> types = Orm.select(searchEntity);

	EntityList accounts = (EntityList) account.getList();
	for (AbstractOrmEntity ent : accounts)
	    if (ent.getData("stateTypeID").equals(
		    types.get(0).getPrimaryKeyValue()))
		list.addEntity(ent);

	list.setCurrentModule(getOwnedByModule());
	list.setTitle("Liste des comptes non-payés");

	return list;
    }
}
