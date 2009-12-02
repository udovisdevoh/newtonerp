package modules.finances;

import modules.common.entityDefinitions.Address;
import modules.common.entityDefinitions.Country;
import modules.common.entityDefinitions.State;
import modules.finances.actions.DisplayUnpaidServices;
import modules.finances.entityDefinitions.Bank;
import modules.finances.entityDefinitions.BankAccount;
import modules.finances.entityDefinitions.FederalWageBracket;
import modules.finances.entityDefinitions.PayableEmployee;
import modules.finances.entityDefinitions.ProvincialWageBracket;
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

	addGlobalActionMenuItem("Impôt Fed/Salaire", new BaseAction("GetList",
		new FederalWageBracket()));

	addGlobalActionMenuItem("Impôt Prov/Salaire", new BaseAction("GetList",
		new ProvincialWageBracket()));

	addGlobalActionMenuItem("Services à payer", new DisplayUnpaidServices());

	// tempo//////////////////////////////////////////////////////////
	addGlobalActionMenuItem("Employee et paies", new BaseAction("GetList",
		new PayableEmployee()));
	// //////////////////////////////////

	setVisibleName("Finances");

    }

    public void initDB() throws Exception
    {
	super.initDB();

	StateType stateType;
	Address address;
	Bank bank;
	BankAccount bankAccount;
	ServiceProvider service;

	stateType = new StateType();
	stateType.setData("name", "Non-paye");
	stateType.newE();

	stateType = new StateType();
	stateType.setData("name", "Paye");
	stateType.newE();

	address = new Address();
	address.setData("streetName", "Dummy");
	address.setData("streetNumber", 201);
	address.setData("city", "Montréal");
	address.setData("telephoneNumber", "514-000-0000");
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
	bank.setData("name", "Dummy Bank");
	bank.setData("transit", "1111");
	bank.setData(new Address().getForeignKeyName(), 1);
	bank.newE();

	bankAccount = new BankAccount();
	bankAccount.setData("folio", "2222");
	bankAccount.setData("balance", 100000.00);
	bankAccount.setData("marginBalance", 0.00);
	bankAccount.setData("margin", 0.00);
	bankAccount.setData(new Bank().getForeignKeyName(), 1);
	bankAccount.newE();

	service = new ServiceProvider();
	service.setData("name", "Geek Team inc");
	service.setData("service", "Internet");
	service.setData(new Address().getForeignKeyName(), 2);
	service.newE();

	// ---laisser ça là ou je vous en calisse une
	// Données pour impôt 2009
	FederalWageBracket fwb = new FederalWageBracket();
	fwb.setData("minBracket", 0.0);
	fwb.setData("maxBracket", 40726.0);
	fwb.setData("ajustment", 0.0);
	fwb.setData("tax", 15.0);
	fwb.newE();

	fwb.setData("minBracket", 40726.0);
	fwb.setData("maxBracket", 81452.0);
	fwb.setData("ajustment", 6109.0);
	fwb.setData("tax", 22.0);
	fwb.newE();

	fwb.setData("minBracket", 81452.0);
	fwb.setData("maxBracket", 126264.0);
	fwb.setData("ajustment", 15069.0);
	fwb.setData("tax", 26.0);
	fwb.newE();

	fwb.setData("minBracket", 126264.0);
	fwb.setData("maxBracket", 99999999.0);
	fwb.setData("ajustment", 26720.0);
	fwb.setData("tax", 29.0);
	fwb.newE();

	ProvincialWageBracket pwb = new ProvincialWageBracket();
	pwb.setData("minBracket", 0.0);
	pwb.setData("maxBracket", 40726.0);
	pwb.setData("ajustment", 0.0);
	pwb.setData("tax", 11.0);
	pwb.newE();

	pwb.setData("minBracket", 40726.0);
	pwb.setData("maxBracket", 81452.0);
	pwb.setData("ajustment", 6109.0);
	pwb.setData("tax", 15.0);
	pwb.newE();

	pwb.setData("minBracket", 81452.0);
	pwb.setData("maxBracket", 126264.0);
	pwb.setData("ajustment", 15069.0);
	pwb.setData("tax", 19.0);
	pwb.newE();

	pwb.setData("minBracket", 126264.0);
	pwb.setData("maxBracket", 99999999.0);
	pwb.setData("ajustment", 26720.0);
	pwb.setData("tax", 21.0);
	pwb.newE();
	// -----------------------------------------------------------
    }
}
