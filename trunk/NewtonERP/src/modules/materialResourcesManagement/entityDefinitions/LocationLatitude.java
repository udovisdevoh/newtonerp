package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldDouble;
import newtonERP.orm.field.type.FieldInt;

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
	}

	@Override
	public Fields initFields() throws Exception
	{
		FieldInt degrees = new FieldInt("Degrés", "degrees");
		FieldInt minutes = new FieldInt("Minutes", "minutes");
		FieldDouble seconds = new FieldDouble("Secondes", "seconds");

		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
		fieldsInit.add(degrees);
		fieldsInit.add(minutes);
		fieldsInit.add(seconds);
		return new Fields(fieldsInit);
	}
}
