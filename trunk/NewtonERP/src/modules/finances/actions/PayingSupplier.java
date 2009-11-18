package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import modules.finances.entityDefinitions.BankAccount;
import modules.finances.entityDefinitions.StateType;
import modules.finances.entityDefinitions.SupplierAccount;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Action PayingSupplier: représente l'action de payer le compte correspondant.
 * 
 * ps pas terminé
 * @author Pascal Lemay
 */
public class PayingSupplier extends AbstractAction
{

    /**
     * constructeur
     * @throws Exception si création fail
     */
    public PayingSupplier() throws Exception
    {
	super(new SupplierAccount());
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {

	SupplierAccount account = (SupplierAccount) entity;
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
