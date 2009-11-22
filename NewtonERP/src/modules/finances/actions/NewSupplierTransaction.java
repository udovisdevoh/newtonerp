package modules.finances.actions;

import java.util.Hashtable;

import modules.customerVendor.entityDefinitions.Invoice;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * Action NewSupplierTransaction: représente l'action de créer une nouvelle
 * transaction de fournisseur à partir d'une facture de fournisseur.
 * 
 * Cera "callé" lors de la création d'une nouvelle Invoice fournisseur.
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
	System.out.println("Works");
	// Invoice invoice = (Invoice) entity;
	// SupplierTransaction transaction = new SupplierTransaction();

	// transaction.setData("balance", invoice.getData("total"));
	// transaction.setData("deadline", invoice.getData("date"));
	// transaction.setData(invoice.getForeignKeyName(), invoice
	// .getPrimaryKeyValue());
	// transaction.setData(new Merchant().getForeignKeyName(), invoice
	// .getData(new Merchant().getForeignKeyName()));

	// transaction.newE();

	// StateType type = new StateType();
	// type.setData("name", "Non-paye");

	// type.get().get(0).assign(transaction);

	// return new StaticTextEntity(null);
	return null;
    }
}
