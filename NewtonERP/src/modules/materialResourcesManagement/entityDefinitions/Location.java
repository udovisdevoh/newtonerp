package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

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
public class Location extends AbstractOrmEntity implements PromptViewable
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
	AccessorManager.addAccessor(this, new Latitude());
	AccessorManager.addAccessor(this, new Longitude());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldsInit.add(new FieldInt("Status", new LocationStatus()
		.getForeignKeyName()));
	fieldsInit.add(new FieldInt("Latitude", new Latitude()
		.getForeignKeyName()));
	fieldsInit.add(new FieldInt("Longitude", new Longitude()
		.getForeignKeyName()));
	return new Fields(fieldsInit);
    }
}
