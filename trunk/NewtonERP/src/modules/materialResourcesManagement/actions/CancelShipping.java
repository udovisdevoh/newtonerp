package modules.materialResourcesManagement.actions;

import java.util.Hashtable;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.InvoiceStatus;
import modules.materialResourcesManagement.entityDefinitions.Shipping;
import modules.materialResourcesManagement.entityDefinitions.ShippingStatus;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;

/**
 * Action to cancel a shipping
 * 
 * @author r3hallejo
 */
public class CancelShipping extends AbstractAction
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public CancelShipping() throws Exception
    {
	super(new Shipping());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	// Le shipping
	Shipping retShipping = (Shipping) entity;

	// Facture reliée
	Invoice searchInvoice = new Invoice();
	searchInvoice.setData(searchInvoice.getPrimaryKeyName(), retShipping
		.getData(new Invoice().getForeignKeyName()));
	Invoice retInvoice = (Invoice) Orm.selectUnique(searchInvoice);

	// Status annulé
	ShippingStatus searchStatus = new ShippingStatus();
	searchStatus.setData("status", "Annulé");
	ShippingStatus statusAnnule = (ShippingStatus) Orm
		.selectUnique(searchStatus);

	// Status annulé de facture
	InvoiceStatus searchInvoiceStatus = new InvoiceStatus();
	searchInvoiceStatus.setData("status", "Annulé");
	InvoiceStatus statusFactureAnnule = (InvoiceStatus) Orm
		.selectUnique(searchInvoiceStatus);

	// On mets le status a annulé sur le shipping et sur le invoice
	retShipping.setData(new ShippingStatus().getForeignKeyName(),
		statusAnnule.getPrimaryKeyValue());
	retInvoice.setData(new InvoiceStatus().getForeignKeyName(),
		statusFactureAnnule.getPrimaryKeyValue());

	retShipping.save();
	retInvoice.save();

	new RemoveAndAddProductQuantityInStock().doAction(retShipping, null);

	return null;
    }
}
