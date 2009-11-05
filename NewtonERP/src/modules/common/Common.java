package modules.common;

import modules.common.entityDefinitions.Address;
import modules.common.entityDefinitions.Country;
import modules.common.entityDefinitions.State;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * A general common module for everything that could have been duplicated
 * between modules
 * 
 * @author r3hallejo
 */
public class Common extends Module
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public Common() throws Exception
    {
	super();
	setDefaultAction(new BaseAction("GetList", new Address()));
	addGlobalActionMenuItem("Adresses", new BaseAction("GetList",
		new Address()));
	addGlobalActionMenuItem("Pays",
		new BaseAction("GetList", new Country()));
	addGlobalActionMenuItem("Province / États", new BaseAction("GetList",
		new State()));
	setVisibleName("General");
    }

    public void initDB() throws Exception
    {
	super.initDB();

	Country country = new Country();
	country.setData("name", "Canada");
	country.newE();

	Country country1 = new Country();
	country1.setData("name", "Etats-Unis");
	country1.newE();

	Country country2 = new Country();
	country2.setData("name", "Mexique");
	country2.newE();

	Country country3 = new Country();
	country3.setData("name", "Chine");
	country3.newE();

	Country country4 = new Country();
	country4.setData("name", "Tibet");
	country4.newE();

	State state = new State();
	state.setData("name", "Quebec");
	state.newE();

	State state1 = new State();
	state1.setData("name", "Virginia");
	state1.newE();

	State state2 = new State();
	state2.setData("name", "New Jersey");
	state2.newE();

	State state3 = new State();
	state3.setData("name", "Washington");
	state3.newE();

	State state4 = new State();
	state4.setData("name", "Rhode Island");
	state4.newE();

	Address address = new Address();
	address.setData("streetName", "Ontario");
	address.setData("streetNumber", 255);
	address.setData("city", "Montreal");
	address.setData("postalCode", "HOH OHO");
	address.setData("telephoneNumber", "514-348-0936");
	address.setData("stateID", 1);
	address.setData("countryID", 2);
	address.newE();

	Address address1 = new Address();
	address1.setData("streetName", "Saint-Denis");
	address1.setData("streetNumber", 1064);
	address1.setData("city", "Quebec");
	address1.setData("postalCode", "ROU X23");
	address1.setData("telephoneNumber", "418-338-0936");
	address1.setData("stateID", 3);
	address1.setData("countryID", 4);
	address1.newE();

	Address address2 = new Address();
	address2.setData("streetName", "Cote");
	address2.setData("streetNumber", 1089);
	address2.setData("city", "Sherbrooke");
	address2.setData("postalCode", "BlE U14");
	address2.setData("telephoneNumber", "450-697-7080");
	address2.setData("stateID", 1);
	address2.setData("countryID", 1);
	address2.newE();
    }
}
