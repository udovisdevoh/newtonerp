package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.Merchant;
import modules.finances.entityDefinitions.BankAccount;
import modules.finances.entityDefinitions.StateType;
import modules.finances.entityDefinitions.SupplierTransaction;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

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

	SupplierTransaction searchTransaction = new SupplierTransaction();
	searchTransaction.setData(new Invoice().getForeignKeyName(), invoice
		.getPrimaryKeyValue());
	Vector<AbstractOrmEntity> retTransactions = Orm
		.select(searchTransaction);

	if (invoice.getData("isForSupplier").equals(true))
	{
	    if (retTransactions.size() == 0)
	    {
		// Donc si yen a pas on en crée une nouvelle
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

		StateType type = new StateType();
		type.setData("name", "Non-paye");
		type.get().get(0).assign(transaction);

		transaction.newE();
	    }
	    else
	    {
		// On va chercher la transaction reliée
		SupplierTransaction relatedTransaction = (SupplierTransaction) retTransactions
			.get(0);

		relatedTransaction.setData("balance", invoice.getData("total"));
		relatedTransaction.setData("deadline", invoice.getData("date"));
		relatedTransaction.setData("paymentDate",
			new GregorianCalendar());
		relatedTransaction.setData(new StateType().getForeignKeyName(),
			1);
		relatedTransaction.setData(invoice.getForeignKeyName(), invoice
			.getPrimaryKeyValue());
		relatedTransaction.setData(new Merchant().getForeignKeyName(),
			invoice.getData(new Merchant().getForeignKeyName()));
		relatedTransaction.setData(new BankAccount()
			.getForeignKeyName(), 1);

		StateType type = new StateType();
		type.setData("name", "Non-paye");
		type.get().get(0).assign(relatedTransaction);

		relatedTransaction.save();
	    }

	}

	return null;
    }
}
