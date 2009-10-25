package newtonERP.module;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.exception.EntityException;
import newtonERP.module.field.Field;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.viewers.viewables.ListViewable;

/**
 * @author Guillaume Lacasse
 * 
 */
public class EntityList extends AbstractEntity implements ListViewable,
	Iterable<AbstractOrmEntity>
{
    // Je ne sais pas comment initialiser un vector avec des valeurs par défault
    // en JAVA
    // -Guillaume
    private static Hashtable<String, AbstractAction> globalActionButtonList;
    private static Hashtable<String, AbstractAction> specificActionButtonList;
    private Module currentModule;
    private Vector<AbstractOrmEntity> data = new Vector<AbstractOrmEntity>();

    @Override
    public Vector<String> getColumnTitleList() throws OrmException
    {
	// return data.get(0).getFields().getLongFieldNameList();

	Vector<String> columnTitleList = new Vector<String>();

	Set<String> shortKeySet = getRowList().get(0).keySet();
	String currentName;

	for (String shortKey : shortKeySet)
	{
	    AbstractOrmEntity definition = data.get(0);
	    try
	    {
		Field currentField = definition.getFields().getField(shortKey);
		currentName = currentField.getName();
	    } catch (Exception e)
	    {
		currentName = shortKey;
	    }

	    columnTitleList.add(currentName);
	}

	return columnTitleList;
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
		    "New", new User()));
	}
	return globalActionButtonList;
    }

    @Override
    public Vector<Map<String, String>> getRowList() throws OrmException
    {
	Vector<Map<String, String>> userListInfo = new Vector<Map<String, String>>();

	TreeMap<String, String> entityInfo;
	for (AbstractOrmEntity entity : data)
	{
	    entityInfo = new TreeMap<String, String>();
	    for (Field field : entity.getFields())
	    {
		entityInfo.put(field.getShortName(), field.getDataString());

		ListOfValue listOfValue = entity.tryMatchListOfValue(field
			.getShortName());
		if (listOfValue != null)
		    entityInfo.put(listOfValue.getLabelName(), listOfValue
			    .getForeignValue(field.getDataString()));
	    }

	    userListInfo.add(entityInfo);
	}
	return userListInfo;
    }

    public void addEntity(AbstractOrmEntity entity)
    {
	data.add(entity);
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

    public String getKeyValue(int rowNumber)
    {
	return data.get(rowNumber).getFields().getField(getKeyName())
		.getDataString();
    }

    @Override
    public String getInternalElementName()
    {
	return data.get(0).getClass().getSimpleName();
    }

    @Override
    public Iterator<AbstractOrmEntity> iterator()
    {
	return data.iterator();
    }
}
