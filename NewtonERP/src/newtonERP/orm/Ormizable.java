package newtonERP.orm;

import java.util.Hashtable;

import newtonERP.orm.exceptions.OrmException;

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
    /**
     * Method used to format the entitie so the orm can save them
     * 
     * @return an Hastable of data
     * @throws OrmException an exception that can occur in the orm
     */
    public Hashtable<String, String> getOrmizableData() throws OrmException;

    /**
     * Method used to return the primary key value of an Entity
     * 
     * @return the primary key value (an autoincrement value)
     */
    public int getPrimaryKeyValue();

    /**
     * Method used to return the search criterias generated by the views
     * 
     * @return the search criterias
     */
    public String getSearchCriteria();

    /**
     * Method used in the entities to set the values
     * 
     * @param parameters which are field values
     */
    public void setOrmizableData(Hashtable<String, Object> parameters);
}
