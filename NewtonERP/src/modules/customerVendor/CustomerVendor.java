package modules.customerVendor;

import java.util.GregorianCalendar;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.customerVendor.entityDefinitions.InvoiceLine;
import modules.customerVendor.entityDefinitions.InvoiceStatus;
import modules.customerVendor.entityDefinitions.InvoiceTaxLine;
import modules.customerVendor.entityDefinitions.Merchant;
import modules.customerVendor.entityDefinitions.Tax;
import modules.materialResourcesManagement.entityDefinitions.Product;
import modules.taskModule.entityDefinitions.ActionEntity;
import modules.taskModule.entityDefinitions.EffectEntity;
import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.ModuleEntity;
import modules.taskModule.entityDefinitions.SearchEntity;
import modules.taskModule.entityDefinitions.Specification;
import modules.taskModule.entityDefinitions.TaskEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;
import newtonERP.orm.Orm;

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
	addGlobalActionMenuItem("Status de facture", new BaseAction("GetList",
		new InvoiceStatus()));
	setVisibleName("Clients / Fournisseurs");
    }

    public void initDB() throws Exception
    {
	super.initDB();

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
	invoice.setData("isForSupplier", false);
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

	// TÂCHES AUTOMATISÉES

	// CALCUL AUTOMATIQUE DE FACTURE A PARTIR D'UN INVOICELINE

	ModuleEntity module = new ModuleEntity();
	module.setData("systemName", "CustomerVendor");
	module.newE();

	ModuleEntity foundModule = (ModuleEntity) Orm.selectUnique(module);

	EntityEntity entite = new EntityEntity();
	entite.setData("systemName", "InvoiceLine");
	entite.setData(new ModuleEntity().getForeignKeyName(), foundModule
		.getPrimaryKeyValue());
	entite.newE();

	EntityEntity foundEntity = (EntityEntity) Orm.selectUnique(entite);

	SearchEntity searchEntity = new SearchEntity();
	searchEntity.setData("name", "Pour chaque InvoiceLine");
	searchEntity.setData(new EntityEntity().getForeignKeyName(),
		foundEntity.getPrimaryKeyValue());
	searchEntity.newE();

	ModuleEntity moduleAction = new ModuleEntity();
	moduleAction.setData("systemName", "CustomerVendor");
	module.newE();

	ModuleEntity foundModuleAction = (ModuleEntity) Orm
		.selectUnique(moduleAction);

	ActionEntity action = new ActionEntity();
	action.setData("systemName", "GetAndCalculateAssociatedInvoice");
	action.setData(new ModuleEntity().getForeignKeyName(),
		foundModuleAction.getPrimaryKeyValue());
	action.newE();

	SearchEntity foundSearchEntity = (SearchEntity) Orm
		.selectUnique(searchEntity);
	ActionEntity foundActionEntity = (ActionEntity) Orm
		.selectUnique(action);

	EffectEntity effet = new EffectEntity();
	effet.setData("name", "On calcule la facture pour chaque InvoiceLine");
	effet.setData(new SearchEntity().getForeignKeyName(), foundSearchEntity
		.getPrimaryKeyValue());
	effet.setData(new ActionEntity().getForeignKeyName(), foundActionEntity
		.getPrimaryKeyValue());
	effet.newE();

	Specification specification = new Specification();
	specification.setData("name", "On calcule la facture");
	specification.setData(new SearchEntity().getForeignKeyName(),
		foundSearchEntity.getPrimaryKeyValue());
	specification.newE();

	Specification foundSpecification = (Specification) Orm
		.selectUnique(specification);
	EffectEntity foundEffectEntity = (EffectEntity) Orm.selectUnique(effet);

	TaskEntity task = new TaskEntity();
	task.setData("isActive", true);
	task.setData(new Specification().getForeignKeyName(),
		foundSpecification.getPrimaryKeyValue());
	task.setData(new EffectEntity().getForeignKeyName(), foundEffectEntity
		.getPrimaryKeyValue());
	task.newE();

	// CALCUL AUTOMATIQUE DE FACTURE A PARTIR D'UN TAXLINE

	ModuleEntity module1 = new ModuleEntity();
	module1.setData("systemName", "CustomerVendor");
	module1.newE();

	ModuleEntity foundModule1 = (ModuleEntity) Orm.selectUnique(module);

	EntityEntity entite1 = new EntityEntity();
	entite1.setData("systemName", "InvoiceTaxLine");
	entite1.setData(new ModuleEntity().getForeignKeyName(), foundModule1
		.getPrimaryKeyValue());
	entite1.newE();

	EntityEntity foundEntity1 = (EntityEntity) Orm.selectUnique(entite1);

	SearchEntity searchEntity1 = new SearchEntity();
	searchEntity1.setData("name", "Pour chaque TaxLine");
	searchEntity1.setData(new EntityEntity().getForeignKeyName(),
		foundEntity1.getPrimaryKeyValue());
	searchEntity1.newE();

	ModuleEntity moduleAction1 = new ModuleEntity();
	moduleAction1.setData("systemName", "CustomerVendor");
	moduleAction1.newE();

	ModuleEntity foundModuleAction1 = (ModuleEntity) Orm
		.selectUnique(moduleAction1);

	ActionEntity action1 = new ActionEntity();
	action1
		.setData("systemName",
			"GetAndCalculateAssociatedInvoiceFromTax");
	action1.setData(new ModuleEntity().getForeignKeyName(),
		foundModuleAction1.getPrimaryKeyValue());
	action1.newE();

	SearchEntity foundSearchEntity1 = (SearchEntity) Orm
		.selectUnique(searchEntity1);
	ActionEntity foundActionEntity1 = (ActionEntity) Orm
		.selectUnique(action1);

	EffectEntity effet1 = new EffectEntity();
	effet1.setData("name", "On calcule la facture pour chaque TaxLine");
	effet1.setData(new SearchEntity().getForeignKeyName(),
		foundSearchEntity1.getPrimaryKeyValue());
	effet1.setData(new ActionEntity().getForeignKeyName(),
		foundActionEntity1.getPrimaryKeyValue());
	effet1.newE();

	Specification specification1 = new Specification();
	specification1.setData("name", "On calcule la facture pour taxes");
	specification1.setData(new SearchEntity().getForeignKeyName(),
		foundSearchEntity1.getPrimaryKeyValue());
	specification1.newE();

	Specification foundSpecification1 = (Specification) Orm
		.selectUnique(specification1);
	EffectEntity foundEffectEntity1 = (EffectEntity) Orm
		.selectUnique(effet1);

	TaskEntity task1 = new TaskEntity();
	task1.setData("isActive", true);
	task1.setData(new Specification().getForeignKeyName(),
		foundSpecification1.getPrimaryKeyValue());
	task1.setData(new EffectEntity().getForeignKeyName(),
		foundEffectEntity1.getPrimaryKeyValue());
	task1.newE();
    }
}