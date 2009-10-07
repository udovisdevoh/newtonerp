package newtonERP.module;

import java.lang.reflect.Field;
import java.util.Hashtable;

/**
 * @author r3lemaypa, r3lacasgu, CloutierJo
 * 
 */
public class AbstractEntity
{

    /**
     * @param parameters
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public AbstractEntity getEntityFromHashTable(
	    Hashtable<String, String> parameters)
	    throws IllegalArgumentException, IllegalAccessException
    {

	return null;
    }

    /**
     * @param entity
     * @return
     * @throws Exception
     */
    public Hashtable<String, String> getHashTableFromEntity() throws Exception
    {
	Field[] fields = getClass().getDeclaredFields();
	String fieldName;
	String value;
	Hashtable<String, String> hash = new Hashtable<String, String>();
	for (int i = 0; i < fields.length; i++)
	{
	    fieldName = "get"
		    + fields[i].getName().substring(0, 1).toUpperCase()
		    + fields[i].getName().substring(1);
	    value = (String) getClass().getMethod(fieldName, null).invoke(this,
		    null);
	    hash.put(fieldName, value);
	}

	return null;

    }
}