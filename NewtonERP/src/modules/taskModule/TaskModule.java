package modules.taskModule;

import modules.taskModule.entityDefinitions.SpecificationEntity;
import modules.taskModule.entityDefinitions.SpecificationOperator;
import modules.taskModule.entityDefinitions.Task;
import modules.taskModule.entityDefinitions.TaskAction;
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
	addGlobalActionMenuItem("Actions", new BaseAction("GetList",
		new TaskAction()));
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
	specification.setData(new SpecificationOperator().getForeignKeyName(),
		1);
	specification.newE();

	TaskAction action = new TaskAction();
	action.setData("name", "rien");
	action.newE();
    }
}
