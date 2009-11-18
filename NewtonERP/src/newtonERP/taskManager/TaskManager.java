package newtonERP.taskManager;

import java.util.Collection;
import java.util.Vector;

import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.Specification;
import modules.taskModule.entityDefinitions.TaskEntity;
import newtonERP.common.ListModule;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.Module;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.PluralAccessor;

/**
 * Gestionnaire de tâches (effectue les tâches automatisées)
 * @author Guillaume Lacasse
 */
public class TaskManager
{
    private static Vector<String> taskModuleEntityDefinitionNameList;

    /**
     * @param entity Entité pour laquelle on doit effecter des tâches s'il y a
     *            lieu
     * @return entité viewable de retour
     * @throws Exception si exécution fail
     */
    public static AbstractEntity executeTasks(AbstractOrmEntity entity)
	    throws Exception
    {
	return executeTasks(entity.getSystemName());
    }

    /**
     * @param entityName nom de l'entité
     * @return enitité visible de retour
     * @throws Exception si exécution fail
     */
    public static AbstractEntity executeTasks(String entityName)
	    throws Exception
    {
	if (entityName == null)
	    return null;

	AbstractEntity retEntity = null;

	if (isEntityRelatedToTaskModule(entityName))
	    TaskCache.clear();

	Collection<TaskEntity> concernedTaskList = getConcernedTaskList(entityName);
	for (TaskEntity task : concernedTaskList)
	    if (task.isActive())
		if (task.isSatisfied())
		    retEntity = task.execute();

	return retEntity;
    }

    private static boolean isEntityRelatedToTaskModule(String entityName)
	    throws Exception
    {
	return getTaskModuleEntityDefinitionNameList().contains(entityName);
    }

    private static Vector<String> getTaskModuleEntityDefinitionNameList()
	    throws Exception
    {
	if (taskModuleEntityDefinitionNameList == null)
	{
	    taskModuleEntityDefinitionNameList = new Vector<String>();

	    Module taskModule = ListModule.getModule("TaskModule");

	    for (String entityDefinitionName : taskModule
		    .getEntityDefinitionList().keySet())
	    {
		taskModuleEntityDefinitionNameList.add(entityDefinitionName);
	    }
	}

	return taskModuleEntityDefinitionNameList;
    }

    /**
     * On exécute tous les tâches possibles
     * @throws Exception si exécution fail
     */
    public static void executeAllTasks() throws Exception
    {
	Collection<TaskEntity> concernedTaskList = getTaskList();
	for (TaskEntity task : concernedTaskList)
	    if (task.isActive())
		if (task.isSatisfied())
		    task.execute();

    }

    private static Collection<TaskEntity> getTaskList() throws Exception
    {
	Vector<AbstractOrmEntity> result = Orm.select(new TaskEntity());
	Vector<TaskEntity> taskList = new Vector<TaskEntity>();

	for (AbstractOrmEntity entity : result)
	    taskList.add((TaskEntity) entity);

	return taskList;
    }

    private static Collection<TaskEntity> getConcernedTaskList(String entityName)
	    throws Exception
    {
	Vector<TaskEntity> concernedTaskList = TaskCache
		.getConcernedTaskList(entityName);

	if (concernedTaskList != null)
	    return concernedTaskList;

	concernedTaskList = new Vector<TaskEntity>();

	try
	{
	    // On va chercher l'entité représentant le type d'entité de l'entité
	    // (c'est très fourrant)
	    EntityEntity entityEntity = getEntityEntity(entityName);

	    // On va chercher les entités de recherches concernant l'entité de
	    // définition d'entité
	    PluralAccessor searchEntityList = getSearchEntityList(entityEntity);

	    // On va chercher la liste de spécification qui concerne les
	    // entitées de
	    // recherches
	    PluralAccessor specificationList = getSpecificationEntityList(searchEntityList);

	    // On va chercher la liste de tâches qui concerne la liste de
	    // spécification
	    PluralAccessor taskList = getTaskList(specificationList);

	    for (AbstractOrmEntity currentEntity : taskList)
		concernedTaskList.add((TaskEntity) currentEntity);

	} catch (Exception e)
	{
	    System.out
		    .println("Impossible d'obtenir la liste des tâches pour cette entité");
	}

	TaskCache.setConcernedTaskList(entityName, concernedTaskList);

	return concernedTaskList;
    }

    private static PluralAccessor getTaskList(PluralAccessor specificationList)
	    throws Exception
    {
	PluralAccessor taskEntityList = new PluralAccessor(new TaskEntity());

	for (AbstractOrmEntity specificationEntity : specificationList)
	{
	    PluralAccessor currentAccessor = specificationEntity
		    .getPluralAccessor("TaskEntity");

	    taskEntityList.addAll(currentAccessor);
	}

	return taskEntityList;
    }

    private static PluralAccessor getSpecificationEntityList(
	    PluralAccessor searchEntityList) throws Exception
    {
	PluralAccessor specificationEntityList = new PluralAccessor(
		new Specification());

	for (AbstractOrmEntity searchEntity : searchEntityList)
	{
	    PluralAccessor currentAccessor = searchEntity
		    .getPluralAccessor("Specification");

	    specificationEntityList.addAll(currentAccessor);
	}

	return specificationEntityList;
    }

    private static PluralAccessor getSearchEntityList(EntityEntity entityEntity)
	    throws Exception
    {
	return entityEntity.getPluralAccessor("SearchEntity");
    }

    private static EntityEntity getEntityEntity(String entityName)
	    throws Exception
    {
	EntityEntity searchEntity = new EntityEntity();
	searchEntity.initFields();
	searchEntity.setData("systemName", entityName);

	return (EntityEntity) (Orm.selectUnique(searchEntity));
    }

    /**
     * @param searchEntities entités pour lesquelles on doit possiblement
     *            effectuer des tâches
     * @throws Exception si exécution fail
     */
    public static void executeTasks(Vector<AbstractOrmEntity> searchEntities)
	    throws Exception
    {
	if (searchEntities.size() > 0)
	    executeTasks(searchEntities.get(0));
    }
}