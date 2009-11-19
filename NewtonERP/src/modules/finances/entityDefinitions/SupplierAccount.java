package modules.finances.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.Merchant;
import modules.finances.actions.PayingService;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldCurrency;
import newtonERP.orm.field.FieldDate;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Entité supplierAccount du module finances: représente les montant payables
 * aux fournisseurs et leurs dates d'échéance
 * 
 * @author Pascal Lemay
 */
public class SupplierAccount extends AbstractOrmEntity

{
    /**
     * @throws Exception if creation fails
     */
    public SupplierAccount() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new Merchant());
	AccessorManager.addAccessor(this, new Invoice());
	AccessorManager.addAccessor(this, new StateType());
	setVisibleName("Compte Fournisseur");
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldInt primaryKey = new FieldInt("Numéro", getPrimaryKeyName());
	primaryKey.setNaturalKey(true);

	FieldCurrency balance = new FieldCurrency("Solde", "balance");
	balance.setNaturalKey(true);

	FieldDate deadLine = new FieldDate("Échéance", "deadline");
	deadLine.setNaturalKey(true);

	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(primaryKey);
	fieldsInit.add(new FieldInt("Facture", new Invoice()
		.getForeignKeyName()));
	fieldsInit.add(deadLine);
	fieldsInit.add(new FieldDate("Date de paiement", "paymentDate"));
	fieldsInit.add(balance);
	fieldsInit
		.add(new FieldInt("État", new StateType().getForeignKeyName()));
	fieldsInit.add(new FieldInt("Numéro de fournisseur", new Merchant()
		.getForeignKeyName()));

	return new Fields(fieldsInit);
    }

    @Override
    public final ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	PayingService paying = new PayingService();
	paying.setOwnedByModul(getCurrentModule());

	Hashtable<String, String> actionParameters = new Hashtable<String, String>();
	actionParameters.put(getPrimaryKeyName(), "&");

	ListViewerData list = super.getList(parameters);

	list.addSpecificActionButtonList(new ActionLink("Payer", paying,
		actionParameters));

	return list;
    }

}
