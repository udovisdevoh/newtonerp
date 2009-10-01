package modules.testModule;

import java.util.Hashtable;

import newtonERP.module.actions.ActionableEntity;
import newtonERP.module.actions.IAction;
import newtonERP.viewers.TestViewable;
import newtonERP.viewers.Viewable;

public class TestAction implements IAction
{

    @Override
    public Viewable perform(ActionableEntity entity,
	    Hashtable<String, String> parameters)
    {
	return new TestViewable();
    }
}
