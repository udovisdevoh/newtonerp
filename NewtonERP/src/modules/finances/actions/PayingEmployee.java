package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import modules.finances.entityDefinitions.BankAccount;
import modules.finances.entityDefinitions.PayableEmployee;
import modules.finances.entityDefinitions.StateType;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.AlertEntity;
import newtonERP.orm.Orm;

/**
 * Action PayingEmployee: représente l'action de payer l'employé correspondant.
 * 
 * @author Pascal Lemay
 */
public class PayingEmployee extends AbstractAction
{
    /**
     * constructeur
     * @throws Exception si création fail
     */
    public PayingEmployee() throws Exception
    {
	super(new PayableEmployee());
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	PayableEmployee emp = (PayableEmployee) entity;
	// new CalculateFederalTax().doAction(emp, null);
	PayableEmployee employee = (PayableEmployee) Orm.selectUnique(emp);

	BankAccount searchBank = new BankAccount();
	searchBank.setData(new BankAccount().getPrimaryKeyName(), employee
		.getData(new BankAccount().getForeignKeyName()));

	// Le montant de la paie
	Double gain = (Double) employee.getData("gains");
	Double fedTax = (Double) employee.getData("fedTax");
	Double provTax = (Double) employee.getData("provTax");
	Double total = gain - fedTax - provTax;
	String bill = String.valueOf(total);
	// Le compte de banque pour paiement
	AbstractOrmEntity bankAccount = Orm.selectUnique(searchBank);

	// Le montant comme paramètre
	Hashtable<String, String> actionParameters = new Hashtable<String, String>();
	actionParameters.put("bill", bill);

	// Passage du compte de banque et du solde de la transaction
	// à l'action DebitFromBankAccount()
	AlertEntity alert = (AlertEntity) new DebitFromBankAccount().doAction(
		bankAccount, actionParameters);

	if (alert.getMessage().equals("Paiement effectué"))
	{
	    employee.setData("paymentDate", new GregorianCalendar());
	    employee.setData(new StateType().getForeignKeyName(), 2);
	    employee.save();
	    return null;
	}
	return alert;

    }
}
