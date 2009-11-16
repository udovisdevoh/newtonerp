package modules.finances.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldCurrency;
import newtonERP.orm.field.FieldDate;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Entité ServiceProviderAccount du module finances: représente les montant
 * payables aux fournisseurs de services (internet, électricité...)
 * 
 * @author Pascal Lemay
 */

public class ServiceProviderAccount extends AbstractOrmEntity implements
	PromptViewable
{
    /**
     * @throws Exception if creation fails
     */
    public ServiceProviderAccount() throws Exception
    {
	super();
	addNaturalKey("service");
	addNaturalKey(getPrimaryKeyName());
	AccessorManager.addAccessor(this, new ServiceProvider());
	AccessorManager.addAccessor(this, new StateType());
	setVisibleName("Comptes Fournisseurs de services");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Service", "service"));
	fieldsInit.add(new FieldDate("Échéance", "deadline"));
	fieldsInit.add(new FieldDate("Date de paiement", "paymentDate"));
	fieldsInit.add(new FieldCurrency("Solde", "balance"));
	fieldsInit
		.add(new FieldInt("État", new StateType().getForeignKeyName()));
	fieldsInit.add(new FieldInt("Numéro de Fournisseur",
		new ServiceProvider().getForeignKeyName()));

	return new Fields(fieldsInit);
    }

}
