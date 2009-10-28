package newtonERP.module.generalEntity;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import modules.userRightModule.actions.Login;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.Module;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author Guillaume Lacasse
 * 
 *         A loging form
 */
public class LoginForm extends AbstractEntity implements PromptViewable
{
    private Module defaultModule;
    private Hashtable<String, String> inputList = new Hashtable<String, String>();
    private String currentSelectedUser;
    private Vector<String> alertMessageList;

    /**
     * @param currentSelectedUser the currecntly selected user
     * @throws Exception an exception that can occur
     */
    public LoginForm(String currentSelectedUser) throws Exception
    {
	this.currentSelectedUser = currentSelectedUser;
	defaultModule = new UserRightModule();
	alertMessageList = new Vector<String>();
    }

    @Override
    public String getButtonCaption()
    {
	return "Entrer";
    }

    @Override
    public AbstractAction getCurrentAction()
    {
	return new Login();
    }

    @Override
    public Module getCurrentModule()
    {
	return defaultModule;
    }

    @Override
    public Hashtable<String, FlagPool> getFlagPoolList()
    {
	// TODO Auto-generated method stub
	return new Hashtable<String, FlagPool>();
    }

    @Override
    public Hashtable<String, String> getInputList() throws OrmException
    {
	inputList.put("name", currentSelectedUser);
	inputList.put("password", "");
	return inputList;
    }

    @Override
    public String getLabelName(String inputName)
    {
	if (inputName == "name")
	    return "Utilisateur";
	else if (inputName == "password")
	    return "Mot de passe";
	else
	    return "nom de champ invalide";
    }

    @Override
    public String getPromptMessage()
    {
	return "Identification";
    }

    @Override
    public boolean isFieldHidden(String fieldName)
    {
	if (fieldName.equals("password"))
	    return true;
	return false;
    }

    @Override
    public ListOfValue tryMatchListOfValue(String fieldKeyName)
    {
	return null;
    }

    public void addAlertMessage(String string)
    {
	alertMessageList.add(string);
    }

    @Override
    public Vector<String> getAlertMessageList()
    {
	return alertMessageList;
    }

}
