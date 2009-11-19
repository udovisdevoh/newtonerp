package modules.finances.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.finances.actions.PayingService;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldCurrency;
import newtonERP.orm.field.FieldDate;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;
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
	setVisibleName("Transaction de services");
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldString service = new FieldString("Service", "service");
	service.setNaturalKey(true);

	FieldInt primaryKey = new FieldInt("Numéro", getPrimaryKeyName());
	primaryKey.setNaturalKey(true);

	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(primaryKey);
	fieldsInit.add(service);
	fieldsInit.add(new FieldDate("Échéance", "deadline"));
	fieldsInit.add(new FieldDate("Date de paiement", "paymentDate"));
	fieldsInit.add(new FieldCurrency("Solde", "balance"));
	fieldsInit
		.add(new FieldInt("État", new StateType().getForeignKeyName()));
	fieldsInit.add(new FieldInt("Numéro de Fournisseur",
		new ServiceProvider().getForeignKeyName()));

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
