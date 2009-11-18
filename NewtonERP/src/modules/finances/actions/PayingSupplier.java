package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import modules.finances.entityDefinitions.StateType;
import modules.finances.entityDefinitions.SupplierAccount;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

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
	account.save();
	return account.getList();

    }
}
