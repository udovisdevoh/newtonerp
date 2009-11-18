package modules.finances.actions;

import java.util.Hashtable;

import modules.finances.entityDefinitions.BankAccount;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.generalEntity.StaticTextEntity;

/**
 * Action DebitFromBankAccount: représente l'action de débiter le montant d'un
 * compte payer au compte de banque correspondant.
 * 
 * ps pas terminé
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
	Double montant = (Double) bankAccount.getData("balance");
	montant -= 250;// pour test
	bankAccount.setData("balance", montant);
	bankAccount.save();

	return new StaticTextEntity("Paiement effectué");
    }

}
