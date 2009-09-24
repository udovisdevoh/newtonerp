package newtonERP.orm;

import java.util.Hashtable;
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
    private Sgbdable sgbd = new SgbdSqlite();

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

	// On ajoute nom de la table et l'ajout
	sqlQuery += "INSERT INTO " + newEntity.getTableName() + " VALUES (";

	// TODO: Iterate through the hashtable to build the query, for now we
	// only need it to work

	/*
	 * // Iterate throught values Iterator it = data.values().iterator();
	 * 
	 * while (it.hasNext()) { // Retrieve key Object key = it.next(); }
	 */
	sqlQuery += "'" + data.get("name") + "',";
	sqlQuery += "'" + data.get("age") + "',";
	sqlQuery += "'" + data.get("color") + "');";

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
