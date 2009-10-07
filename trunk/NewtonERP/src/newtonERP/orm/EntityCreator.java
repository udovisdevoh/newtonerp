package newtonERP.orm;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Vector;

import newtonERP.orm.exceptions.OrmEntityCreationException;
import newtonERP.orm.exceptions.OrmException;

/**
 * 
 * @author r3hallejo
 * 
 *         Class used to create the entities in the select statement of the orm
 *         because it's not the orm's responsability. By doing that I am trying
 *         to respect the SRP (Single responsability principle) principle
 */
public class EntityCreator
{
    private static Vector<Ormizable> returnedEntities = new Vector<Ormizable>();

    /**
     * Default constructor used to create the entities from the result set
     * 
     * @param rs the result set from which we will create our entities
     * @param searchEntity the entities from which we searched
     * @return a vector of ormizable entities
     * @throws OrmException an exception that can occur in the orm
     */
    public static Vector<Ormizable> createEntitiesFromResultSet(ResultSet rs,
	    Ormizable searchEntity) throws OrmException
    {
	// Now we will iterate through the result set to create the entities
	// Here we must handle the sql exceptions that throws the result set
	// For each row in my result set
	try
	{
	    // For each row in my result setS
	    while (rs.next())
	    {
		// We initialize our instance of the ormizable entity.
		// Our Fields array and our parameters hashtable
		Ormizable entity = searchEntity.getClass().newInstance();
		Field[] fields = entity.getClass().getDeclaredFields();
		Hashtable<String, Object> parameters = new Hashtable<String, Object>();

		// We add each column to the hashtable plus it's value
		for (int i = 1; i < fields.length; i++)
		{
		    // TODO: Remove the next line when properly debugged
		    System.out.println(fields[i].getName() + " : "
			    + rs.getObject(i));
		    parameters.put(fields[i].getName(), rs.getObject(i));
		}

		// We format the data into the newly created entity
		entity.setOrmizableData(parameters);

		// We add the entities to our vector of created entities
		returnedEntities.add(entity);
	    }
	} catch (Exception e)
	{
	    throw new OrmEntityCreationException(e.getMessage());
	}

	return returnedEntities;
    }
}