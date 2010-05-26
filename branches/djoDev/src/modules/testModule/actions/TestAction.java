package modules.testModule.actions;

import java.util.Hashtable;

import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * @author r3hallejo
 * 
 *         Action class representing a test action
 */
public class TestAction extends AbstractAction
{
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {

	System.out.println("testAction.perform()");
	// TODO: check that return new TestViewable();
	return null;
    }
}
