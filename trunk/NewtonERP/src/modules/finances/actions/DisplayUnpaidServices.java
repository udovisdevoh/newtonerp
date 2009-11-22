package modules.finances.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.finances.entityDefinitions.ServiceTransaction;
import modules.finances.entityDefinitions.StateType;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.viewers.viewerData.ListViewerData;

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
	ServiceTransaction account = new ServiceTransaction();
	ListViewerData list = new ListViewerData(account);

	StateType searchEntity = new StateType();

	searchEntity.setData(searchEntity.getPrimaryKeyName(), 1);
	Vector<AbstractOrmEntity> types = Orm.select(searchEntity);

	ListViewerData accounts = account.getList();
	for (AbstractOrmEntity ent : accounts)
	    if (ent.getData("stateTypeID").equals(
		    types.get(0).getPrimaryKeyValue()))
		list.addEntity(ent);

	list.setCurrentModule(getOwnedByModule());
	list.setTitle("Liste des comptes non-payés");

	return list;
    }

}
