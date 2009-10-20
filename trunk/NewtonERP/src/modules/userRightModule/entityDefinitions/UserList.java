package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.EntityException;
import newtonERP.module.Module;
import newtonERP.viewers.viewables.ListViewable;

/**
 * @author Guillaume Lacasse
 * 
 */
public class UserList extends AbstractEntity implements ListViewable
{
    // Je ne sais pas comment initialiser un vector avec des valeurs par défault
    // en JAVA
    // -Guillaume
    private static Vector<String> columnTitleList;
    private static Hashtable<String, AbstractAction> globalActionButtonList;
    private static Hashtable<String, AbstractAction> specificActionButtonList;
    private Vector<User> data = new Vector<User>();

    @Override
    public Vector<String> getColumnTitleList()
    {
	if (columnTitleList == null)
	{
	    // Ça devrait être dans le constructeur statique s'il y en avait un
	    // en
	    // JAVA
	    // La manière intelligente d'initialiser les valeurs seraient
	    // d'initialiser le vecteur avec
	    // des valeurs par défault mais je ne sais pas comment en JAVA
	    columnTitleList = new Vector<String>();
	    columnTitleList.add("PKuserID");
	    columnTitleList.add("Nom");
	    columnTitleList.add("Groupe");
	}
	return columnTitleList;
    }

    @Override
    public String getCurrentUserName()
    {
	// TODO Remove hack
	return "admin";
    }

    @Override
    public Hashtable<String, AbstractAction> getGlobalActionButtonList()
    {
	if (globalActionButtonList == null)
	{
	    // TODO: remove dummy code: must not recreate actions
	    globalActionButtonList = new Hashtable<String, AbstractAction>();
	    globalActionButtonList.put("Nouvel utilisateur", new NewUser());
	}
	return globalActionButtonList;
    }

    @Override
    public Vector<Vector<String>> getRowValues()
    {
	Vector<Vector<String>> userListInfo = new Vector<Vector<String>>();

	Vector<String> userInfo;
	for (User user : data)
	{
	    userInfo = new Vector<String>();
	    userInfo.add(user.getDataString("PKuserID"));
	    userInfo.add(user.getDataString("name"));
	    userInfo.add(user.getDataString("groupID"));
	    userListInfo.add(userInfo);
	}
	return userListInfo;
    }

    public void addUser(User user)
    {
	data.add(user);
    }

    @Override
    public Hashtable<String, AbstractAction> getSpecificActionButtonList()
    {
	if (specificActionButtonList == null)
	{
	    // TODO: remove dummy code: must not recreate actions
	    specificActionButtonList = new Hashtable<String, AbstractAction>();
	    specificActionButtonList.put("Edit", new EditUser());
	    specificActionButtonList.put("Delete", new DeleteUser());
	}
	return specificActionButtonList;
    }

    @Override
    public Module getSubmitModule() throws EntityException
    {
	// TODO Auto-generated method stub
	/*
	 * if (submitModule == null) throw new EntityException(
	 * "Missing submit module, set it with setSubmitModule()");
	 * 
	 * return submitModule;
	 */

	return new UserRightModule();
    }

    @Override
    public String getTitle()
    {
	return "Liste des utilisateurs";
    }
}
