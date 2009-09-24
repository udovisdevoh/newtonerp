package newtonERP.orm;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import newtonERP.orm.exceptions.OrmException;
import newtonERP.orm.sgbd.SgbdSqlite;
import newtonERP.orm.sgbd.Sgbdable;

/**
 * 
 * @author r3hallejo, r3lacasgu
 * 
 *         Basic class for the orm. It is used to put the objects in the databse
 *         using SqLite3 and its java binding. The orm will receive an entity
 *         from which the orm will perform various tasks such as generating the
 *         query. Then it's gonna send the query to the SgbdSqlite classe to
 *         execute it.
 */
public class Orm
{
    private static Sgbdable sgbd = new SgbdSqlite();

    /**
     * Method used to do search queries done from the views to the databse
     * 
     * @param searchEntity
     * @return a vector of Ormizable entities
     */
    public static Vector<Ormizable> getSearchResult(Ormizable searchEntity)
    {
	return null;
    }

    /**
     * Method used to add an entity in the databse
     * 
     * @param newEntity
     * @throws OrmException
     */
    public static void add(Ormizable newEntity) throws OrmException
    {
	Hashtable<String, String> data = newEntity.getOrmizableData();
	String sqlQuery = "";

	// We add the hardcoded statements, in this case INSERT TO
	sqlQuery += "INSERT INTO " + newEntity.getTableName() + " (";

	// We now iterate through the key set so we can add the fields to the
	// query
	Iterator keySetIterator = data.keySet().iterator();
	while (keySetIterator.hasNext())
	{
	    // Retrieve key
	    Object key = keySetIterator.next();

	    if (!keySetIterator.hasNext())
		sqlQuery += "'" + key.toString() + "') ";
	    else
		sqlQuery += "'" + key.toString() + "', ";
	}

	// We add the VALUES keyword for the query
	sqlQuery += "VALUES (";

	// Now we add the values to the query
	Iterator valueIterator = data.values().iterator();
	while (valueIterator.hasNext())
	{
	    Object value = valueIterator.next();

	    if (!valueIterator.hasNext())
		sqlQuery += "'" + value.toString() + "') ";
	    else
		sqlQuery += "'" + value.toString() + "', ";
	}

	// We execute the query and print out the sql query produced to see we
	// have no errors
	sgbd.Execute(sqlQuery);
	System.out.println("SQL query produced : " + sqlQuery);
    }

    /**
     * Method used to delete an entity from the database
     * 
     * @param searchEntity
     * @throws OrmException
     */
    public static void delete(Ormizable searchEntity) throws OrmException
    {
    }

    /**
     * Method used to update / change an entity
     * 
     * @param searchEntity
     * @param entityContainingChanges
     * @throws OrmException
     */
    public static void update(Ormizable searchEntity,
	    Ormizable entityContainingChanges) throws OrmException
    {
    }
}
