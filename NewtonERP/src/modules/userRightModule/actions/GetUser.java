package modules.userRightModule.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.ModuleException;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author Gabriel Therrien
 * 
 */
public class GetUser extends AbstractAction
{

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {
	Vector<String> search = new Vector<String>();
	search.add("name=" + ((User) entity).getName());
	User retUser = new User();
	try
	{
	    retUser = (User) Orm.select(new User(), search).get(0);
	} catch (OrmException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	retUser.setSubmitAction(this);
	try
	{
	    retUser.setSubmitModule(new UserRightModule());
	} catch (ModuleException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	// TODO Auto-generated method stub
	return retUser;
    }
}
