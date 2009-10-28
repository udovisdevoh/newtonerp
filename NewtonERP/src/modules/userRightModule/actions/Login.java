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
	String currentLoginName = parameters.get("name");
	if (currentLoginName == null)
	    currentLoginName = "";

	LoginForm loginForm = new LoginForm(currentLoginName);

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
		if (IsGroupValid((User) (userList.get(0))))
		{
		    Authentication.setCurrentUserName(entity
			    .getDataString("name"));
		    return new StaticTextEntity("Bienvenue "
			    + userList.get(0).getData("name"));
		}
		else
		{
		    loginForm.addAlertMessage("Groupe invalide ou corrompu");
		    return loginForm;
		}
	    }
	    loginForm.addAlertMessage("Nom ou mot de passe invalide");
	}

	return loginForm;
    }

    private boolean IsGroupValid(User user)
    {
	AbstractOrmEntity groups;
	try
	{
	    groups = user.getGroupsEntity();
	} catch (Exception e)
	{
	    return false;
	}
	if (groups == null)
	    return false;
	return true;
    }
}
