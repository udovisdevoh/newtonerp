package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import modules.finances.entityDefinitions.ServiceProviderAccount;
import modules.finances.entityDefinitions.StateType;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * Action PayingService: représente l'action de payer le compte correspondant.
 * ps pas terminer
 * @author Pascal Lemay
 */
public class PayingService extends AbstractAction
{

    /**
     * constructeur
     * @throws Exception si création fail
     */
    public PayingService() throws Exception
    {
	super(new ServiceProviderAccount());
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {

	ServiceProviderAccount account = (ServiceProviderAccount) entity;
	account.setData("paymentDate", new GregorianCalendar());
	account.setData(new StateType().getForeignKeyName(), 1);

	/*
	 * Vector<String> searchCriterias = new Vector<String>();
	 * searchCriterias.add(account.getPrimaryKeyName() + "='" +
	 * account.getPrimaryKeyValue() + "'"); Orm.update(account,
	 * searchCriterias);
	 */
	account.save();
	return account.getList();

    }
}
