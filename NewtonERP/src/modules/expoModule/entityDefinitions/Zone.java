package modules.expoModule.entityDefinitions;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.Groups;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.Authentication;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldBool;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.viewers.viewerData.BaseViewerData;
import newtonERP.viewers.viewerData.PromptViewerData;

/**
 * Zone
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Zone extends AbstractOrmEntity
{
    private static HashSet<String> hiddenComplementaryInformation;

    /**
     * constructor
     * @throws Exception remonte
     */
    public Zone() throws Exception
    {
	super();
	setVisibleName("Zone");
	setDetailedDescription("zone d'exposition");
	AccessorManager.addAccessor(this, new Floor());
	// AccessorManager.addAccessor(this, new KioskCustomer());
	AccessorManager.addAccessor(this, new KioskInvoiceItem());
    }

    static
    {
	hiddenComplementaryInformation = new HashSet<String>();
	hiddenComplementaryInformation.add("Zone");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKzoneID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKzoneID);

	FieldInt positionX = new FieldInt("Position X", "PositionX");
	positionX.setNaturalKey(true);
	fieldList.add(positionX);

	FieldInt positionY = new FieldInt("Position Y", "PositionY");
	positionY.setNaturalKey(true);
	fieldList.add(positionY);

	/*
	 * FieldInt plugCount = new FieldInt("Nombre de prises électriques",
	 * "PlugCount"); fieldList.add(plugCount);
	 */

	FieldBool isActive = new FieldBool("Acitvée?", "isActive");
	isActive.setNaturalKey(false);
	fieldList.add(isActive);

	/*
	 * FieldInt client = new FieldInt("Client", "kioskCustomerID");
	 * client.setNaturalKey(true); fieldList.add(client);
	 */

	FieldInt kioskInvoiceItem = new FieldInt("Élément de facture",
		"kioskInvoiceItemID");
	kioskInvoiceItem.setNaturalKey(false);
	fieldList.add(kioskInvoiceItem);

	FieldInt floorID = new FieldInt("Plancher", "floorID");
	fieldList.add(floorID);
	return new Fields(fieldList);
    }

    /**
     * @return retourne le nom de kiosque associé à cette zone
     * @throws Exception si ça fail
     */
    public String getKioskName() throws Exception
    {
	KioskInvoiceItem kioskInvoiceItem = (KioskInvoiceItem) getSingleAccessor("kioskInvoiceItemID");

	KioskInvoice kioskInvoice = (KioskInvoice) kioskInvoiceItem
		.getSingleAccessor("kioskInvoiceID");

	KioskCustomer kioskCustomer = (KioskCustomer) kioskInvoice
		.getSingleAccessor("kioskCustomerID");

	return kioskCustomer.getNaturalKeyDescription();
    }

    /**
     * @param wallTypeName nom du type de muret
     * @param x position x de la zone
     * @param y position y de la zone
     * @return s'il y a un muret à la position indiquée
     * @throws Exception si ça fail
     */
    public boolean isMuretAt(String wallTypeName, int x, int y)
	    throws Exception
    {
	WallType wallType = (WallType) Orm.getOrCreateEntity(new WallType(),
		"Name", wallTypeName);

	int wallTypeId = wallType.getPrimaryKeyValue();

	Muret muret = new Muret();
	muret.setData(wallType.getForeignKeyName(), wallTypeId);
	muret.setData("zoneID", getPrimaryKeyValue());

	Vector<AbstractOrmEntity> resultSet = muret.get();

	return resultSet.size() > 0;
    }

    public BaseViewerData editUI(Hashtable<String, String> parameters,
	    boolean isReadOnly) throws Exception
    {
	User currentUser = Authentication.getCurrentUser();
	Groups group = (Groups) currentUser.getSingleAccessor("groupsID");

	boolean isAdmin = group.getData("groupName").equals("admin");

	Vector<AbstractOrmEntity> entityList = this.get();

	if (!isAdmin)
	{

	    if (entityList.size() > 0)
	    {
		Zone zone = (Zone) entityList.get(0);

		KioskInvoiceItem kioskInvoiceItem = (KioskInvoiceItem) zone
			.getSingleAccessor("kioskInvoiceItemID");

		KioskInvoice kioskInvoice = (KioskInvoice) kioskInvoiceItem
			.getSingleAccessor("kioskInvoiceID");

		KioskCustomer kioskCustomer = (KioskCustomer) kioskInvoice
			.getSingleAccessor("kioskCustomerID");

		Integer zoneOwnerCustomerId = kioskCustomer
			.getPrimaryKeyValue();

		if (zoneOwnerCustomerId.equals(kioskCustomer
			.getPrimaryKeyValue())
			&& !((Boolean) zone.getData("isActive")))
		{
		    isReadOnly = false;
		}
		else
		{
		    isReadOnly = true;
		}
	    }
	    else
	    {
		isReadOnly = false;
	    }
	}
	else
	{
	    isReadOnly = false;
	}

	BaseViewerData baseViewerData = super.editUI(parameters, isReadOnly);

	if (!isAdmin)
	{
	    PromptViewerData promptViewerData = (PromptViewerData) baseViewerData;
	    promptViewerData.getData().getFields().getField("isActive")
		    .setReadOnly(true);
	}

	if (entityList.size() > 0)
	{
	    Zone zone = (Zone) entityList.get(0);
	    for (String complementaryInfoLine : zone
		    .getComplementaryInfoLineList())
		baseViewerData.addComplementaryInfoLine(complementaryInfoLine);
	}

	return baseViewerData;
    }

    private Vector<String> getComplementaryInfoLineList() throws Exception
    {
	Vector<String> complementaryInfoLineList = new Vector<String>();

	KioskInvoiceItem kioskInvoiceItem = (KioskInvoiceItem) getSingleAccessor("kioskInvoiceItemID");

	KioskInvoice kioskInvoice = (modules.expoModule.entityDefinitions.KioskInvoice) kioskInvoiceItem
		.getSingleAccessor("kioskInvoiceID");

	KioskCustomer kioskCustomer = (KioskCustomer) kioskInvoice
		.getSingleAccessor("kioskCustomerID");

	PluralAccessor invoiceList = kioskCustomer
		.getPluralAccessor("KioskInvoice");

	for (AbstractOrmEntity invoice : invoiceList)
	{
	    PluralAccessor invoiceItemList = invoice
		    .getPluralAccessor("KioskInvoiceItem");

	    for (AbstractOrmEntity invoiceItem : invoiceItemList)
	    {
		Option option = (Option) invoiceItem
			.getSingleAccessor("optionID");
		String optionName = option.getDataString("name");

		if (!hiddenComplementaryInformation.contains(optionName))
		    complementaryInfoLineList.add(optionName);
	    }
	}

	return complementaryInfoLineList;
    }

    /**
     * @return ID du client propriétaire de la zone
     * @throws Exception si ça fail
     */
    public Integer getCustomerOwnerId() throws Exception
    {
	KioskInvoiceItem kioskInvoiceItem = (KioskInvoiceItem) getSingleAccessor("kioskInvoiceItemID");

	KioskInvoice kioskInvoice = (KioskInvoice) kioskInvoiceItem
		.getSingleAccessor("kioskInvoiceID");

	KioskCustomer kioskCustomer = (KioskCustomer) kioskInvoice
		.getSingleAccessor("kioskCustomerID");

	return kioskCustomer.getPrimaryKeyValue();
    }
}
