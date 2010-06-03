package modules.materialResourcesManagement.actions;

import java.util.Hashtable;

import modules.materialResourcesManagement.entityDefinitions.Shipping;
import modules.materialResourcesManagement.entityDefinitions.ShippingStatus;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;

/**
 * Dispatches the actions that can be done on a shipping
 * 
 * @author r3hallejo
 */
public class DispatchShippingActions extends AbstractAction
{
	/**
	 * Default constructor
	 * 
	 * @throws Exception a general exception
	 */
	public DispatchShippingActions() throws Exception
	{
		super(new Shipping());
	}

	@Override
	public AbstractEntity doAction(AbstractEntity entity,
			Hashtable<String, String> parameters) throws Exception
	{
		// The related shipping
		Shipping searchShipping = (Shipping) entity;
		Shipping retShipping = (Shipping) Orm.selectUnique(searchShipping);

		// The "Nouveau" status of shippings
		ShippingStatus searchStatusNew = new ShippingStatus();
		searchStatusNew.setData("status", "Nouveau");
		ShippingStatus statusNouveau = (ShippingStatus) Orm
				.selectUnique(searchStatusNew);

		// The "En fermeture" status of shippings
		ShippingStatus searchStatusInClosing = new ShippingStatus();
		searchStatusInClosing.setData("status", "En cours de fermeture");
		ShippingStatus statusEnFermeture = (ShippingStatus) Orm
				.selectUnique(searchStatusInClosing);

		// The "En annulation" status of shippings
		ShippingStatus searchStatusInCancel = new ShippingStatus();
		searchStatusInCancel.setData("status", "En cours d'annulation");
		ShippingStatus statusEnAnnulation = (ShippingStatus) Orm
				.selectUnique(searchStatusInCancel);

		if (retShipping.getData(new ShippingStatus().getForeignKeyName())
				.equals(statusNouveau.getPrimaryKeyValue()))
		{
			new OpenShipping().doAction(retShipping, null);
		}
		else if (retShipping.getData(new ShippingStatus().getForeignKeyName())
				.equals(statusEnFermeture.getPrimaryKeyValue()))
		{
			new CloseShipping().doAction(retShipping, null);
		}
		else if (retShipping.getData(new ShippingStatus().getForeignKeyName())
				.equals(statusEnAnnulation.getPrimaryKeyValue()))
		{
			new CancelShipping().doAction(retShipping, null);
		}

		return null;
	}
}
