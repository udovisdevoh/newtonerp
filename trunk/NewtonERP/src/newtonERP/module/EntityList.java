package newtonERP.module;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.exception.EntityException;
import newtonERP.module.field.Field;
import newtonERP.viewers.viewables.ListViewable;

/**
 * @author Guillaume Lacasse
 * 
 */
public class EntityList extends AbstractEntity implements ListViewable
{
    // Je ne sais pas comment initialiser un vector avec des valeurs par défault
    // en JAVA
    // -Guillaume
    private static Vector<String> columnTitleList;
    private static Hashtable<String, AbstractAction> globalActionButtonList;
    private static Hashtable<String, AbstractAction> specificActionButtonList;
    private Module currentModule;
    private Vector<AbstractOrmEntity> data = new Vector<AbstractOrmEntity>();

    @Override
    public Vector<String> getColumnTitleList()
    {
	return data.get(0).getFields().getLongFieldNameList();
    }

    @Override
    public Hashtable<String, AbstractAction> getGlobalActionButtonList()
    {
	if (globalActionButtonList == null)
	{
	    // TODO: remove dummy code: must not recreate actions
	    globalActionButtonList = new Hashtable<String, AbstractAction>();
	    // TODO Make sure new User() is the appropriated behavior we want
	    globalActionButtonList.put("Nouvel utilisateur", new BaseAction(
		    "new", new User()));
	}
	return globalActionButtonList;
    }

    @Override
    public Vector<Vector<String>> getRowValues()
    {
	Vector<Vector<String>> userListInfo = new Vector<Vector<String>>();

	Vector<String> userInfo;
	for (AbstractOrmEntity entity : data)
	{
	    userInfo = new Vector<String>();
	    for (Field field : entity.getFields())
		userInfo.add(field.getDataString());

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
	    // TODO Make sure new User() is the appropriated behavior we want
	    specificActionButtonList.put("Modifier", new BaseAction("Edit",
		    new User()));
	    specificActionButtonList.put("Effacer", new BaseAction("Delete",
		    new User()));
	}
	return specificActionButtonList;
    }

    @Override
    public Module getCurrentModule() throws EntityException
    {
	if (currentModule == null)
	    throw new EntityException(
		    "Vous devez setter le module courrant dans le viewer avec setCurrentModule()");

	return currentModule;
    }

    public void setCurrentModule(Module module)
    {
	currentModule = module;
    }

    public String getKeyName()
    {
	return data.get(0).getPrimaryKeyName();
    }

    public String getKeyValue()
    {
	return data.get(0).getFields().getField(getKeyName()).getDataString();
    }
}
