package modules.materialResourcesManagement;

import modules.materialResourcesManagement.entityDefinitions.Address;
import modules.materialResourcesManagement.entityDefinitions.Product;
import modules.materialResourcesManagement.entityDefinitions.Warehouse;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * Material management resources module for the NewtonERP
 * 
 * @author r3hallejo
 */
public class MaterialResourcesManagement extends Module
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public MaterialResourcesManagement() throws Exception
    {
	super();
	setDefaultAction(new BaseAction("GetList", new Warehouse()));
	addGlobalActionMenuItem("Entrepôts", new BaseAction("GetList",
		new Warehouse()));
	addGlobalActionMenuItem("Adresses", new BaseAction("GetList",
		new Address()));
	addGlobalActionMenuItem("Produits", new BaseAction("GetList",
		new Product()));
    }

    @Override
    public String getVisibleName()
    {
	return "Ressources matérielles";
    }

    /*
     * public void initDB() throws Exception { // insert de une adresse Address
     * adresse = new Address(); adresse.setData("city", "Montréal");
     * adresse.setData("streetName", "Ontario"); adresse.setData("streetNumber",
     * 255); adresse.setData("postalCode", "HOH OHO");
     * adresse.setData("telephoneNumber", "514-343-5678");
     * 
     * Orm.insert(adresse);
     * 
     * // retrouve le addressID int addressID = (Integer) ((Address)
     * Orm.select(adresse).get(0)) .getData(adresse.getPrimaryKeyName());
     * 
     * Warehouse warehouse = new Warehouse(); warehouse.setData("warehouseName",
     * "Arb_Montréal"); warehouse.setData("addressID", addressID);
     * 
     * Orm.insert(warehouse);
     * 
     * for (int i = 0; i < 10; i++) { Product product = new Product();
     * product.setData("code", "A40898B6" + i); product.setData("name",
     * "Tuile plancher 12 * 12");
     * 
     * Orm.insert(product); }
     * 
     * }
     */
}
