package modules.customerVendor;

import java.util.GregorianCalendar;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.InvoiceLine;
import modules.customerVendor.entityDefinitions.Merchant;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * Représente le module client-fournisseur (facturation et factures de
 * fournisseur + le marketing )
 * @author r3lemaypa Guillaume cloutierJo r3hallejo
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
	setDefaultAction(new BaseAction("GetList", new Merchant()));

	addGlobalActionMenuItem("Commerçants", new BaseAction("GetList",
		new Merchant()));

	addGlobalActionMenuItem("Factures", new BaseAction("GetList",
		new Invoice()));

	addGlobalActionMenuItem("Lignes de factures", new BaseAction("GetList",
		new InvoiceLine()));

	setVisibleName("Clients / Fournisseurs");
    }

    public void initDB() throws Exception
    {
	super.initDB();

	Merchant customer3 = new Merchant();
	customer3.setData("name", "Nous");
	customer3.setData("addressID", 1);
	customer3.newE();

	Merchant customer = new Merchant();
	customer.setData("name", "Bombardier");
	customer.setData("addressID", 2);
	customer.newE();

	Merchant customer1 = new Merchant();
	customer1.setData("name", "Hydro-Québec");
	customer1.setData("addressID", 3);
	customer1.newE();

	Merchant customer2 = new Merchant();
	customer2.setData("name", "Arborite");
	customer2.setData("addressID", 3);
	customer2.newE();

	Invoice invoice = new Invoice();
	invoice.setData("total", 45.16);
	invoice.setData(new Merchant().getForeignKeyName(), 2);
	invoice.setData("date", new GregorianCalendar());
	invoice.newE();
    }
}