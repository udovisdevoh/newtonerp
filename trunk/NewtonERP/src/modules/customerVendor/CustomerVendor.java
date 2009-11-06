package modules.customerVendor;

import modules.customerVendor.entityDefinitions.Customer;
import modules.customerVendor.entityDefinitions.Invoice;
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

	addGlobalActionMenuItem("Factures", new BaseAction("GetList",
		new Invoice()));

	setVisibleName("Clients / Fournisseurs");
    }

    public void initDB() throws Exception
    {
	super.initDB();
	/*
	 * Customer customer3 = new Customer(); customer3.setData("name",
	 * "Nous"); customer3.setData("phone", "555-5555");
	 * customer3.setData("address", "666 St-Lucifer"); customer3.newE();
	 * 
	 * Customer customer = new Customer(); customer.setData("name",
	 * "Bombardier"); customer.setData("phone", "514-345-6575");
	 * customer.setData("address", "1084 Sherbrooke, Montreal");
	 * customer.newE();
	 * 
	 * Customer customer1 = new Customer(); customer1.setData("name",
	 * "Hydro-Québec"); customer1.setData("phone", "514-780-4568");
	 * customer1.setData("address", "458 Sainte-Catherine, Montreal");
	 * customer1.newE();
	 * 
	 * Customer customer2 = new Customer(); customer2.setData("name",
	 * "Arborite"); customer2.setData("phone", "514-266-2710");
	 * customer2.setData("address", "24 Dollard, Lasalle");
	 * customer2.newE();
	 */

    }
}