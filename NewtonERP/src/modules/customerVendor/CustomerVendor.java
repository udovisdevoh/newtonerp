package modules.customerVendor;

import java.util.GregorianCalendar;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.InvoiceLine;
import modules.customerVendor.entityDefinitions.InvoiceStatus;
import modules.customerVendor.entityDefinitions.InvoiceTaxLine;
import modules.customerVendor.entityDefinitions.Merchant;
import modules.customerVendor.entityDefinitions.Tax;
import modules.customerVendor.entityDefinitions.TaxType;
import modules.materialResourcesManagement.entityDefinitions.Product;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * Représente le module client-fournisseur (facturation et factures de
 * fournisseur + le marketing )
 * 
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

	addGlobalActionMenuItem("Lignes de taxes", new BaseAction("GetList",
		new InvoiceTaxLine()));

	addGlobalActionMenuItem("Taxes", new BaseAction("GetList", new Tax()));

	setVisibleName("Clients / Fournisseurs");
    }

    public void initDB() throws Exception
    {
	super.initDB();

	TaxType type = new TaxType();
	type.setData("name", "Provincial");
	type.newE();

	TaxType type1 = new TaxType();
	type1.setData("name", "Fédéral");
	type1.newE();

	InvoiceStatus status10 = new InvoiceStatus();
	status10.setData("status", "Payé et livrée");
	status10.newE();

	InvoiceStatus status11 = new InvoiceStatus();
	status11.setData("status", "Fermée");
	status11.newE();

	InvoiceStatus status12 = new InvoiceStatus();
	status12.setData("status", "Nouveau");
	status12.newE();

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
	invoice.setData("total", 0);
	invoice.setData(new Merchant().getForeignKeyName(), 2);
	invoice.setData("date", new GregorianCalendar());
	invoice.setData("taxTotal", 0);
	invoice.setData(new InvoiceStatus().getForeignKeyName(), 3);
	invoice.setData("isForCustomer", true);
	invoice.newE();

	Tax taxe = new Tax();
	taxe.setData("name", "TPS");
	taxe.setData("code", "03123");
	taxe.setData("value", 5);
	taxe.setData("isFederalTax", true);
	taxe.setData("isStateTax", false);
	taxe.newE();

	Tax taxe1 = new Tax();
	taxe1.setData("name", "TVQ");
	taxe1.setData("code", "06544");
	taxe1.setData("value", 7.5);
	taxe1.setData("isFederalTax", false);
	taxe1.setData("isStateTax", true);
	taxe1.newE();

	InvoiceLine ligne = new InvoiceLine();
	ligne.setData(new Invoice().getForeignKeyName(), 1);
	ligne.setData(new Product().getForeignKeyName(), 1);
	ligne.setData("quantity", 10);
	ligne.setData("unitPrice", 10.0);
	ligne.newE();

	InvoiceTaxLine ligneTaxe = new InvoiceTaxLine();
	ligneTaxe.setData(new Invoice().getForeignKeyName(), 1);
	ligneTaxe.setData(new Tax().getForeignKeyName(), 1);
	ligneTaxe.newE();

	InvoiceTaxLine ligneTaxe1 = new InvoiceTaxLine();
	ligneTaxe1.setData(new Invoice().getForeignKeyName(), 1);
	ligneTaxe1.setData(new Tax().getForeignKeyName(), 2);
	ligneTaxe1.newE();
    }
}