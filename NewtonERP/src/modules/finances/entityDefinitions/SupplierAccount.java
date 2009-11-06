package modules.finances.entityDefinitions;

import java.util.Vector;

import modules.customerVendor.entityDefinitions.Customer;
import modules.customerVendor.entityDefinitions.Invoice;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDate;
import newtonERP.module.field.FieldDouble;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Entité supplierAccount du module finances: représente les montant payables
 * aux fournisseurs et leurs dates d'échéance
 * 
 * @author Pascal Lemay
 */
public class SupplierAccount extends AbstractOrmEntity implements
	PromptViewable

{
    /**
     * @throws Exception if creation fails
     */
    public SupplierAccount() throws Exception
    {
	super();
	addNaturalKey(getPrimaryKeyName());
	AccessorManager.addAccessor(this, new Customer());
	AccessorManager.addAccessor(this, new Invoice());
	AccessorManager.addAccessor(this, new StateType());
	addCurrencyFormat("balance");
	setVisibleName("Compte Fournisseur");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldInt("Facture", new Invoice()
		.getForeignKeyName()));
	fieldsInit.add(new FieldDate("Échéance", "deadline"));
	fieldsInit.add(new FieldDouble("Solde", "balance"));
	fieldsInit
		.add(new FieldInt("État", new StateType().getForeignKeyName()));
	fieldsInit.add(new FieldInt("Numéro de fournisseur", new Customer()
		.getForeignKeyName()));

	return new Fields(fieldsInit);
    }
}