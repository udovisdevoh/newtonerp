package modules.finances.actions;

import java.util.Hashtable;

import modules.finances.entityDefinitions.BankAccount;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.generalEntity.AlertEntity;

/**
 * Action DebitFromBankAccount: called from PayingService ou PayingSupplier
 * représente l'action de débiter le montant d'une transaction à payer, du
 * compte de banque correspondant.
 * 
 * ps pas terminé
 * 
 * @author Pascal Lemay
 */
public class DebitFromBankAccount extends AbstractAction
{
    /**
     * constructeur
     * @throws Exception si création fail
     */
    public DebitFromBankAccount() throws Exception
    {
	super(new BankAccount());
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	BankAccount bankAccount = (BankAccount) entity;
	// Montant de transaction
	Double bill = Double.parseDouble(parameters.get("bill"));
	// Solde du compte de banque
	Double balance = (Double) bankAccount.getData("balance");
	// Disponible sur marge
	Double marginBalance = (Double) bankAccount.getData("marginBalance");

	if (bill <= (balance + marginBalance))
	{
	    balance -= bill;// débitage
	    if (balance < 0)
	    {
		marginBalance += balance;
		bankAccount.setData("marginBalance", marginBalance);
		balance = 0.0;
	    }
	    bankAccount.setData("balance", balance);
	    bankAccount.save();
	    return new AlertEntity("Paiement effectué");
	}

	return new AlertEntity("Paiement impossible, fond insuffisant");
    }

}
