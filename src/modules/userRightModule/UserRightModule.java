package modules.userRightModule;

import java.util.Vector;

import modules.userRightModule.entityDefinitions.Groups;
import modules.userRightModule.entityDefinitions.GroupsRight;
import modules.userRightModule.entityDefinitions.Right;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.Authentication;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.serveur.ConfigManager;

/**
 * @author Gabriel Therrien, Guillaume Lacasse, CloutierJo
 * 
 *         Class representing the user right module. Base module for the ERP.
 */
public class UserRightModule extends newtonERP.module.Module
{
	/**
	 * Default constructor for the user right module initializing himself. Adds
	 * the actions and the entity defenitions
	 */
	public UserRightModule()
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

	public void initDB()
	{
		Groups groups;
		User user;
		Right right;
		int rightID;
		GroupsRight groupsRight;

		// ***************** unlogged User ****************
		// cree le groupe
		groups = new Groups();
		groups.setData("groupName", "unLogedGroup");
		groups.newE();

		// cree le user unLogedUser
		user = new User();
		user.setData("name", "unLogedUser");
		user.setData("password", "");
		user.setData(groups.getForeignKeyName(), groups.getPrimaryKeyValue());
		user.newE();

		// retrouve le rightID du login
		right = new Right();
		right.setData("moduleName", getSystemName());
		right.setData("actionName", "Login");
		right = (Right) right.get(right).get(0);
		rightID = (Integer) right.getData(right.getPrimaryKeyName());

		// cree le GroupsRight pour donne acces au login a unloggedUser
		groupsRight = new GroupsRight();
		groupsRight.setData(groups.getForeignKeyName(),
				groups.getPrimaryKeyValue());
		groupsRight.setData(right.getPrimaryKeyName(), rightID);
		groupsRight.newE();

		// ***************** admin User ****************

		// cree le groupe
		groups = new Groups();
		groups.setData("groupName", "admin");
		groups.newE();

		// cree le user Admin
		user = new User();
		user.setData("name",
				ConfigManager.loadStringAttrProperty("default-user", "name"));
		user.setData("password", ConfigManager.loadStringAttrProperty(
				"default-user", "password"));
		user.setData(groups.getPrimaryKeyName(), groups.getPrimaryKeyValue());
		user.newE();

		groupsRight = new GroupsRight();

		// retrouve les rightID pour donne les droit au group admin
		right = new Right();
		right.setData("moduleName", getSystemName());
		for (AbstractOrmEntity currentRight : Orm.select(right))
		{
			rightID = currentRight.getPrimaryKeyValue();

			// cree le GroupsRight
			groupsRight.setData("groupsID", groups.getPrimaryKeyValue());
			groupsRight.setData("rightID", rightID);
			groupsRight.newE();
		}
	}

	/**
	 * @param groupName nom du groupe
	 * @param actionName nom de l'action
	 * @param entityName nom de l'entité
	 */
	public void addGroupsRight(String groupName, String actionName,
			String entityName)
	{
		// todo: correct deprecate
		Groups groups = (Groups) Orm.getOrCreateEntity(new Groups(),
				"groupName", groupName);
		// todo: correct deprecate
		Right right = (Right) Orm.getOrCreateEntity(new Right(), "actionName",
				actionName, "entityName", entityName);

		// todo: correct deprecate
		Orm.getOrCreateEntity(new GroupsRight(), groups.getForeignKeyName(),
				groups.getPrimaryKeyValue().toString(), right
						.getForeignKeyName(), right.getPrimaryKeyValue()
						.toString());
	}

	/**
	 * @param groupName group's name
	 * @param rightActionName right's name
	 */
	public void addGroupsRight(String groupName, String rightActionName)
	{
		// todo: correct deprecate
		Groups groups = (Groups) Orm.getOrCreateEntity(new Groups(),
				"groupName", groupName);
		// todo: correct deprecate
		Right right = (Right) Orm.getOrCreateEntity(new Right(), "actionName",
				rightActionName);

		// todo: correct deprecate
		Orm.getOrCreateEntity(new GroupsRight(), groups.getForeignKeyName(),
				groups.getPrimaryKeyValue().toString(), right
						.getForeignKeyName(), right.getPrimaryKeyValue()
						.toString());
	}

	/**
	 * @param groupName group's name
	 * @param rightActionName right's name
	 */
	public void removeGroupsRight(String groupName, String rightActionName)
	{
		// todo: correct deprecate
		Groups groups = (Groups) Orm.getOrCreateEntity(new Groups(),
				"groupName", groupName);
		// todo: correct deprecate
		Right right = (Right) Orm.getOrCreateEntity(new Right(), "actionName",
				rightActionName);

		// todo: correct deprecate
		Orm.delete(new GroupsRight(), groups.getForeignKeyName(), groups
				.getPrimaryKeyValue().toString(), right.getForeignKeyName(),
				right.getPrimaryKeyValue().toString());
	}

	/**
	 * @param actionLink un actionLink
	 * @return vrai si les permission sont présentement accordées, sinon, faux
	 */
	public boolean isPermissionAllowed(newtonERP.common.ActionLink actionLink)
	{
		String userName = Authentication.getCurrentUserName();
		Groups groups = tryGetGroupsForUser(userName);
		String entityName;
		AbstractEntity entity;

		if (groups == null)
			return false;

		AbstractAction action = actionLink.getAction();
		String actionName = action.getSystemName();

		if (action instanceof BaseAction)
			entity = ((BaseAction) action).getEntity();
		else
			entity = action.getEntityUsable();

		if (entity == null)
			entityName = "";
		else
			entityName = entity.getSystemName();

		Right right = tryGetRight(actionName, entityName);

		if (right == null)
			right = tryGetRight(actionName);

		return isGroupsRightExists(groups.getPrimaryKeyValue(),
				right.getPrimaryKeyValue());
	}

	private modules.userRightModule.entityDefinitions.Right tryGetRight(
			String actionName)
	{
		Right right = new Right();
		right.setData("actionName", actionName);

		Vector<AbstractOrmEntity> rightList = right.get();
		if (rightList.size() > 0)
			return (Right) rightList.get(0);
		return null;
	}

	private modules.userRightModule.entityDefinitions.Right tryGetRight(
			String actionName, String entityName)
	{
		Right right = new Right();
		right.setData("actionName", actionName);
		right.setData("entityName", entityName);

		Vector<AbstractOrmEntity> rightList = right.get();
		if (rightList.size() > 0)
			return (Right) rightList.get(0);
		return null;
	}

	private modules.userRightModule.entityDefinitions.Groups tryGetGroupsForUser(
			String userName)
	{
		User user = new User();
		user.setData("name", userName);

		Vector<AbstractOrmEntity> userList = user.get();

		if (userList.size() < 1)
			return null;

		user = (User) userList.get(0);

		Groups groups = (Groups) AccessorManager.getSingleAccessor(user,
				"groupsID");

		return groups;
	}

	private boolean isGroupsRightExists(Integer groupsID, Integer rightID)
	{
		GroupsRight groupsRight = new GroupsRight();
		groupsRight.setData("groupsID", groupsID);
		groupsRight.setData("rightID", rightID);

		Vector<AbstractOrmEntity> entityList = groupsRight.get();
		return entityList.size() > 0;
	}

}
