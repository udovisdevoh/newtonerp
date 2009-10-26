package modules.userRightModule;

import java.util.Vector;

import modules.userRightModule.actions.GetGroupsList;
import modules.userRightModule.actions.GetRightList;
import modules.userRightModule.actions.GetUserList;
import modules.userRightModule.entityDefinitions.GroupRight;
import modules.userRightModule.entityDefinitions.Groups;
import modules.userRightModule.entityDefinitions.Right;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.Module;
import newtonERP.module.exception.FieldNotCompatibleException;
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
    public UserRightModule()
    {
	super();
	setDefaultAction(new GetUserList());
	addGlobalActionMenuItem("Utilisateurs", new GetUserList());
	addGlobalActionMenuItem("Liste des droits", new GetRightList());
	addGlobalActionMenuItem("Groupes", new GetGroupsList());
    }

    public void initDB()
    {
	try
	{
	    // cree le groupe
	    Groups group = new Groups();
	    group.setData("groupName", "admin");
	    Orm.insert(group);

	    // retrouve le groupsID
	    Vector<String> search = new Vector<String>();
	    search.add("groupName=" + "'admin'");
	    int groupsID = (Integer) ((Groups) Orm.select(group, search).get(0))
		    .getData(group.getPrimaryKeyName());

	    // cree le user
	    User user = new User();
	    user.setData("name", "admin");
	    user.setData("password", "aaa");
	    user.setData("groupsID", groupsID);
	    Orm.insert(user);

	    int rightID;
	    GroupRight groupRight = new GroupRight();

	    // retrouve les rightID
	    search.clear();
	    search.add("moduleName='" + this.getClass().getSimpleName() + "'");

	    for (AbstractOrmEntity right : Orm.select(new Right(), search))
	    {
		rightID = (Integer) ((Right) right).getData(right
			.getPrimaryKeyName());

		// cree le groupRight
		groupRight.setData("groupsID", groupsID);
		groupRight.setData("rightID", rightID);
		Orm.insert(groupRight);
	    }
	} catch (OrmException e)
	{
	    e.printStackTrace();// FIXME: TODO: est-ce que ça doit vraiment être
	    // catché?
	} catch (FieldNotCompatibleException e)
	{
	    e.printStackTrace();// FIXME: TODO: est-ce que ça doit vraiment être
	    // catché?
	}
    }
}
