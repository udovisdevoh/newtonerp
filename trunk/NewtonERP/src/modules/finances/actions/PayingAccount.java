package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import modules.finances.entityDefinitions.ServiceProviderAccount;
import modules.finances.entityDefinitions.StateType;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;

/**
 * Action PayingAccount: représente l'action de payer le compte correspondant.
 * ps: pas encore au point
 * @author Pascal Lemay
 */
public class PayingAccount extends AbstractAction
{

    /**
     * constructeur
     * @throws Exception si création fail
     */
    public PayingAccount() throws Exception
    {
	super(new ServiceProviderAccount());
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {

	/*
	 * if (entity instanceof SupplierAccount) { SupplierAccount account =
	 * (SupplierAccount) entity; account.setData("paymentDate", new
	 * GregorianCalendar()); account.setData(new
	 * StateType().getForeignKeyName(), "Paye"); return account; }
	 */
	if (entity instanceof ServiceProviderAccount)
	{
	    ServiceProviderAccount account = (ServiceProviderAccount) entity;
	    account.setData("paymentDate", new GregorianCalendar());
	    account.setData(new StateType().getForeignKeyName(), 1);

	    Vector<String> searchCriterias = new Vector<String>();
	    searchCriterias.add(account.getPrimaryKeyName() + "='"
		    + account.getPrimaryKeyValue() + "'");
	    Orm.update(account, searchCriterias);
	    return account.getList();
	}
	return null;

    }
}
