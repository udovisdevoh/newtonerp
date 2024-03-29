package modules.userRightModule; 
 // TODO: clean up that file

import java.util.Vector;

import modules.userRightModule.entityDefinitions.Groups;
import modules.userRightModule.entityDefinitions.GroupsRight;
import modules.userRightModule.entityDefinitions.Right;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.ActionLink;
import newtonERP.common.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.serveur.ConfigManager;

/**
 * @author Gabriel Therrien, Guillaume Lacasse, CloutierJo Class representing the user right module. Base module for the
 *         ERP.
 */

@SuppressWarnings("deprecation")
public class UserRightModule extends Module {
	/**
	 * Default constructor for the user right module initializing himself. Adds the actions and the entity defenitions
	 */
	public UserRightModule() {
		super();
		setDefaultAction(new BaseAction("GetList", new User()));
		addGlobalActionMenuItem("Utilisateurs", new BaseAction("GetList", new User()));
		addGlobalActionMenuItem("Liste des droits", new BaseAction("GetList", new Right()));
		addGlobalActionMenuItem("Groupes", new BaseAction("GetList", new Groups()));
		setVisibleName("Gestion de droits");
	}

	@Override
	public void initDB() {
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
		groupsID = (Integer) ((Groups) Orm.getInstance().select(groups, search).get(0)).getData(groups
		        .getPrimaryKeyName());

		// cree le user unLogedUser
		user = new User();
		user.setData("name", "unLogedUser");
		user.setData("password", "");
		user.setData("groupsID", groupsID);
		Orm.getInstance().insert(user);

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
		groupsID = (Integer) ((Groups) Orm.getInstance().select(groups, search).get(0)).getData(groups
		        .getPrimaryKeyName());

		// cree le user Admin
		user = new User();
		user.setData("name", ConfigManager.loadStringAttrProperty("default-user", "name"));
		user.setData("password", ConfigManager.loadStringAttrProperty("default-user", "password"));
		user.setData("groupsID", groupsID);
		user.newE();

		groupsRight = new GroupsRight();

		// retrouve les rightID
		search.clear();
		search.add("moduleName='" + getSystemName() + "'");

		for(AbstractOrmEntity currentRight : Orm.getInstance().select(new Right(), search)){
			rightID = (Integer) ((Right) currentRight).getData(currentRight.getPrimaryKeyName());

			// cree le GroupsRight
			groupsRight.setData("groupsID", groupsID);
			groupsRight.setData("rightID", rightID);
			groupsRight.newE();
		}
	}

	/**
	 * @param groupName nom du groupe
	 * @param actionName nom de l'action
	 * @param entityName nom de l'entité
	 */
	public void addGroupsRight(String groupName, String actionName, String entityName) {
		Groups groups = (Groups) Orm.getInstance().getOrCreateEntity(new Groups(), "groupName", groupName);
		Right right = (Right) Orm.getInstance().getOrCreateEntity(new Right(), "actionName", actionName, "entityName",
		        entityName);

		Orm.getInstance().getOrCreateEntity(new GroupsRight(), groups.getForeignKeyName(),
		        groups.getPrimaryKeyValue().toString(), right.getForeignKeyName(),
		        right.getPrimaryKeyValue().toString());
	}

	/**
	 * @param groupName group's name
	 * @param rightActionName right's name
	 */
	public void addGroupsRight(String groupName, String rightActionName)

	{
		Groups groups = (Groups) Orm.getInstance().getOrCreateEntity(new Groups(), "groupName", groupName);
		Right right = (Right) Orm.getInstance().getOrCreateEntity(new Right(), "actionName", rightActionName);

		Orm.getInstance().getOrCreateEntity(new GroupsRight(), groups.getForeignKeyName(),
		        groups.getPrimaryKeyValue().toString(), right.getForeignKeyName(),
		        right.getPrimaryKeyValue().toString());
	}

	/**
	 * @param groupName group's name
	 * @param rightActionName right's name
	 */
	public void removeGroupsRight(String groupName, String rightActionName)

	{
		Groups groups = (Groups) Orm.getInstance().getOrCreateEntity(new Groups(), "groupName", groupName);
		Right right = (Right) Orm.getInstance().getOrCreateEntity(new Right(), "actionName", rightActionName);

		Orm.getInstance().delete(new GroupsRight(), groups.getForeignKeyName(), groups.getPrimaryKeyValue().toString(),
		        right.getForeignKeyName(), right.getPrimaryKeyValue().toString());
	}

	/**
	 * @param actionLink un actionLink
	 * @return vrai si les permission sont présentement accordées, sinon, faux
	 */
	public boolean isPermissionAllowed(ActionLink actionLink) {
		String userName = Authentication.getCurrentUserName();
		Groups groups = tryGetGroupsForUser(userName);
		String entityName;
		AbstractEntity entity;

		if(groups == null){
			return false;
		}

		AbstractAction action = actionLink.getAction();
		String actionName = action.getSystemName();

		if(action instanceof BaseAction){
			entity = ((BaseAction) action).getEntity();
		}else{
			entity = action.getEntityUsable();
		}

		if(entity == null){
			entityName = "";
		}else{
			entityName = entity.getSystemName();
		}

		Right right = tryGetRight(actionName, entityName);

		if(right == null){
			right = tryGetRight(actionName);
		}

		return isGroupsRightExists(groups.getPrimaryKeyValue(), right.getPrimaryKeyValue());
	}

	private Right tryGetRight(String actionName) {
		Right right = new Right();
		right.setData("actionName", actionName);

		Vector<AbstractOrmEntity> rightList = right.get();
		if(rightList.size() > 0){
			return (Right) rightList.get(0);
		}
		return null;
	}

	private Right tryGetRight(String actionName, String entityName)

	{
		Right right = new Right();
		right.setData("actionName", actionName);
		right.setData("entityName", entityName);

		Vector<AbstractOrmEntity> rightList = right.get();
		if(rightList.size() > 0){
			return (Right) rightList.get(0);
		}
		return null;
	}

	private Groups tryGetGroupsForUser(String userName) {
		User user = new User();
		user.setData("name", userName);

		Vector<AbstractOrmEntity> userList = user.get();

		if(userList.size() < 1){
			return null;
		}

		user = (User) userList.get(0);

		Groups groups = (Groups) AccessorManager.getSingleAccessor(user, "groupsID");

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
