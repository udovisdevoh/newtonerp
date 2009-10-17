package modules.userRightModule;

import java.util.Vector;

import modules.userRightModule.entityDefinitions.GroupRight;
import modules.userRightModule.entityDefinitions.Groups;
import modules.userRightModule.entityDefinitions.Right;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.Module;
import newtonERP.module.ModuleException;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author Gabriel Therrien, Guillaume Lacasse
 * 
 *         Class representing the user right module. Base module for the ERP.
 */
public class UserRightModule extends Module
{
    /**
     * Default constructor for the user right module initializing himself. Adds
     * the actions and the entity defenitions
     * 
     * FIXME : See user line 201 for my comment
     */
    public UserRightModule() throws ModuleException
    {
	super();
	setDefaultAction("GetUser");
    }

    public void initDB()
    {
	try
	{
	    // cree le groupe
	    Groups group = new Groups();
	    group.setGroupName("admin");
	    Orm.insert(group);

	    // retourve le groupID
	    Vector<String> search = new Vector<String>();
	    search.add("groupName=" + "'admin'");
	    int groupID = ((Groups) Orm.select(group, search).get(0))
		    .getPKgroupID();

	    // cree le user
	    User user = new User();
	    user.setName("admin");
	    user.setPassword("aaa");
	    user.setGroupID(groupID);
	    Orm.insert(user);
	    int rightID;
	    GroupRight groupRight = new GroupRight();
	    for (String action : getActionList().keySet())
	    {
		Right right = new Right();
		// retrouve le rightID
		search.clear();
		search.add("ActionName='" + action + "' AND ModuleName='"
			+ this.getClass().getSimpleName() + "'");
		right = ((Right) Orm.select(right, search).get(0));
		rightID = right.getPKrightID();

		// cree le groupRight
		groupRight.setGroupID(groupID);
		groupRight.setRightID(rightID);
		Orm.insert(groupRight);
	    }
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
    }
}
