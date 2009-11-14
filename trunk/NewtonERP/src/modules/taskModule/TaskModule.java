package modules.taskModule;

import java.util.Vector;

import modules.taskModule.entityDefinitions.ActionEntity;
import modules.taskModule.entityDefinitions.Effect;
import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.ModuleEntity;
import modules.taskModule.entityDefinitions.Parameter;
import modules.taskModule.entityDefinitions.SearchCriteria;
import modules.taskModule.entityDefinitions.SearchCriteriaOperator;
import modules.taskModule.entityDefinitions.SearchEntity;
import modules.taskModule.entityDefinitions.Specification;
import modules.taskModule.entityDefinitions.Task;
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
	setDefaultAction(new BaseAction("GetList", new Task()));
	setVisibleName("Automation");
	addGlobalActionMenuItem("Tâches", new BaseAction("GetList", new Task()));

	addGlobalActionMenuItem("Spécification", new BaseAction("GetList",
		new Specification()));
	addGlobalActionMenuItem("Effet",
		new BaseAction("GetList", new Effect()));
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

	// Contrairement aux précédentes, les entitées suivantes sont là juste
	// pour tester
	Parameter parameter = new Parameter();
	parameter.setData("key", "message");
	parameter.setData("value", "vous etes vire mon estie");
	parameter.newE();

	SearchCriteria searchCriteria = new SearchCriteria();
	searchCriteria.setData("key", "salaire");
	searchCriteria.setData(new SearchEntity().getForeignKeyName(), 1);
	searchCriteria.setData(
		new SearchCriteriaOperator().getForeignKeyName(), 5);
	searchCriteria.setData("value", "33400");
	searchCriteria.newE();

	SearchEntity searchEntity = new SearchEntity();
	searchEntity.setData("name", "Employe qui a un salaire poche");
	searchEntity.setData(new EntityEntity().getForeignKeyName(), 1);
	searchEntity.newE();

	Effect effect = new Effect();
	effect.setData("name", "On congedie le dude");
	effect.setData(new SearchEntity().getForeignKeyName(), 1);
	effect.setData(new ActionEntity().getForeignKeyName(), 1);
	effect.newE();

	Specification specification = new Specification();
	specification.setData("name", "Si employe en retard");
	specification.setData(new SearchEntity().getForeignKeyName(), 1);
	specification.newE();
    }

    private static void initActionsAndEntities() throws Exception
    {
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
