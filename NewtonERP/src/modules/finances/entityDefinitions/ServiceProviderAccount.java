package modules.finances.entityDefinitions;

import java.util.Vector;

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

	// FIXME : Pourquoi la clé naturelle phone? Ceci doit planter non étant
	// donnée que tu n'a pas de clé phone dans cette classe. Ajouté par
	// Kovalev le 7 Novembre
	addNaturalKey("phone");

	addNaturalKey(getPrimaryKeyName());
	AccessorManager.addAccessor(this, new ServiceProvider());
	AccessorManager.addAccessor(this, new StateType());
	addCurrencyFormat("balance");
	setVisibleName("Comptes Fournisseurs de services");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Service", "service"));
	fieldsInit.add(new FieldDate("Échéance", "deadline"));
	fieldsInit.add(new FieldDouble("Solde", "balance"));
	fieldsInit
		.add(new FieldInt("État", new StateType().getForeignKeyName()));
	fieldsInit.add(new FieldInt("Numéro de Fournisseur",
		new ServiceProvider().getForeignKeyName()));

	return new Fields(fieldsInit);
    }

}
