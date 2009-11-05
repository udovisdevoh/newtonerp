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

	setVisibleName("Clients / Fournisseurs");
    }

    public void initDB() throws Exception
    {
	super.initDB();

	Customer customer = new Customer();
	customer.setData("name", "Bombardier");
	customer.setData("phone", "514-345-6575");
	customer.setData("address", "1084 Sherbrooke, Montreal");
	customer.newE();

	Customer customer1 = new Customer();
	customer1.setData("name", "Hydro-Québec");
	customer1.setData("phone", "514-780-4568");
	customer1.setData("address", "458 Sainte-Catherine, Montreal");
	customer1.newE();

	Customer customer2 = new Customer();
	customer2.setData("name", "Arborite");
	customer2.setData("phone", "514-266-2710");
	customer2.setData("address", "24 Dollard, Lasalle");
	customer2.newE();

	Vendor vendor = new Vendor();
	vendor.setData("name", "Wilsonart");
	vendor.setData("phone", "514-848-4796");
	vendor.setData("address", "1 Notre-Dame, Washington");
	vendor.newE();

	Vendor vendor1 = new Vendor();
	vendor1.setData("name", "Ciment Saint-Laurent");
	vendor1.setData("phone", "514-999-4796");
	vendor1.setData("address", "45 Mansfield, Montreal");
	vendor1.newE();

	Vendor vendor2 = new Vendor();
	vendor2.setData("name", "Volvo");
	vendor2.setData("phone", "514-848-4678");
	vendor2.setData("address", "9087 HillSide, New York");
	vendor2.newE();
    }
}