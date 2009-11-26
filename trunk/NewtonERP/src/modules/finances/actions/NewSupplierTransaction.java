package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.Merchant;
import modules.finances.entityDefinitions.BankAccount;
import modules.finances.entityDefinitions.StateType;
import modules.finances.entityDefinitions.SupplierTransaction;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * Action NewSupplierTransaction: représente l'action de créer une nouvelle
 * transaction de fournisseur à partir d'une facture de fournisseur.
 * 
 * Sera "callé" lors de la création d'une nouvelle Invoice fournisseur.
 * @author Pascal Lemay
 */
public class NewSupplierTransaction extends AbstractAction
{
    /**
     * constructeur
     * @throws Exception si création fail
     */
    public NewSupplierTransaction() throws Exception
    {
	super(new Invoice());
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Invoice invoice = (Invoice) entity;
	SupplierTransaction transaction = new SupplierTransaction();

	transaction.setData("balance", invoice.getData("total"));

	transaction.setData("deadline", invoice.getData("date"));

	transaction.setData("paymentDate", new GregorianCalendar());

	transaction.setData(new StateType().getForeignKeyName(), 1);

	transaction.setData(invoice.getForeignKeyName(), invoice
		.getPrimaryKeyValue());

	transaction.setData(new Merchant().getForeignKeyName(), invoice
		.getData(new Merchant().getForeignKeyName()));

	transaction.setData(new BankAccount().getForeignKeyName(), 1);

	transaction.newE();

	StateType type = new StateType();
	type.setData("name", "Non-paye");

	type.get().get(0).assign(transaction);

	// Laisser a null pour pouvoir modifier la facture
	return null;
    }
}
