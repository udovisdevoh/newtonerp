package newtonERP.orm.cache;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;

public class DataCache
{
    private static Hashtable<String, EntityCache> entityCacheList;

    public static Vector<AbstractOrmEntity> tryGetFromCache(
	    AbstractEntity entityDefinition, String sqlQuery)
    {
	String entityName = entityDefinition.getClass().getSimpleName();
	EntityCache entityCache;

	entityCache = getEntityCacheList().get(entityName);
	if (entityCache == null)
	{
	    entityCache = new EntityCache();
	    getEntityCacheList().put(entityName, entityCache);
	}
	else
	{
	    System.out.println("Getting " + entityName + " from cache");
	}

	return entityCache.getData(sqlQuery);
    }

    public static void addToCache(AbstractEntity entityDefinition,
	    String sqlQuery, Vector<AbstractOrmEntity> entityList)
    {
	String entityName = entityDefinition.getClass().getSimpleName();
	EntityCache entityCache;

	entityCache = getEntityCacheList().get(entityName);
	if (entityCache == null)
	{
	    entityCache = new EntityCache();
	    getEntityCacheList().put(entityName, entityCache);
	}

	entityCache.add(sqlQuery, entityList);
    }

    public static void clear(AbstractEntity entityDefinition)
    {
	String entityName = entityDefinition.getClass().getSimpleName();
	EntityCache entityCache;

	entityCache = getEntityCacheList().get(entityName);
	if (entityCache == null)
	{
	    entityCache = new EntityCache();
	    getEntityCacheList().put(entityName, entityCache);
	}
	entityCache.clear();
    }

    private static Hashtable<String, EntityCache> getEntityCacheList()
    {
	if (entityCacheList == null)
	    entityCacheList = new Hashtable<String, EntityCache>();
	return entityCacheList;
    }

    public static void clearAll()
    {
	entityCacheList.clear();
    }

    public static void clear(Vector<AbstractOrmEntity> searchEntities)
    {
	if (searchEntities.size() > 0)
	    clear(searchEntities.get(0));
    }

    public static Vector<AbstractOrmEntity> tryGetFromCache(
	    Vector<AbstractOrmEntity> searchEntities, String sqlQuery)
    {
	return tryGetFromCache(searchEntities.get(0), sqlQuery);
    }

    public static void addToCache(Vector<AbstractOrmEntity> searchEntities,
	    String sqlQuery, Vector<AbstractOrmEntity> entityList)
    {
	addToCache(searchEntities.get(0), sqlQuery, entityList);
    }
}
