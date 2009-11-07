package modules.materialResourcesManagement;

import java.util.GregorianCalendar;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.materialResourcesManagement.entityDefinitions.Latitude;
import modules.materialResourcesManagement.entityDefinitions.Location;
import modules.materialResourcesManagement.entityDefinitions.LocationStatus;
import modules.materialResourcesManagement.entityDefinitions.Longitude;
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
	addGlobalActionMenuItem("Locations", new BaseAction("GetList",
		new Location()));
	addGlobalActionMenuItem("Type de locations", new BaseAction("GetList",
		new LocationStatus()));
	addGlobalActionMenuItem("Latitudes", new BaseAction("GetList",
		new Latitude()));
	addGlobalActionMenuItem("Longitudes", new BaseAction("GetList",
		new Longitude()));
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
	product.setData("purchasingPrice", 12.99);
	product.setData("sellingPrice", 24.69);
	product.newE();

	Product product1 = new Product();
	product1.setData("code", "A5326");
	product1.setData("name", "Boite");
	product1.setData("quantityInStock", 10000);
	product1.setData("reorderPoint", 8000);
	product1.setData("reservedStock", 59);
	product1.setData("purchasingPrice", 2.99);
	product1.setData("sellingPrice", 3.99);
	product1.newE();

	Product product2 = new Product();
	product2.setData("code", "A89B4");
	product2.setData("name", "Colle contact 2L");
	product2.setData("quantityInStock", 3);
	product2.setData("reorderPoint", 0);
	product2.setData("reservedStock", 0);
	product2.setData("purchasingPrice", 10.99);
	product2.setData("sellingPrice", 12.99);
	product2.newE();

	Product product3 = new Product();
	product3.setData("code", "A987C");
	product3.setData("name", "Écran LCD 17");
	product3.setData("quantityInStock", 50);
	product3.setData("reorderPoint", 20);
	product3.setData("reservedStock", 2);
	product3.setData("purchasingPrice", 109.99);
	product3.setData("sellingPrice", 129.99);
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

	LocationStatus locStatus = new LocationStatus();
	locStatus.setData("status", "At warehouse");
	locStatus.newE();

	LocationStatus locStatus1 = new LocationStatus();
	locStatus1.setData("status", "On course");
	locStatus1.newE();

	LocationStatus locStatus2 = new LocationStatus();
	locStatus2.setData("status", "At destination");
	locStatus2.newE();

	// Cegep du Vieux-Montréal
	Latitude lat = new Latitude();
	lat.setData("degrees", 45);
	lat.setData("minutes", 30);
	lat.setData("seconds", 51.13);
	lat.newE();

	Longitude longitude = new Longitude();
	longitude.setData("degrees", 73);
	longitude.setData("minutes", 33);
	longitude.setData("seconds", 57.07);
	longitude.newE();

	// Université Sherbrooke
	Latitude lat1 = new Latitude();
	lat1.setData("degrees", 45);
	lat1.setData("minutes", 22);
	lat1.setData("seconds", 50.62);
	lat1.newE();

	Longitude longitude1 = new Longitude();
	longitude1.setData("degrees", 71);
	longitude1.setData("minutes", 55);
	longitude1.setData("seconds", 50.09);
	longitude1.newE();

	// Université Laval
	Latitude lat2 = new Latitude();
	lat2.setData("degrees", 46);
	lat2.setData("minutes", 46);
	lat2.setData("seconds", 48.21);
	lat2.newE();

	Longitude longitude2 = new Longitude();
	longitude2.setData("degrees", 71);
	longitude2.setData("minutes", 16);
	longitude2.setData("seconds", 27.76);
	longitude2.newE();

	Location location = new Location();
	location.setData(new Latitude().getForeignKeyName(), 1);
	location.setData(new Longitude().getForeignKeyName(), 1);
	location.setData(new LocationStatus().getForeignKeyName(), 1);
	location.newE();

	Shipping shipping = new Shipping();
	shipping.setData(new Invoice().getForeignKeyName(), 1);
	shipping.setData(new ShippingType().getForeignKeyName(), 2);
	shipping.setData("estimatedShippingDate", new GregorianCalendar());
	shipping.setData(new Shipper().getForeignKeyName(), 1);
	shipping.setData("shippingComment", "Be extra careful");
	shipping.setData(new Location().getForeignKeyName(), 1);
	shipping.newE();
    }
}
