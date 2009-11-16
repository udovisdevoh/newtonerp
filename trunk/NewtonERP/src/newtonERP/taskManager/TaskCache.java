package newtonERP.taskManager;

import java.util.Hashtable;
import java.util.Vector;

import modules.taskModule.entityDefinitions.TaskEntity;
import newtonERP.module.AbstractOrmEntity;

/**
 * Cache pour que taskManager se rappelle des tasks concernant entitées
 * @author Guillaume
 * 
 */
public class TaskCache
{
    private static Hashtable<String, Vector<TaskEntity>> cacheList;

    /**
     * @param entity entité pour laquelle on veut les tasks concernées
     * @return tasks concernées pour entité
     */
    public static Vector<TaskEntity> getConcernedTaskList(
	    AbstractOrmEntity entity)
    {
	return getCacheList().get(entity.getSystemName());
    }

    private static Hashtable<String, Vector<TaskEntity>> getCacheList()
    {
	if (cacheList == null)
	    cacheList = new Hashtable<String, Vector<TaskEntity>>();
	return cacheList;
    }

    /**
     * @param entity pour laquelle on veut les tasks concernées
     * @param concernedTaskList tasks concernées pour entité
     */
    public static void setConcernedTaskList(AbstractOrmEntity entity,
	    Vector<TaskEntity> concernedTaskList)
    {
	getCacheList().put(entity.getSystemName(), concernedTaskList);
    }

}
