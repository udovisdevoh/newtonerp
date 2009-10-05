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
 * 
 *         Pour la primary key value, le field name sera précédé du suffixe "PK"
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
     * Method used in the entities to set the values
     * 
     * @param parameters which are field values
     */
    public void setOrmizableData(Hashtable<String, Object> parameters);
}
