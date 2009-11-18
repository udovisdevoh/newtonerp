package modules.taskModule;

import java.util.Vector;

import modules.taskModule.entityDefinitions.ActionEntity;
import modules.taskModule.entityDefinitions.EffectEntity;
import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.FieldEntity;
import modules.taskModule.entityDefinitions.FieldTypeEntity;
import modules.taskModule.entityDefinitions.ModuleEntity;
import modules.taskModule.entityDefinitions.Parameter;
import modules.taskModule.entityDefinitions.SearchCriteria;
import modules.taskModule.entityDefinitions.SearchCriteriaOperator;
import modules.taskModule.entityDefinitions.SearchEntity;
import modules.taskModule.entityDefinitions.Specification;
import modules.taskModule.entityDefinitions.TaskEntity;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.ListModule;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;
import newtonERP.orm.Orm;
import newtonERP.orm.field.Field;

/**
 * Module des tasks
 * @author Guillaume Lacasse
 */
public class TaskModule extends Module
{
    /**
     * @throws Exception si création fail
     */
    public TaskModule() throws Exception
    {
	super();
	setDefaultAction(new BaseAction("GetList", new TaskEntity()));
	setVisibleName("Système");
	addGlobalActionMenuItem("Modules", new BaseAction("GetList",
		new ModuleEntity()));
	addGlobalActionMenuItem("Tâches", new BaseAction("GetList",
		new TaskEntity()));
	addGlobalActionMenuItem("Spécification", new BaseAction("GetList",
		new Specification()));
	addGlobalActionMenuItem("Effet", new BaseAction("GetList",
		new EffectEntity()));
	addGlobalActionMenuItem("Entités de recherches", new BaseAction(
		"GetList", new SearchEntity()));
	addGlobalActionMenuItem("Critères de recherches", new BaseAction(
		"GetList", new SearchCriteria()));
	addGlobalActionMenuItem("Paramètres", new BaseAction("GetList",
		new Parameter()));
    }

    public void initDB() throws Exception
    {
	super.initDB();

	initActionsAndEntities();
	initSearchCriteriaOperators();

	// les entitées suivantes sont là juste
	// pour tester
	Parameter parameter = new Parameter();
	parameter.setData("key", "message");
	parameter
		.setData("value",
			"Voici votre nouveau compte utilisateur :Employee.firstName:Employee.lastName");
	// parameter.setData("value", ":Employee.firstName:Employee.lastName");
	parameter.newE();

	SearchCriteriaOperator equals = new SearchCriteriaOperator();
	equals.setData("name", "=");
	equals = (SearchCriteriaOperator) Orm.selectUnique(equals);

	EntityEntity entity = new EntityEntity();
	entity.setData("systemName", "Employee");
	entity = (EntityEntity) Orm.selectUnique(entity);

	SearchEntity searchEntity = new SearchEntity();
	searchEntity.setData("name", "Employe sans login");
	searchEntity.setData(entity.getForeignKeyName(), entity
		.getPrimaryKeyValue());
	searchEntity.newE();

	User user = new User();
	user.setData("name", "unLogedUser");
	user = (User) Orm.selectUnique(user);

	SearchCriteria searchCriteria = new SearchCriteria();
	searchCriteria.setData("key", new User().getForeignKeyName());
	searchCriteria.setData(searchEntity.getForeignKeyName(), searchEntity
		.getPrimaryKeyValue());
	searchCriteria.setData(
		new SearchCriteriaOperator().getForeignKeyName(), equals
			.getPrimaryKeyValue());
	searchCriteria.setData("value", user.getPrimaryKeyValue());
	searchCriteria.newE();

	ActionEntity actionEntity = new ActionEntity();
	actionEntity.setData("systemName", "CreateUserForEmployee");
	actionEntity = (ActionEntity) actionEntity.get().get(0);

	EffectEntity effect = new EffectEntity();
	effect.setData("name", "On cre un login");
	effect.setData(searchEntity.getForeignKeyName(), searchEntity
		.getPrimaryKeyValue());
	// effect.setData(actionEntity.getForeignKeyName(), actionEntity
	// .getPrimaryKeyValue());
	effect.assign(actionEntity);
	effect.newE();

	Specification specification = new Specification();
	specification.setData("name", "Si employe pas encore de login");
	specification.setData(searchEntity.getForeignKeyName(), searchEntity
		.getPrimaryKeyValue());
	specification.newE();

	TaskEntity task = new TaskEntity();
	task.setData("isActive", false);
	task.setData(specification.getForeignKeyName(), specification
		.getPrimaryKeyValue());
	task.setData(effect.getForeignKeyName(), effect.getPrimaryKeyValue());
	task.newE();
    }

    private static void initSearchCriteriaOperators() throws Exception
    {
	// Les entitées suivantes sont là pour avoir des données par default
	// qu'on doit garder

	SearchCriteriaOperator searchCriteriaOperator;

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "=");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "!=");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", ">");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "<");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", ">=");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "<=");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "in");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "is");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "like");
	searchCriteriaOperator.newE();
    }

    private static void initActionsAndEntities() throws Exception
    {
	// Les entitées suivantes sont là pour avoir des données par default
	// qu'on doit garder

	String moduleForeignKey = new ModuleEntity().getForeignKeyName();

	ModuleEntity moduleEntity;

	moduleEntity = new ModuleEntity();
	moduleEntity.setData("systemName", "...");
	moduleEntity.newE();

	ActionEntity actionEntity;

	actionEntity = new ActionEntity();
	actionEntity.setData(moduleForeignKey, moduleEntity
		.getPrimaryKeyValue());
	actionEntity.setData("systemName", "Get");
	actionEntity.newE();

	actionEntity = new ActionEntity();
	actionEntity.setData(moduleForeignKey, moduleEntity
		.getPrimaryKeyValue());
	actionEntity.setData("systemName", "New");
	actionEntity.newE();

	actionEntity = new ActionEntity();
	actionEntity.setData(moduleForeignKey, moduleEntity
		.getPrimaryKeyValue());
	actionEntity.setData("systemName", "Edit");
	actionEntity.newE();

	actionEntity = new ActionEntity();
	actionEntity.setData(moduleForeignKey, moduleEntity
		.getPrimaryKeyValue());
	actionEntity.setData("systemName", "Delete");
	actionEntity.newE();

	actionEntity = new ActionEntity();
	actionEntity.setData(moduleForeignKey, moduleEntity
		.getPrimaryKeyValue());
	actionEntity.setData("systemName", "GetList");
	actionEntity.newE();

	EntityEntity entityEntity;

	Module module = null;
	Vector<String> allModule;
	allModule = new Vector<String>(ListModule.getAllModules().keySet());
	// on s'assure d'avoir créé le userRightModule en premier
	for (String moduleName : allModule)
	{
	    module = ListModule.getModule(moduleName);

	    moduleEntity = new ModuleEntity();
	    moduleEntity.setData("systemName", moduleName);
	    moduleEntity.setData("visibleName", module.getVisibleName());
	    moduleEntity.newE();

	    for (String actionName : module.getActionList().keySet())
	    {
		actionEntity = new ActionEntity();
		actionEntity.setData(moduleForeignKey, moduleEntity
			.getPrimaryKeyValue());
		actionEntity.setData("systemName", actionName);
		actionEntity.newE();
	    }

	    for (String entityName : module.getEntityDefinitionList().keySet())
	    {
		AbstractOrmEntity realEntity = module.getEntityDefinitionList()
			.get(entityName);

		entityEntity = new EntityEntity();
		entityEntity.setData(moduleForeignKey, moduleEntity
			.getPrimaryKeyValue());
		entityEntity.setData("systemName", entityName);
		entityEntity
			.setData("visibleName", realEntity.getVisibleName());
		entityEntity.newE();

		initFieldEntitiesForEntityEntity(entityEntity, realEntity);
	    }
	}
    }

    private static void initFieldEntitiesForEntityEntity(
	    EntityEntity entityEntity, AbstractOrmEntity realEntity)
	    throws Exception
    {
	realEntity.initFields();

	for (Field field : realEntity.getFields())
	    initFieldEntity(field, entityEntity);
    }

    private static void initFieldEntity(Field field, EntityEntity entityEntity)
	    throws Exception
    {
	FieldTypeEntity fieldType = getOrCreateFieldType(field);
	FieldEntity fieldEntity = new FieldEntity();
	fieldEntity.setData("name", field.getShortName());
	fieldEntity.setData("visibleName", field.getName());
	fieldEntity.setData("readOnly", field.isReadOnly());
	fieldEntity.setData("hidden", field.isHidden());
	fieldEntity.setData("naturalKey", field.isNaturalKey());
	fieldEntity.assign(fieldType);
	fieldEntity.assign(entityEntity);
	fieldEntity.newE();
    }

    private static FieldTypeEntity getOrCreateFieldType(Field field)
	    throws Exception
    {
	FieldTypeEntity fieldType = new FieldTypeEntity();
	fieldType.setData("systemName", field.getSystemName());

	Vector<AbstractOrmEntity> result = Orm.select(fieldType);

	if (result.size() > 0)
	    return (FieldTypeEntity) result.get(0);

	fieldType.newE();
	return fieldType;
    }
}
