package modules.userRightModule.actions;

import java.util.Hashtable;

import newtonERP.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.generalEntity.StaticTextEntity;

public class Logout extends AbstractAction
{

    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Authentication.setCurrentUserName(null);

	return new StaticTextEntity("Au revoir!");
    }

}