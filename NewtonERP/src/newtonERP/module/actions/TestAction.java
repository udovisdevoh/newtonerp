package newtonERP.module.actions;

import java.util.Hashtable;

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
