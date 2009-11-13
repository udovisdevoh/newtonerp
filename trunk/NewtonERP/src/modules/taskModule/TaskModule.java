package modules.taskModule;

import modules.taskModule.entityDefinitions.SpecificationEntity;
import modules.taskModule.entityDefinitions.SpecificationOperator;
import modules.taskModule.entityDefinitions.Task;
import modules.taskModule.entityDefinitions.TaskEffect;
import modules.taskModule.entityDefinitions.TaskSearchEntity;
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
	setVisibleName("Automatisation");
	addGlobalActionMenuItem("Tâches", new BaseAction("GetList", new Task()));
	addGlobalActionMenuItem("Spécifications", new BaseAction("GetList",
		new SpecificationEntity()));
	addGlobalActionMenuItem("Effets", new BaseAction("GetList",
		new TaskEffect()));
	addGlobalActionMenuItem("Entités de recherches", new BaseAction(
		"GetList", new TaskSearchEntity()));
    }

    public void initDB() throws Exception
    {
	super.initDB();

	SpecificationOperator operator;

	operator = new SpecificationOperator();
	operator.setData("name", "aucun");
	operator.newE();

	operator = new SpecificationOperator();
	operator.setData("name", "et");
	operator.newE();

	operator = new SpecificationOperator();
	operator.setData("name", "ou");
	operator.newE();

	SpecificationEntity specification;
	specification = new SpecificationEntity();
	specification.setData("name", "defaultSpecification");
	specification.setData("systemName", "defaultSpecification");
	specification.setData("parent1", 0);
	specification.setData("parent2", 0);
	specification.setData(new SpecificationOperator().getForeignKeyName(),
		1);
	specification.newE();

	TaskEffect action = new TaskEffect();
	action.setData("name", "rien");
	action.newE();
    }
}
