package newtonERP.orm.cache;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;

public class EntityCache
{
    private Hashtable<String, Vector<AbstractOrmEntity>> queryCacheList;

    public Vector<AbstractOrmEntity> getData(String query)
    {
	return getQueryCacheList().get(query);
    }

    public void clear()
    {
	getQueryCacheList().clear();
    }

    private Hashtable<String, Vector<AbstractOrmEntity>> getQueryCacheList()
    {
	if (queryCacheList == null)
	    queryCacheList = new Hashtable<String, Vector<AbstractOrmEntity>>();
	return queryCacheList;
    }

    public void add(String sqlQuery, Vector<AbstractOrmEntity> entityList)
    {
	getQueryCacheList().put(sqlQuery, entityList);
    }
}
