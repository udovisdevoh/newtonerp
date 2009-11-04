package modules.finances;

import modules.finances.entityDefinitions.ServiceProvider;
import modules.finances.entityDefinitions.ServiceProviderAccount;
import modules.finances.entityDefinitions.SupplierAccount;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * Représente le module finances
 * 
 * @author r3lemaypa
 */
public class Finances extends Module
{
    /**
     * constructeur
     * @throws Exception remonte
     */
    public Finances() throws Exception
    {
	super();

	setDefaultAction(new BaseAction("GetList", new SupplierAccount()));

	addGlobalActionMenuItem("Comptes Fournisseurs", new BaseAction(
		"GetList", new SupplierAccount()));

	addGlobalActionMenuItem("Comptes Fournisseurs de services",
		new BaseAction("GetList", new ServiceProviderAccount()));

	addGlobalActionMenuItem("Comptes Fournisseurs de services",
		new BaseAction("GetList", new ServiceProvider()));

	setVisibleName("Finances");

    }
}
