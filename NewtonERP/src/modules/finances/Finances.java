package modules.finances;

import modules.common.entityDefinitions.Address;
import modules.common.entityDefinitions.Country;
import modules.common.entityDefinitions.State;
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
	Address address;
	Bank bank;
	ServiceProvider service;

	stateType = new StateType();
	stateType.setData("name", "Non-paye");
	stateType.newE();

	stateType = new StateType();
	stateType.setData("name", "Paye");
	stateType.newE();

	address = new Address();
	address.setData("streetName", "Clark");
	address.setData("streetNumber", 201);
	address.setData("city", "Montréal");
	address.setData("telephoneNumber", "514-564-1379");
	address.setData("postalCode", "H8Y9T5");
	address.setData(new Country().getForeignKeyName(), 1);
	address.setData(new State().getForeignKeyName(), 1);
	address.newE();

	address = new Address();
	address.setData("streetName", "Saint-Laurent");
	address.setData("streetNumber", 303);
	address.setData("city", "Montréal");
	address.setData("telephoneNumber", "514-789-1379");
	address.setData("postalCode", "H8Y9T5");
	address.setData(new Country().getForeignKeyName(), 1);
	address.setData(new State().getForeignKeyName(), 1);
	address.newE();

	bank = new Bank();
	bank.setData("name", "Banque de Montréal");
	bank.setData("transit", "4001");
	bank.setData(new Address().getForeignKeyName(), 1);
	bank.newE();

	service = new ServiceProvider();
	service.setData("name", "Geek Team inc");
	service.setData("service", "Internet");
	service.setData(new Address().getForeignKeyName(), 2);
	service.newE();
    }
}
