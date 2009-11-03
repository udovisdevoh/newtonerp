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
	setVisibleName("Ressources matérielles");
    }
}
