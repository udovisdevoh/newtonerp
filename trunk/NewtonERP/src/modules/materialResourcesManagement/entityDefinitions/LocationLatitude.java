package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldDouble;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.Fields;

/**
 * A latitude coordinate of a location
 * 
 * @author r3hallejo
 */
public class LocationLatitude extends AbstractOrmEntity
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public LocationLatitude() throws Exception
    {
	super();
	addNaturalKey("degrees");
	addNaturalKey("minutes");
	addNaturalKey("seconds");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldsInit.add(new FieldInt("Degrés", "degrees"));
	fieldsInit.add(new FieldInt("Minutes", "minutes"));
	fieldsInit.add(new FieldDouble("Secondes", "seconds"));
	return new Fields(fieldsInit);
    }
}
