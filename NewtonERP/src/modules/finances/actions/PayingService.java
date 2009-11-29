package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import modules.finances.entityDefinitions.BankAccount;
import modules.finances.entityDefinitions.ServiceTransaction;
import modules.finances.entityDefinitions.StateType;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.AlertEntity;
import newtonERP.orm.Orm;

/**
 * Action PayingService: représente l'action de payer le compte correspondant.
 * 
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
	super(new ServiceTransaction());
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	ServiceTransaction trans = (ServiceTransaction) entity;
	/*
	 * ServiceTransaction searchT = new ServiceTransaction();
	 * searchT.setData(trans.getPrimaryKeyName(),
	 * trans.getPrimaryKeyValue());
	 */

	// La transaction
	AbstractOrmEntity transaction = Orm.selectUnique(trans);
	// Le solde
	String bill = String.valueOf(transaction.getData("balance"));

	BankAccount searchBank = new BankAccount();
	searchBank.setData(new BankAccount().getPrimaryKeyName(), transaction
		.getData(new BankAccount().getForeignKeyName()));
	// Le compte de banque pour paiement
	AbstractOrmEntity bankAccount = Orm.selectUnique(searchBank);

	// Le solde comme paramètre
	Hashtable<String, String> actionParameters = new Hashtable<String, String>();
	actionParameters.put("bill", bill);

	// Passage du compte de banque et du solde de la transaction
	// à l'action DebitFromBankAccount()
	AlertEntity alert = (AlertEntity) new DebitFromBankAccount().doAction(
		bankAccount, actionParameters);

	if (alert.getMessage().equals("Paiement effectué"))
	{
	    transaction.setData("paymentDate", new GregorianCalendar());
	    transaction.setData(new StateType().getForeignKeyName(), 2);
	    transaction.save();
	    return transaction.getList();
	}
	return alert;

    }
}
