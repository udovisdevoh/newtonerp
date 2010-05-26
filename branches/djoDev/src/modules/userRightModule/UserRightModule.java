package modules.userRightModule;

import java.util.Vector;

import modules.userRightModule.entityDefinitions.Groups;
import modules.userRightModule.entityDefinitions.GroupsRight;
import modules.userRightModule.entityDefinitions.Right;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;
import newtonERP.orm.Orm;

/**
 * @author Gabriel Therrien, Guillaume Lacasse, CloutierJo
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
	setDefaultAction(new BaseAction("GetList", new User()));
	addGlobalActionMenuItem("Utilisateurs", new BaseAction("GetList",
		new User()));
	addGlobalActionMenuItem("Liste des droits", new BaseAction("GetList",
		new Right()));
	addGlobalActionMenuItem("Groupes", new BaseAction("GetList",
		new Groups()));
	setVisibleName("Gestion de droits");
    }

    public void initDB() throws Exception
    {
	Groups groups;
	User user;
	int rightID, groupsID;
	Right right;
	GroupsRight groupsRight;
	Vector<String> search;

	// ***************** unlogged User ****************
	// cree le groupe
	groups = new Groups();
	groups.setData("groupName", "unLogedGroup");
	groups.newE();

	// retrouve le groupsID
	search = new Vector<String>();
	search.clear();
	search.add("groupName=" + "'unLogedGroup'");
	groupsID = (Integer) ((Groups) Orm.select(groups, search).get(0))
		.getData(groups.getPrimaryKeyName());

	// cree le user unLogedUser
	user = new User();
	user.setData("name", "unLogedUser");
	user.setData("password", "");
	user.setData("groupsID", groupsID);
	Orm.insert(user);

	// retrouve les rightID

	right = new Right();
	right.getFields().getField("moduleName").setData(getSystemName());
	right.getFields().getField("actionName").setData("Login");
	right = (Right) right.get(right).get(0);
	rightID = (Integer) right.getData(right.getPrimaryKeyName());

	// cree le GroupsRight
	groupsRight = new GroupsRight();
	groupsRight.setData("groupsID", groupsID);
	groupsRight.setData("rightID", rightID);
	groupsRight.newE();

	// cree le groupe
	groups = new Groups();
	groups.setData("groupName", "admin");
	groups.newE();

	// retrouve le groupsID
	search = new Vector<String>();
	search.add("groupName=" + "'admin'");
	groupsID = (Integer) ((Groups) Orm.select(groups, search).get(0))
		.getData(groups.getPrimaryKeyName());

	// cree le user Admin
	user = new User();
	user.setData("name", "admin");
	user.setData("password", "aaa");
	user.setData("groupsID", groupsID);
	user.newE();

	groupsRight = new GroupsRight();

	// retrouve les rightID
	search.clear();
	search.add("moduleName='" + getSystemName() + "'");

	for (AbstractOrmEntity currentRight : Orm.select(new Right(), search))
	{
	    rightID = (Integer) ((Right) currentRight).getData(currentRight
		    .getPrimaryKeyName());

	    // cree le GroupsRight
	    groupsRight.setData("groupsID", groupsID);
	    groupsRight.setData("rightID", rightID);
	    groupsRight.newE();
	}
    }
}
