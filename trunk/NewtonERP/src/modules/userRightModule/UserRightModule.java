package modules.userRightModule;

import java.util.Vector;

import modules.userRightModule.actions.DeleteGroup;
import modules.userRightModule.actions.DeleteUser;
import modules.userRightModule.actions.EditUser;
import modules.userRightModule.actions.GetUser;
import modules.userRightModule.actions.GetUserList;
import modules.userRightModule.actions.NewUser;
import modules.userRightModule.actions.NewUserType;
import modules.userRightModule.actions.RightCheck;
import modules.userRightModule.entityDefinitions.GroupRight;
import modules.userRightModule.entityDefinitions.Groups;
import modules.userRightModule.entityDefinitions.Right;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.Module;
import newtonERP.module.ModuleException;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

public class UserRightModule extends Module
{

    /**
     * Fait Par Gabriel Therrien et Guillaume Lacasse Le module de UserRight
     * 
     * @param args
     */
    public UserRightModule() throws ModuleException
    {
	// On cré les référence vers les définitions d'entités
	addDefinitinEntity(new User());
	addDefinitinEntity(new Groups());
	addDefinitinEntity(new GroupRight());
	addDefinitinEntity(new Right());

	// On cré les références vers les actions du module
	addAction(new NewUser());
	addAction(new EditUser());
	addAction(new DeleteUser());
	addAction(new NewUserType());
	addAction(new GetUserList());
	addAction(new DeleteGroup());
	addAction(new RightCheck());
	addAction(new GetUser());

    }

    public void firstBuild()
    {
	try
	{
	    // cree le groupe
	    Groups group = new Groups();
	    group.setGroupName("admin");
	    Orm.insert(group);

	    // retourve le groupID
	    Vector<String> search = new Vector<String>();
	    search.add("Newton_groupName=" + "'admin'");
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
		// cree le right
		Right right = new Right();
		right.setActionName(action);
		right.setModuleName(getClass().getSimpleName());
		Orm.insert(right);

		// retrouve le rightID
		search.clear();
		search.add("Newton_ActionName='" + action
			+ "' AND Newton_ModuleName='"
			+ this.getClass().getSimpleName() + "'");
		System.out.println("******"
			+ Orm.select(right, search).get(0).getClass()
				.getSimpleName());
		right = ((Right) Orm.select(right, search).get(0));
		rightID = right.getPKrightID();

		// cree le groupRight
		groupRight.setGroupID(groupID);
		groupRight.setRightID(rightID);
		Orm.insert(groupRight);
	    }
	} catch (OrmException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
