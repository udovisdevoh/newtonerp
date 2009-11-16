package newtonERP.taskManager;

import java.util.Collection;
import java.util.Vector;

import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.Specification;
import modules.taskModule.entityDefinitions.TaskEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.PluralAccessor;

/**
 * Gestionnaire de tâches (effectue les tâches automatisées)
 * @author Guillaume Lacasse
 */
public class TaskManager
{
    /**
     * @param entity Entité pour laquelle on doit effecter des tâches s'il y a
     *            lieu
     * @throws Exception si exécution fail
     */
    public static void executeTasks(AbstractOrmEntity entity) throws Exception
    {
	Collection<TaskEntity> concernedTaskList = getConcernedTaskList(entity);
	for (TaskEntity task : concernedTaskList)
	    if (task.isActive())
		if (task.isSatisfied())
		    task.execute();
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

    private static Collection<TaskEntity> getConcernedTaskList(
	    AbstractOrmEntity entity) throws Exception
    {
	Vector<TaskEntity> concernedTaskList = new Vector<TaskEntity>();

	try
	{
	    // On va chercher l'entité représentant le type d'entité de l'entité
	    // (c'est très fourrant)
	    EntityEntity entityEntity = getEntityEntity(entity);

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

    private static EntityEntity getEntityEntity(AbstractOrmEntity entity)
	    throws Exception
    {
	EntityEntity searchEntity = new EntityEntity();
	searchEntity.initFields();
	searchEntity.setData("systemName", entity.getSystemName());

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