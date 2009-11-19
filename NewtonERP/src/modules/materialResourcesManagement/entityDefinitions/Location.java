package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.Fields;

/**
 * Represents a shipping location
 * 
 * Eventually : (Sprint 4?)
 * 
 * Work out with the google api to locate the truck on the road, track the
 * trucks route to destination.
 * 
 * @author r3hallejo
 */
public class Location extends AbstractOrmEntity
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public Location() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new LocationStatus());
	AccessorManager.addAccessor(this, new LocationLatitude());
	AccessorManager.addAccessor(this, new LocationLongitude());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldsInit.add(new FieldInt("Status", new LocationStatus()
		.getForeignKeyName()));
	fieldsInit.add(new FieldInt("Latitude", new LocationLatitude()
		.getForeignKeyName()));
	fieldsInit.add(new FieldInt("Longitude", new LocationLongitude()
		.getForeignKeyName()));
	return new Fields(fieldsInit);
    }
}
