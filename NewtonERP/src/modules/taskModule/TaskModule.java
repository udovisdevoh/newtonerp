package modules.taskModule;

import java.util.Vector;

import modules.taskModule.entityDefinitions.ActionEntity;
import modules.taskModule.entityDefinitions.EffectEntity;
import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.ModuleEntity;
import modules.taskModule.entityDefinitions.Parameter;
import modules.taskModule.entityDefinitions.SearchCriteria;
import modules.taskModule.entityDefinitions.SearchCriteriaOperator;
import modules.taskModule.entityDefinitions.SearchEntity;
import modules.taskModule.entityDefinitions.Specification;
import modules.taskModule.entityDefinitions.TaskEntity;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.ListModule;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

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
	setVisibleName("Automation");
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

	SearchCriteria searchCriteria = new SearchCriteria();
	searchCriteria.setData("key", new User().getForeignKeyName());
	searchCriteria.setData(new SearchEntity().getForeignKeyName(), 1);
	searchCriteria.setData(
		new SearchCriteriaOperator().getForeignKeyName(), 1);
	searchCriteria.setData("value", 1);
	searchCriteria.newE();

	SearchEntity searchEntity = new SearchEntity();
	searchEntity.setData("name", "Employe sans login");
	searchEntity.setData(new EntityEntity().getForeignKeyName(), 19);
	searchEntity.newE();

	EffectEntity effect = new EffectEntity();
	effect.setData("name", "On cre un login");
	effect.setData(new SearchEntity().getForeignKeyName(), 1);
	effect.setData(new ActionEntity().getForeignKeyName(), 1);
	effect.newE();

	Specification specification = new Specification();
	specification.setData("name", "Si employe pas encore de login");
	specification.setData(new SearchEntity().getForeignKeyName(), 1);
	specification.newE();

	// TaskEntity task = new TaskEntity();
	// task.setData("isActive", true);
	// task.setData(new Specification().getForeignKeyName(), 1);
	// task.setData(new EffectEntity().getForeignKeyName(), 1);
	// task.newE();
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
	    moduleEntity = new ModuleEntity();
	    moduleEntity.setData("systemName", moduleName);
	    moduleEntity.newE();

	    module = ListModule.getModule(moduleName);
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
		entityEntity = new EntityEntity();
		entityEntity.setData(moduleForeignKey, moduleEntity
			.getPrimaryKeyValue());
		entityEntity.setData("systemName", entityName);
		entityEntity.newE();
	    }
	}
    }
}
