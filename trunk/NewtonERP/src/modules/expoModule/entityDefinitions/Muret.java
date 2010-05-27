package modules.expoModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.Groups;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.Authentication;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.viewers.viewerData.BaseViewerData;

/**
 * Représente un muret
 * @author Guillaume Lacasse
 */
public class Muret extends AbstractOrmEntity
{
    /**
     * @throws Exception si ça fail
     */
    public Muret() throws Exception
    {
	super();
	setVisibleName("Muret");
	setDetailedDescription("muret de séparation pour l'exposition");
	AccessorManager.addAccessor(this, new KioskCustomer());
	AccessorManager.addAccessor(this, new Zone());
	AccessorManager.addAccessor(this, new WallType());
    }

    @Override
    public Fields initFields() throws Exception
    {

	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKwallTypeID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKwallTypeID);

	FieldInt client = new FieldInt("Client", new KioskCustomer()
		.getForeignKeyName());
	fieldList.add(client);

	FieldInt positionX = new FieldInt("Position sur zone", new WallType()
		.getForeignKeyName());
	fieldList.add(positionX);

	FieldInt zone = new FieldInt("Zone", new Zone().getForeignKeyName());
	fieldList.add(zone);

	return new Fields(fieldList);
    }

    @Override
    public BaseViewerData editUI(Hashtable<String, String> parameters,
	    boolean isReadOnly) throws Exception
    {
	User currentUser = Authentication.getCurrentUser();
	Groups group = (Groups) currentUser.getSingleAccessor("groupsID");

	if (!group.getData("groupName").equals("admin"))
	{

	    PluralAccessor customerList = currentUser
		    .getPluralAccessor("KioskCustomer");

	    KioskCustomer kioskCustomer = (KioskCustomer) customerList.get(0);

	    Vector<AbstractOrmEntity> entityList = this.get();

	    if (entityList.size() > 0)
	    {

		Muret muret = (Muret) entityList.get(0);

		Zone zone = (Zone) muret.getSingleAccessor("zoneID");

		Integer zoneOwnerCustomerId = zone.getCustomerOwnerId();

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
	return baseViewerData;
    }
}
