package newtonERP.taskManager;

import java.util.Hashtable;
import java.util.Vector;

import modules.taskModule.entityDefinitions.TaskEntity;

/**
 * Cache pour que taskManager se rappelle des tasks concernant entitées
 * @author Guillaume
 * 
 */
public class TaskCache
{
    private static Hashtable<String, Vector<TaskEntity>> cacheList;

    /**
     * @param entityName entité pour laquelle on veut les tasks concernées
     * @return tasks concernées pour entité
     */
    public static Vector<TaskEntity> getConcernedTaskList(String entityName)
    {
	return getCacheList().get(entityName);
    }

    private static Hashtable<String, Vector<TaskEntity>> getCacheList()
    {
	if (cacheList == null)
	    cacheList = new Hashtable<String, Vector<TaskEntity>>();
	return cacheList;
    }

    /**
     * @param entityName nom d'entité pour laquelle on veut les tasks concernées
     * @param concernedTaskList tasks concernées pour entité
     */
    public static void setConcernedTaskList(String entityName,
	    Vector<TaskEntity> concernedTaskList)
    {
	getCacheList().put(entityName, concernedTaskList);
    }

    /**
     * Clear the cache
     */
    public static void clear()
    {
	getCacheList().clear();
    }

}
