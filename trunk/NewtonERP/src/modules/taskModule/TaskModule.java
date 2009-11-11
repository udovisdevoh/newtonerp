package modules.taskModule;

import modules.taskModule.entityDefinitions.Task;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

public class TaskModule extends Module
{
    public TaskModule() throws Exception
    {
	super();
	setDefaultAction(new BaseAction("GetList", new Task()));
	setVisibleName("Automatisation");
    }

    public void initDB() throws Exception
    {
	super.initDB();
    }
}
