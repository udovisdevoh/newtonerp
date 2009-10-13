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
 * 	Action class used to get a user
 */
public class GetUser extends AbstractAction
{
    /**
     * TODO: Check if this comment is right
     * Default constructor that creates a new user or that gets one? It isn't really clear...
     */
    public GetUser()
    {
	super(new User());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {
	Vector<String> search = new Vector<String>();
	search.add("name='" + ((User) entity).getName() + "'");
	User retUser = new User();
	try
	{
	    retUser = (User) Orm.select(new User(), search).get(0);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}

	retUser.setSubmitAction(this);
	
	try
	{
	    retUser.setSubmitModule(new UserRightModule());
	} catch (ModuleException e)
	{
	    e.printStackTrace();
	}

	return retUser;
    }
}
