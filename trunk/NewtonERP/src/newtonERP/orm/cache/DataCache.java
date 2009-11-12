package newtonERP.orm.cache;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;

public class DataCache
{
    private Hashtable<String, EntityCache> entityCacheList;

    public Vector<AbstractOrmEntity> tryGetFromCache(
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

	return entityCache.getData(sqlQuery);
    }

    public void addToCache(AbstractEntity entityDefinition, String sqlQuery,
	    Vector<AbstractOrmEntity> entityList)
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

    public void clear(AbstractEntity entityDefinition)
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

    private Hashtable<String, EntityCache> getEntityCacheList()
    {
	if (entityCacheList == null)
	    entityCacheList = new Hashtable<String, EntityCache>();
	return entityCacheList;
    }
}
