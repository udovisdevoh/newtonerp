package modules.userRightModule;

import java.util.Vector;

import modules.userRightModule.actions.Login;
import modules.userRightModule.entityDefinitions.GroupRight;
import modules.userRightModule.entityDefinitions.Groups;
import modules.userRightModule.entityDefinitions.Right;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;
import newtonERP.orm.Orm;

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
     * @throws Exception remonte
     */
    public UserRightModule() throws Exception
    {
	super();
	setDefaultAction(new Login());
	addGlobalActionMenuItem("Utilisateurs", new BaseAction("GetList",
		new User()));
	addGlobalActionMenuItem("Liste des droits", new BaseAction("GetList",
		new Right()));
	addGlobalActionMenuItem("Groupes", new BaseAction("GetList",
		new Groups()));
    }

    public void initDB() throws Exception
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

	// ***************** unlogged User ****************
	// cree le groupe
	group = new Groups();
	group.setData("groupName", "unLogedGroup");
	Orm.insert(group);

	// retrouve le groupsID
	search.clear();
	search.add("groupName=" + "'unLogedGroup'");
	groupsID = (Integer) ((Groups) Orm.select(group, search).get(0))
		.getData(group.getPrimaryKeyName());

	// cree le user
	user = new User();
	user.setData("name", "unLogedUser");
	user.setData("password", "");
	user.setData("groupsID", groupsID);
	Orm.insert(user);

	// retrouve les rightID

	Right right = new Right();
	right.getFields().getField("moduleName").setData(
		this.getClass().getSimpleName());
	right.getFields().getField("actionName").setData("Login");
	right = (Right) right.get(right).get(0);
	rightID = (Integer) right.getData(right.getPrimaryKeyName());

	// cree le groupRight
	groupRight.setData("groupsID", groupsID);
	groupRight.setData("rightID", rightID);
	Orm.insert(groupRight);
    }
}
