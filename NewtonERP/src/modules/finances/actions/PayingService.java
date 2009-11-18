package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import modules.finances.entityDefinitions.BankAccount;
import modules.finances.entityDefinitions.ServiceProviderAccount;
import modules.finances.entityDefinitions.StateType;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Action PayingService: représente l'action de payer le compte correspondant.
 * ps pas terminé
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

	BankAccount searchEntity = new BankAccount();
	searchEntity.setData("folio", "2148");// tempo
	Vector<AbstractOrmEntity> bankAccount = Orm.select(searchEntity);
	new DebitFromBankAccount().doAction(bankAccount.get(0), null);

	account.save();
	return account.getList();

    }
}
