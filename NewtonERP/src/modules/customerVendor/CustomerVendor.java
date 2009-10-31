package modules.customerVendor;

import modules.customerVendor.entityDefinitions.Customer;
import modules.customerVendor.entityDefinitions.CustomerInvoice;
import modules.customerVendor.entityDefinitions.Vendor;
import modules.customerVendor.entityDefinitions.VendorInvoice;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * Représente le module client-fournisseur (facturation et factures de
 * fournisseur + le marketing )
 * @author r3lemaypa Guillaume cloutierJo
 */
public class CustomerVendor extends Module
{
    /**
     * constructeur
     * @throws Exception remonte
     */
    public CustomerVendor() throws Exception
    {
	super();
	setDefaultAction(new BaseAction("GetList", new Customer()));

	addGlobalActionMenuItem("Clients", new BaseAction("GetList",
		new Customer()));

	addGlobalActionMenuItem("Facture de clients", new BaseAction("GetList",
		new CustomerInvoice()));

	addGlobalActionMenuItem("Fournisseurs", new BaseAction("GetList",
		new Vendor()));

	addGlobalActionMenuItem("Facture de fournisseur", new BaseAction(
		"GetList", new VendorInvoice()));
    }

    @Override
    public String getVisibleName()
    {
	return "Clients / Fournisseurs";
    }
}