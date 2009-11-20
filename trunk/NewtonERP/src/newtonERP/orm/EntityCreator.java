package newtonERP.orm;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.exceptions.OrmEntityCreationException;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.orm.field.Field;

/**
 * Class used to create the entities in the select statement of the orm because
 * it's not the orm's responsibility. By doing that I am trying to respect the
 * SRP (Single responsibility principle) principle
 * 
 * @author r3hallejo
 */
public class EntityCreator
{
    /**
     * Default constructor used to create the entities from the result set
     * 
     * @param rs the result set from which we will create our entities
     * @param searchEntity the entities from which we searched
     * @return a vector of ormizable entities
     * @throws OrmException an exception that can occur in the orm
     */
    public static Vector<AbstractOrmEntity> createEntitiesFromResultSet(
	    ResultSet rs, AbstractOrmEntity searchEntity) throws OrmException
    {
	Vector<AbstractOrmEntity> returnedEntities = new Vector<AbstractOrmEntity>();

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
		AbstractOrmEntity entity = searchEntity.getClass()
			.newInstance();
		Collection<Field<?>> fields = ((AbstractEntity) entity)
			.getFields().getFields();
		Hashtable<String, Object> parameters = new Hashtable<String, Object>();

		// We add each column to the hashtable plus it's value
		for (Field<?> field : fields)
		{
		    if (field.getCalcul() == null)
		    {
			String key = field.getShortName();
			Object value = rs.getObject(field.getShortName());
			if (value != null)// Lorsqu'un champ est null
			    parameters.put(key, value);
		    }
		}

		// We format the data into the newly created entity
		if (parameters.size() > 0)
		{
		    entity.setOrmizableData(parameters);

		    // We add the entities to our vector of created entities
		    returnedEntities.add(entity);
		}
	    }
	} catch (Exception e)
	{
	    /*
	     * Ce PrintStackTrace est très utile car il manque de l'information
	     * de provenance de stack dans la nouvelle exception TODO: faire que
	     * throw new OrmEntityCreationException garde l'information de stack
	     * de Exception e. On a convenu qu'il faut laisser passer les
	     * exception. Le fait d'ajouter le PrintStackTrace de l'exception e
	     * n'a pas d'influence sur la propagation de la nouvelle exception
	     * OrmEntityCreationException
	     */
	    e.printStackTrace();
	    throw new OrmEntityCreationException(
		    "Erreur à la création des entitées provenant d'une requête de l'orm : "
			    + e.getStackTrace().toString());
	}
	return returnedEntities;
    }
}
