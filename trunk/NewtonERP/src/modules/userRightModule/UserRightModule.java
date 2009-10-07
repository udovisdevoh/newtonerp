package modules.userRightModule;

import modules.userRightModule.actions.DeleteUser;
import modules.userRightModule.actions.DeleteUserType;
import modules.userRightModule.actions.EditUser;
import modules.userRightModule.actions.EditUserType;
import modules.userRightModule.actions.GetUserList;
import modules.userRightModule.actions.GetUserTypeList;
import modules.userRightModule.actions.NewUser;
import modules.userRightModule.actions.NewUserType;
import modules.userRightModule.entityDefinitions.Group;
import modules.userRightModule.entityDefinitions.GroupRight;
import modules.userRightModule.entityDefinitions.Right;
import modules.userRightModule.entityDefinitions.User;
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
	addDefinitinEntity(new User());
	addDefinitinEntity(new Group());
	addDefinitinEntity(new GroupRight());
	addDefinitinEntity(new Right());

	// On cré les références vers les actions du module
	addAction(new NewUser());
	addAction(new EditUser());
	addAction(new DeleteUser());
	addAction(new NewUserType());
	addAction(new EditUserType());
	addAction(new DeleteUserType());
	addAction(new GetUserList());
	addAction(new GetUserTypeList(), true);

    }
}
