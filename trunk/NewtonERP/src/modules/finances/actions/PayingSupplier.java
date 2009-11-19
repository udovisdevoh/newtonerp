package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import modules.finances.entityDefinitions.BankAccount;
import modules.finances.entityDefinitions.StateType;
import modules.finances.entityDefinitions.SupplierTransaction;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.AlertEntity;
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
	super(new SupplierTransaction());
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {

	SupplierTransaction transaction = (SupplierTransaction) entity;

	Double bill = (Double) transaction.getList(parameters).getEntity().get(
		0).getData("balance");
	String billParam = String.valueOf(bill);

	BankAccount searchEntity = new BankAccount();
	searchEntity.setData("folio", "2148");// tempo
	AbstractOrmEntity bankAccount = Orm.selectUnique(searchEntity);

	Hashtable<String, String> actionParameters = new Hashtable<String, String>();
	actionParameters.put("bill", billParam);

	AlertEntity alert = (AlertEntity) new DebitFromBankAccount().doAction(
		bankAccount, actionParameters);

	if (alert.getMessage().equals("Paiement effectué"))
	{
	    transaction.setData("paymentDate", new GregorianCalendar());
	    transaction.setData(new StateType().getForeignKeyName(), 1);
	    transaction.save();
	    return transaction.getList();
	}
	return alert;

    }
}
