package newtonERP.module.generalEntity;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.field.Field;
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
    private Hashtable<String, AbstractAction> globalActionButtonList;
    private Hashtable<String, AbstractAction> specificActionButtonList;
    private Vector<AbstractOrmEntity> data = new Vector<AbstractOrmEntity>();
    private AbstractOrmEntity internalEntityDefinition;
    private HashSet<String> buttonConfirmList = new HashSet<String>();

    /**
     * @param internalEntityDefinition the internal entity defenition
     */
    public EntityList(AbstractOrmEntity internalEntityDefinition)
	    throws Exception
    {
	this.internalEntityDefinition = internalEntityDefinition;
	specificActionButtonList = buildSpecificActionButtonList();
	addButtonConfirm("Delete");
    }

    private void addButtonConfirm(String string)
    {
	buttonConfirmList.add(string);
    }

    @Override
    public Vector<String> getColumnTitleList() throws Exception
    {
	Vector<String> columnTitleList = new Vector<String>();

	Set<String> shortKeySet = getRowList().get(0).keySet();
	String currentName;

	for (String shortKey : shortKeySet)
	{
	    try
	    {
		Field currentField = internalEntityDefinition.getFields()
			.getField(shortKey);
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
	    globalActionButtonList.put("Nouveau "
		    + internalEntityDefinition.getClass().getSimpleName(),
		    new BaseAction("New", internalEntityDefinition));
	}
	return globalActionButtonList;
    }

    @Override
    public Vector<Map<String, String>> getRowList() throws Exception
    {
	Vector<Map<String, String>> userListInfo = new Vector<Map<String, String>>();

	TreeMap<String, String> entityInfo;

	internalEntityDefinition.initFields();

	if (data.size() < 1)
	    data.add(internalEntityDefinition);

	for (AbstractOrmEntity entity : data)
	{
	    entityInfo = new TreeMap<String, String>();
	    for (Field field : entity.getFields())
	    {
		if (internalEntityDefinition
			.isFieldHidden(field.getShortName()))
		    continue;

		ListOfValue listOfValue = entity.tryMatchListOfValue(field
			.getShortName());
		if (listOfValue != null)
		    entityInfo.put(listOfValue.getLabelName(), listOfValue
			    .getForeignValue(field.getDataString()));
		else
		    entityInfo.put(field.getShortName(), field.getDataString());
	    }

	    userListInfo.add(entityInfo);
	}
	return userListInfo;
    }

    /**
     * @param entity the entity to add
     */
    public void addEntity(AbstractOrmEntity entity)
    {
	data.add(entity);
    }

    private Hashtable<String, AbstractAction> buildSpecificActionButtonList()
    {
	specificActionButtonList = new Hashtable<String, AbstractAction>();
	specificActionButtonList.put("Modifier", new BaseAction("Edit",
		internalEntityDefinition));
	specificActionButtonList.put("Effacer", new BaseAction("Delete",
		internalEntityDefinition));
	return specificActionButtonList;
    }

    @Override
    public Hashtable<String, AbstractAction> getSpecificActionButtonList()
    {
	return specificActionButtonList;
    }

    /**
     * @param caption
     * @param action
     */
    public void addSpecificActionButtonList(String caption,
	    AbstractAction action)
    {
	specificActionButtonList.put(caption, action);
    }

    public String getKeyName()
    {
	return internalEntityDefinition.getPrimaryKeyName();
    }

    public String getKeyValue(int rowNumber)
    {
	return data.get(rowNumber).getFields().getField(getKeyName())
		.getDataString();
    }

    @Override
    public String getInternalElementName()
    {
	return internalEntityDefinition.getClass().getSimpleName();
    }

    @Override
    public Iterator<AbstractOrmEntity> iterator()
    {
	return data.iterator();
    }

    @Override
    public Set<String> getButtonConfirmList()
    {
	return buttonConfirmList;
    }

    /**
     * @param string caption du bouton à enlever
     */
    public void removeSpecificActionButton(String string)
    {
	specificActionButtonList.remove(string);
    }
}
