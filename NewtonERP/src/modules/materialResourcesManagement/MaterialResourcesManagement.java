package modules.materialResourcesManagement;

import modules.materialResourcesManagement.entityDefinitions.Address;
import modules.materialResourcesManagement.entityDefinitions.Product;
import modules.materialResourcesManagement.entityDefinitions.Warehouse;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

public class MaterialResourcesManagement extends Module
{

    public MaterialResourcesManagement() throws Exception
    {
	super();
	addGlobalActionMenuItem("Entrepôts", new BaseAction("GetList",
		new Warehouse()));
	addGlobalActionMenuItem("Adresses", new BaseAction("GetList",
		new Address()));
	addGlobalActionMenuItem("Produits", new BaseAction("GetList",
		new Product()));
    }

}
