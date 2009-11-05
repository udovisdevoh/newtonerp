package modules.finances;

import modules.finances.entityDefinitions.ServiceProvider;
import modules.finances.entityDefinitions.ServiceProviderAccount;
import modules.finances.entityDefinitions.StateType;
import modules.finances.entityDefinitions.SupplierAccount;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * Représente le module finances
 * 
 * @author Pascal Lemay
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

	addGlobalActionMenuItem("Fournisseurs de services", new BaseAction(
		"GetList", new ServiceProvider()));

	addGlobalActionMenuItem("Types d'états", new BaseAction("GetList",
		new StateType()));

	setVisibleName("Finances");

    }

    public void initDB() throws Exception
    {
	super.initDB();

	StateType stateType;

	stateType = new StateType();
	stateType.setData("name", "Paye");
	stateType.newE();

	stateType = new StateType();
	stateType.setData("name", "Non-paye");
	stateType.newE();

	stateType = new StateType();
	stateType.setData("name", "Client bannit");
	stateType.newE();
    }
}
