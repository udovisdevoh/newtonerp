package modules.userRightModule.actions;

import java.util.Hashtable;

import newtonERP.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

public class Logout extends AbstractAction
{

    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Authentication.setCurrentUserName(null);

	return null;
    }

}