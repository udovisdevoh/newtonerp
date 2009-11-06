package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Represents a shipping location
 * 
 * Eventually : (Sprint 4?)
 * 
 * Work out with the google api to locate the truck on the road, track the truck
 * route.
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
	addNaturalKey(new LocationStatus().getForeignKeyName());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Latitude", "latitude"));
	fieldsInit.add(new FieldString("Longitude", "longitude"));
	fieldsInit.add(new FieldInt("Status", new LocationStatus()
		.getForeignKeyName()));
	return new Fields(fieldsInit);
    }

}
