package modules.userRightModule.actions;

import java.util.Hashtable;

import modules.home.Home;
import modules.home.actions.GetHome;
import newtonERP.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.generalEntity.ForwardEntity;
import newtonERP.serveur.Servlet;

public class Logout extends AbstractAction
{

    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Authentication.setCurrentUserName(null);

	return new ForwardEntity(Servlet.makeLink(new Home(), new GetHome()));
    }

}