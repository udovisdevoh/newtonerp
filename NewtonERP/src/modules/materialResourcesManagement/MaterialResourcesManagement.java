package modules.materialResourcesManagement;

import java.util.GregorianCalendar;

import modules.customerVendor.entityDefinitions.Invoice;
import modules.materialResourcesManagement.entityDefinitions.Location;
import modules.materialResourcesManagement.entityDefinitions.LocationLatitude;
import modules.materialResourcesManagement.entityDefinitions.LocationLongitude;
import modules.materialResourcesManagement.entityDefinitions.LocationStatus;
import modules.materialResourcesManagement.entityDefinitions.Product;
import modules.materialResourcesManagement.entityDefinitions.Shipper;
import modules.materialResourcesManagement.entityDefinitions.Shipping;
import modules.materialResourcesManagement.entityDefinitions.ShippingStatus;
import modules.materialResourcesManagement.entityDefinitions.ShippingType;
import modules.materialResourcesManagement.entityDefinitions.Warehouse;
import modules.materialResourcesManagement.entityDefinitions.WorkOrder;
import modules.materialResourcesManagement.entityDefinitions.WorkOrderStatus;
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
	addGlobalActionMenuItem("Status de livraisons", new BaseAction(
		"GetList", new ShippingStatus()));
	addGlobalActionMenuItem("Type de livraisons", new BaseAction("GetList",
		new ShippingType()));
	addGlobalActionMenuItem("Expéditeurs", new BaseAction("GetList",
		new Shipper()));
	addGlobalActionMenuItem("Locations", new BaseAction("GetList",
		new Location()));
	addGlobalActionMenuItem("Status de locations", new BaseAction(
		"GetList", new LocationStatus()));
	addGlobalActionMenuItem("Ordres de travail", new BaseAction("GetList",
		new WorkOrder()));
	addGlobalActionMenuItem("Status d'ordres de travail", new BaseAction(
		"GetList", new WorkOrderStatus()));
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

	ShippingStatus status = new ShippingStatus();
	status.setData("status", "Nouveau");
	status.newE();

	ShippingStatus status2 = new ShippingStatus();
	status2.setData("status", "En cours");
	status2.newE();

	ShippingStatus status1 = new ShippingStatus();
	status1.setData("status", "Fermé");
	status1.newE();

	ShippingStatus status3 = new ShippingStatus();
	status3.setData("status", "En cours de fermeture");
	status3.newE();

	ShippingStatus status4 = new ShippingStatus();
	status4.setData("status", "En cours d'annulation");
	status4.newE();

	ShippingStatus status5 = new ShippingStatus();
	status5.setData("status", "Annulé");
	status5.newE();

	Product product = new Product();
	product.setData("code", "A9648");
	product.setData("name", "Tuile plafond 4x8");
	product.setData("quantityInStock", 1000);
	product.setData("reorderPoint", 500);
	product.setData("reservedStock", 0);
	product.setData("purchasingPrice", "12,99");
	product.setData("maxInStock", 2000);
	product.setData("sellingPrice", "24,69");
	product.setData("isProduced", false);
	product.newE();

	Product product1 = new Product();
	product1.setData("code", "A5326");
	product1.setData("name", "Boite");
	product1.setData("quantityInStock", 10000);
	product1.setData("reorderPoint", 8000);
	product1.setData("reservedStock", 0);
	product1.setData("purchasingPrice", "2,99");
	product1.setData("maxInStock", 10000);
	product1.setData("sellingPrice", "3,99");
	product1.setData("isProduced", true);
	product1.newE();

	Product product2 = new Product();
	product2.setData("code", "A89B4");
	product2.setData("name", "Colle contact 2L");
	product2.setData("quantityInStock", 40);
	product2.setData("reorderPoint", 10);
	product2.setData("reservedStock", 0);
	product2.setData("purchasingPrice", "10,99");
	product2.setData("maxInStock", 50);
	product2.setData("sellingPrice", "12,99");
	product2.setData("isProduced", false);
	product2.newE();

	Product product3 = new Product();
	product3.setData("code", "A987C");
	product3.setData("name", "Ecran LCD 17");
	product3.setData("quantityInStock", 50);
	product3.setData("reorderPoint", 20);
	product3.setData("reservedStock", 0);
	product3.setData("purchasingPrice", "10,99");
	product3.setData("maxInStock", 60);
	product3.setData("sellingPrice", "129,99");
	product3.setData("isProduced", false);
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
	LocationLatitude lat = new LocationLatitude();
	lat.setData("degrees", 45);
	lat.setData("minutes", 30);
	lat.setData("seconds", 51.13);
	lat.newE();

	LocationLongitude longitude = new LocationLongitude();
	longitude.setData("degrees", 73);
	longitude.setData("minutes", 33);
	longitude.setData("seconds", 57.07);
	longitude.newE();

	// Université Sherbrooke
	LocationLatitude lat1 = new LocationLatitude();
	lat1.setData("degrees", 45);
	lat1.setData("minutes", 22);
	lat1.setData("seconds", 50.62);
	lat1.newE();

	LocationLongitude longitude1 = new LocationLongitude();
	longitude1.setData("degrees", 71);
	longitude1.setData("minutes", 55);
	longitude1.setData("seconds", 50.09);
	longitude1.newE();

	// Université Laval
	LocationLatitude lat2 = new LocationLatitude();
	lat2.setData("degrees", 46);
	lat2.setData("minutes", 46);
	lat2.setData("seconds", 48.21);
	lat2.newE();

	LocationLongitude longitude2 = new LocationLongitude();
	longitude2.setData("degrees", 71);
	longitude2.setData("minutes", 16);
	longitude2.setData("seconds", 27.76);
	longitude2.newE();

	Location location = new Location();
	location.setData(new LocationLatitude().getForeignKeyName(), 1);
	location.setData(new LocationLongitude().getForeignKeyName(), 1);
	location.setData(new LocationStatus().getForeignKeyName(), 1);
	location.newE();

	Shipping shipping = new Shipping();
	shipping.setData(new Invoice().getForeignKeyName(), 1);
	shipping.setData(new ShippingType().getForeignKeyName(), 2);
	shipping.setData("estimatedShippingDate", new GregorianCalendar());
	shipping.setData(new Shipper().getForeignKeyName(), 1);
	shipping.setData("shippingComment", "Be extra careful");
	shipping.setData(new Location().getForeignKeyName(), 1);
	shipping.setData(new ShippingStatus().getForeignKeyName(), 1);
	shipping.newE();

	WorkOrderStatus wos = new WorkOrderStatus();
	wos.setData("status", "Nouveau");
	wos.newE();

	WorkOrderStatus wos2 = new WorkOrderStatus();
	wos2.setData("status", "En fermeture");
	wos2.newE();

	WorkOrderStatus wos1 = new WorkOrderStatus();
	wos1.setData("status", "Fermée");
	wos1.newE();
    }
}
