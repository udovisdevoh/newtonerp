package newtonERP.module.moduleGetters;

import java.util.Hashtable;

import newtonERP.module.actions.ActionableEntity;
import newtonERP.module.actions.TestActionableEntity;

public class TestModuleGetter implements IModuleGetter
{

    @Override
    public ActionableEntity getEntityFromParameters(
	    Hashtable<String, String> parameters)
    {
	return new TestActionableEntity();
    }

}
