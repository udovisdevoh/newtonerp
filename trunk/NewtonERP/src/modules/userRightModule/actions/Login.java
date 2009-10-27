package modules.userRightModule.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.User;
import newtonERP.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.generalEntity.LoginForm;
import newtonERP.module.generalEntity.StaticTextEntity;

/**
 * Action class that creates all the rights for every module
 * 
 * @author cloutierJo
 */
public class Login extends AbstractAction
{
    /**
     * constructeur
     */
    public Login()
    {
	super(new User()); // ne travaille pas avec une entity
    }

    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	if (parameters.containsKey("submit"))
	{
	    for (Field field : entity.getFields().getFields())
	    {
		field.setOperator("="); // TODO: on peu tu mettre l'operateur
		// par defaut a = ???
	    }

	    Vector<AbstractOrmEntity> userList = ((AbstractOrmEntity) (entity))
		    .get((AbstractOrmEntity) (entity));

	    if (userList.size() > 0 && userList.get(0).getData("name") != null)
	    {
		Authentication.setCurrentUserName(entity.getDataString("name"));
		return new StaticTextEntity("Bienvenue "
			+ userList.get(0).getData("name"));
	    }
	}

	/*
	 * User retEntity = new User(); retEntity.setCurrentAction(this);
	 * retEntity.setCurrentModule(new UserRightModule());
	 * 
	 * return retEntity;
	 */

	String currentLoginName = parameters.get("name");
	if (currentLoginName == null)
	    currentLoginName = "";

	return new LoginForm(currentLoginName);
    }
}
