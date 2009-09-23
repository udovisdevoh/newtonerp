package newtonERP.orm;

import java.util.Hashtable;

import newtonERP.orm.exceptions.OrmFieldNotFoundException;

/**
 * 
 * @author r3lacasgu, r3hallejo
 * 
 *         Interface representing the methods that every entity should
 *         implements in order for the orm to be able to save it in the database
 *         or do any other actions
 */
public interface Ormizable
{
    public Hashtable<String, String> getOrmizableData()
	    throws OrmFieldNotFoundException;

    public String getTableName();

    public int getPrimaryKeyValue();

    public String getSearchCriteria();
}
