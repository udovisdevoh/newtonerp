package newtonERP.orm.field;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;

/**
 * Cache pour accélérer les dynamic field (Ce cache conserve les entité de
 * fields dynamiques)
 * @author Guillaume Lacasse
 */
public class DynamicFieldCache
{
    private static Hashtable<String, Vector<AbstractOrmEntity>> data;

    /**
     * @param entityName nom de l'entité
     */
    public static void clear(String entityName)
    {
	Vector<AbstractOrmEntity> dataForEntity = tryGetDataForEntity(entityName);
	if (dataForEntity != null)
	    getData().remove(entityName);
    }

    /**
     * @param fieldEntityList liste des entité de field
     * @param entityName nom de l'entité
     */
    public static void add(Vector<AbstractOrmEntity> fieldEntityList,
	    String entityName)
    {
	getData().put(entityName, fieldEntityList);
    }

    /**
     * @param entityName nom de l'entité
     * @return listes des entité de fields
     */
    public static Vector<AbstractOrmEntity> tryGetDataForEntity(
	    String entityName)
    {
	return getData().get(entityName);
    }

    private static Hashtable<String, Vector<AbstractOrmEntity>> getData()
    {
	if (data == null)
	    data = new Hashtable<String, Vector<AbstractOrmEntity>>();
	return data;
    }
}
