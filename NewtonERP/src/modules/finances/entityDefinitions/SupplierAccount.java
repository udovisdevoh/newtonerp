package modules.finances.entityDefinitions;

import java.util.Vector;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.Vendor;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDate;
import newtonERP.module.field.FieldDouble;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
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

	AccessorManager.addAccessor(this, new Vendor());
	AccessorManager.addAccessor(this, new Invoice());
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
	fieldsInit.add(new FieldString("État", "state"));
	fieldsInit.add(new FieldInt("Numéro de fournisseur", new Vendor()
		.getForeignKeyName()));

	return new Fields(fieldsInit);
    }
}
