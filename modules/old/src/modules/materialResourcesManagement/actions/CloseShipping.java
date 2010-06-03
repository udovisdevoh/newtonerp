package modules.materialResourcesManagement.actions;

import java.util.Hashtable;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.materialResourcesManagement.entityDefinitions.Shipping;
import modules.materialResourcesManagement.entityDefinitions.ShippingStatus;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;

/**
 * Closes a shipping by remove the quantities from the stock of the warehouse
 * 
 * @author r3hallejo
 */
public class CloseShipping extends AbstractAction
{
	/**
	 * Default constructor
	 * 
	 * @throws Exception a general exception
	 */
	public CloseShipping() throws Exception
	{
		super(new Shipping());
	}

	@Override
	public AbstractEntity doAction(AbstractEntity entity,
			Hashtable<String, String> parameters) throws Exception
	{
		// The shipping
		Shipping retShipping = (Shipping) entity;

		// The "en cours de fermeture" status
		ShippingStatus searchStatus = new ShippingStatus();
		searchStatus.setData("status", "Fermé");
		ShippingStatus statusFerme = (ShippingStatus) Orm
				.selectUnique(searchStatus);

		retShipping.setData(new ShippingStatus().getForeignKeyName(),
				statusFerme.getPrimaryKeyValue());
		retShipping.save();

		Invoice searchInvoice = new Invoice();
		searchInvoice.setData(searchInvoice.getPrimaryKeyName(), retShipping
				.getData(new Invoice().getForeignKeyName()));
		Invoice retInvoice = (Invoice) Orm.selectUnique(searchInvoice);

		new RemoveProductQuantityFromReservedStock().doAction(retInvoice, null);

		return null;
	}
}
