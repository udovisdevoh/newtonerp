package modules.finances;

import modules.finances.actions.DisplayUnpaidServices;
import modules.finances.entityDefinitions.Bank;
import modules.finances.entityDefinitions.BankAccount;
import modules.finances.entityDefinitions.ServiceProvider;
import modules.finances.entityDefinitions.ServiceTransaction;
import modules.finances.entityDefinitions.StateType;
import modules.finances.entityDefinitions.SupplierTransaction;
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

	setDefaultAction(new BaseAction("GetList", new SupplierTransaction()));

	addGlobalActionMenuItem("Comptes Bancaires", new BaseAction("GetList",
		new BankAccount()));

	addGlobalActionMenuItem("Banques",
		new BaseAction("GetList", new Bank()));

	addGlobalActionMenuItem("Transaction Fournisseurs", new BaseAction(
		"GetList", new SupplierTransaction()));

	addGlobalActionMenuItem("Transaction de services", new BaseAction(
		"GetList", new ServiceTransaction()));

	addGlobalActionMenuItem("Fournisseurs de services", new BaseAction(
		"GetList", new ServiceProvider()));

	addGlobalActionMenuItem("Types d'états", new BaseAction("GetList",
		new StateType()));

	addGlobalActionMenuItem("Services à payer", new DisplayUnpaidServices());

	setVisibleName("Finances");

    }

    public void initDB() throws Exception
    {
	super.initDB();

	StateType stateType;

	stateType = new StateType();
	stateType.setData("name", "Non-paye");
	stateType.newE();

	stateType = new StateType();
	stateType.setData("name", "Paye");
	stateType.newE();
    }
}
