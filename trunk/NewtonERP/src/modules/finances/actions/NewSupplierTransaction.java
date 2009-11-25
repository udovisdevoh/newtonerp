package modules.finances.actions;

import java.util.Hashtable;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.Merchant;
import modules.finances.entityDefinitions.StateType;
import modules.finances.entityDefinitions.SupplierTransaction;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.generalEntity.StaticTextEntity;

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
	transaction.setData(invoice.getForeignKeyName(), invoice
		.getPrimaryKeyValue());
	transaction.setData(new Merchant().getForeignKeyName(), invoice
		.getData(new Merchant().getForeignKeyName()));

	transaction.newE();

	StateType type = new StateType();
	type.setData("name", "Non-paye");

	type.get().get(0).assign(transaction);

	return new StaticTextEntity("Transaction ajoutée");
    }
}
