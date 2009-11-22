package modules.finances.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.finances.actions.PayingService;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldCurrency;
import newtonERP.orm.field.type.FieldDate;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Entité ServiceTransaction du module finances: représente les montants
 * payables aux fournisseurs de services (internet, électricité...)et leurs
 * dates d'échéance
 * 
 * @author Pascal Lemay
 */

public class ServiceTransaction extends AbstractOrmEntity
{
    /**
     * @throws Exception if creation fails
     */
    public ServiceTransaction() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new ServiceProvider());
	AccessorManager.addAccessor(this, new StateType());
	AccessorManager.addAccessor(this, new BankAccount());
	setVisibleName("Transaction de services");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	FieldInt primaryKey = new FieldInt("Numéro Transaction",
		getPrimaryKeyName());
	primaryKey.setNaturalKey(true);
	fieldsInit.add(primaryKey);
	FieldString service = new FieldString("Service", "service");
	// service.setNaturalKey(true); //on peut avoir plusieurs transaction
	// pour le même service
	fieldsInit.add(service);
	fieldsInit.add(new FieldDate("Échéance", "deadline"));
	fieldsInit.add(new FieldDate("Date de paiement", "paymentDate"));
	fieldsInit.add(new FieldCurrency("Solde", "balance"));
	fieldsInit
		.add(new FieldInt("État", new StateType().getForeignKeyName()));
	fieldsInit.add(new FieldInt("Nom du Fournisseur", new ServiceProvider()
		.getForeignKeyName()));
	fieldsInit.add(new FieldInt("Folio pour paiement:", new BankAccount()
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
