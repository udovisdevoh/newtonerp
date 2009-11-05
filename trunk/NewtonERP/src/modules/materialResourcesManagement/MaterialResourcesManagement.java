package modules.materialResourcesManagement;

import modules.materialResourcesManagement.entityDefinitions.Product;
import modules.materialResourcesManagement.entityDefinitions.Shipper;
import modules.materialResourcesManagement.entityDefinitions.Shipping;
import modules.materialResourcesManagement.entityDefinitions.ShippingType;
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
	addGlobalActionMenuItem("Produits", new BaseAction("GetList",
		new Product()));
	addGlobalActionMenuItem("Livraison", new BaseAction("GetList",
		new Shipping()));
	addGlobalActionMenuItem("Type de livraisons", new BaseAction("GetList",
		new ShippingType()));
	addGlobalActionMenuItem("Expéditeurs", new BaseAction("GetList",
		new Shipper()));
	setVisibleName("Ressources matérielles");
    }

    public void initDB() throws Exception
    {
	super.initDB();

	Shipper shipper = new Shipper();
	shipper.setData("shipperName", "Purolator");
	shipper.newE();

	Shipper shipper1 = new Shipper();
	shipper1.setData("shipperName", "FedEx");
	shipper1.newE();

	Shipper shipper2 = new Shipper();
	shipper2.setData("shipperName", "DHL");
	shipper2.newE();

	Shipper shipper3 = new Shipper();
	shipper3.setData("shipperName", "UPS");
	shipper3.newE();

	ShippingType shippingType = new ShippingType();
	shippingType.setData("shippingType", "Ceuillette");
	shippingType.newE();

	ShippingType shippingType1 = new ShippingType();
	shippingType1.setData("shippingType", "Livraison");
	shippingType1.newE();

	ShippingType shippingType2 = new ShippingType();
	shippingType2.setData("shippingType", "Livraison express");
	shippingType2.newE();

	Product product = new Product();
	product.setData("code", "A9648");
	product.setData("name", "Tuile plafond 4x8");
	product.setData("quantityInStock", 1000);
	product.setData("reorderPoint", 500);
	product.setData("reservedStock", 200);
	product.newE();

	Product product1 = new Product();
	product1.setData("code", "A5326");
	product1.setData("name", "Boite");
	product1.setData("quantityInStock", 10000);
	product1.setData("reorderPoint", 8000);
	product1.setData("reservedStock", 59);
	product1.newE();

	Product product2 = new Product();
	product2.setData("code", "A89B4");
	product2.setData("name", "Colle contact 2L");
	product2.setData("quantityInStock", 3);
	product2.setData("reorderPoint", 0);
	product2.setData("reservedStock", 0);
	product2.newE();

	Product product3 = new Product();
	product3.setData("code", "A987C");
	product3.setData("name", "Écran LCD 17");
	product3.setData("quantityInStock", 50);
	product3.setData("reorderPoint", 20);
	product3.setData("reservedStock", 2);
	product3.newE();

	Warehouse warehouse = new Warehouse();
	warehouse.setData("warehouseName", "Arb_Mtl");
	warehouse.setData("addressID", 1);
	warehouse.newE();

	Warehouse warehouse1 = new Warehouse();
	warehouse1.setData("warehouseName", "Arb_Que");
	warehouse1.setData("addressID", 2);
	warehouse1.newE();

	Warehouse warehouse2 = new Warehouse();
	warehouse2.setData("warehouseName", "Arb_She");
	warehouse2.setData("addressID", 3);
	warehouse2.newE();
    }
}
