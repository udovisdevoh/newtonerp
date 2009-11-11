package newtonERP.module.generalEntity;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import newtonERP.common.NaturalMap;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.field.Field;
import newtonERP.viewers.viewables.ListViewable;
import newtonERP.viewers.viewables.PromptViewable;

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
     * @throws Exception exception lancée si création fail
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
	    globalActionButtonList = new Hashtable<String, AbstractAction>();
	    globalActionButtonList.put("Nouveau "
		    + internalEntityDefinition.getVisibleName(),
		    new BaseAction("New", internalEntityDefinition));
	}
	return globalActionButtonList;
    }

    @Override
    public Vector<NaturalMap<String, String>> getRowList() throws Exception
    {
	Vector<NaturalMap<String, String>> userListInfo = new Vector<NaturalMap<String, String>>();

	NaturalMap<String, String> entityInfo;

	internalEntityDefinition.initFields();

	data.insertElementAt(internalEntityDefinition, 0);

	int counter = 0;
	for (AbstractOrmEntity entity : data)
	{
	    entityInfo = new NaturalMap<String, String>();
	    for (Field field : entity.getFields())
	    {
		if (internalEntityDefinition
			.isFieldHidden(field.getShortName()))
		    continue;

		ListOfValue listOfValue = entity.tryMatchListOfValue(field
			.getShortName());

		String shortName = field.getShortName();
		String dataString = field.getDataString();

		if (counter == 0)
		{
		    if (listOfValue != null)
			entityInfo.put(listOfValue.getLabelName(), listOfValue
				.getLabelName());
		    else
			entityInfo.put(shortName, field.getName());
		}
		else
		{

		    if (listOfValue != null)
			entityInfo.put(listOfValue.getLabelName(), listOfValue
				.getForeignValue(dataString));
		    else if (internalEntityDefinition
			    .isMatchCheckBox(shortName))
		    {
			if (dataString == null)
			    dataString = "";
			if (dataString.equals("true"))
			{
			    entityInfo.put(shortName, " - oui - ");
			}
			else
			{
			    entityInfo.put(shortName, " - non - ");
			}
		    }
		    else if (internalEntityDefinition
			    .isMatchLongText(shortName))
		    {
			if (dataString == null)
			    dataString = "";

			if (dataString.length() > 64)
			    dataString = dataString.substring(0, 64) + "...";

			entityInfo.put(shortName, dataString);
		    }
		    else
		    {
			if (dataString == null)
			    dataString = "";
			entityInfo.put(shortName, dataString);
		    }
		}
	    }
	    userListInfo.add(entityInfo);
	    counter++;
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
     * @param caption nom visible du bouton
     * @param action action du bouton
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
    public String getVisibleInternalElementName()
    {
	return internalEntityDefinition.getVisibleName();
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

    @Override
    public boolean isListElementColumnMatchCurrencyFormat(String fieldName)
    {
	return internalEntityDefinition.isMatchCurrencyFormat(fieldName);
    }

    @Override
    public Vector<PromptViewable> getViewableRowList()
    {
	// TODO Auto-generated method stub
	return null;
    }
}
