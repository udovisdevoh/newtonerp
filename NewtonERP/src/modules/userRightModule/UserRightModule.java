package modules.userRightModule;

import modules.userRightModule.actions.DeleteUser;
import modules.userRightModule.actions.DeleteUserType;
import modules.userRightModule.actions.EditGroup;
import modules.userRightModule.actions.EditUser;
import modules.userRightModule.actions.EditUserType;
import modules.userRightModule.actions.GetUserList;
import modules.userRightModule.actions.GetUserTypeList;
import modules.userRightModule.actions.NewGroup;
import modules.userRightModule.actions.NewUser;
import modules.userRightModule.actions.NewUserType;
import modules.userRightModule.entityDefinitions.Group;
import modules.userRightModule.entityDefinitions.GroupRight;
import modules.userRightModule.entityDefinitions.Right;
import modules.userRightModule.entityDefinitions.User;
import modules.userRightModule.moduleGetters.TaskRightGetter;
import modules.userRightModule.moduleGetters.UserGetter;
import modules.userRightModule.moduleGetters.UserListGetter;
import modules.userRightModule.moduleGetters.UserTypeGetter;
import modules.userRightModule.moduleGetters.UserTypeListGetter;
import newtonERP.module.Module;
import newtonERP.module.ModuleException;

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
	definitionEntityList.add(new User());
	definitionEntityList.add(new Right());
	definitionEntityList.add(new Group());
	definitionEntityList.add(new GroupRight());

	// On cré les références vers les actions du module
	actionList.put("NewUser", new NewUser());
	actionList.put("EditUser", new EditUser());
	actionList.put("DeleteUser", new DeleteUser());
	actionList.put("NewUserType", new NewUserType());
	actionList.put("EditUserType", new EditUserType());
	actionList.put("DeleteUserType", new DeleteUserType());
	actionList.put("GetUserList", new GetUserList());
	actionList.put("GetUserTypeList", new GetUserTypeList());
	actionList.put("NewGroup", new NewGroup());
	actionList.put("EditGroup", new EditGroup());
	// On définit l'action par défaut (index)
	setDefaultAction("GetUserTypeList");

	// On cré les références vers les getters du module
	moduleGetterList.put("TaskRightGetter", new TaskRightGetter());
	moduleGetterList.put("UserGetter", new UserGetter());
	moduleGetterList.put("UserListGetter", new UserListGetter());
	moduleGetterList.put("UserTypeGetter", new UserTypeGetter());
	moduleGetterList.put("UserTypeListGetter", new UserTypeListGetter());

	// On défini le le moduleGetter par défaut
	setDefaultModuleGetter("UserTypeListGetter");
    }
}
