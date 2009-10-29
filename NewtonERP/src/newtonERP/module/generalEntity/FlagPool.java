package newtonERP.module.generalEntity;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.orm.Orm;
import newtonERP.viewers.viewables.CheckListViewable;

/**
 * @author Guillaume Lacasse cette classe sert à faire des liste de flag qu'on
 *         peut attribuer à une entité lorsque ces flags sont dans une table
 *         étrangère passant par une table intermédiaire. Exemple: attribution
 *         de droits pour un groupe (ça passe par GroupRight)
 */
public class FlagPool implements CheckListViewable
{
    private AbstractOrmEntity sourceEntityDefinition;
    private String sourceKeyName;
    private String sourceKeyValue;
    private String visibleDescription;
    private AbstractOrmEntity intermediateEntityDefinition;
    private AbstractOrmEntity foreignEntityDefinition;
    private String intermediateKeyIn;
    private String intermediateKeyOut;
    private String foreignKey;
    private String[] foreignDescriptionUiControls;

    /**
     * @param sourceEntityDefinition Definition de l'entité de source, exemple:
     *            groupe
     * @param visibleDescription Description visible
     * @param intermediateEntityDefinition Entité de table intermédiaire,
     *            exemple: GroupRight
     * @param intermediateKeyIn Colonne d'entré de table intermédiaire, exemple:
     *            groupID
     * @param intermediateKeyOut Colonne de sortie de table intermédiaire,
     *            exemple: rightID
     * @param foreignEntityDefinition entité de table étrangère, exemple: Right
     * @param foreignKey clef d'identification de table étrangère, exemple:
     *            PKrightID
     * @param foreignDescriptionUiControls liste de colonne de description de
     *            table étrangère, exemple: Action, Module
     */
    public FlagPool(AbstractOrmEntity sourceEntityDefinition,
	    String visibleDescription,
	    AbstractOrmEntity intermediateEntityDefinition,
	    String intermediateKeyIn, String intermediateKeyOut,
	    AbstractOrmEntity foreignEntityDefinition, String foreignKey,
	    String[] foreignDescriptionUiControls)
    {
	this.visibleDescription = visibleDescription;
	this.intermediateEntityDefinition = intermediateEntityDefinition;
	this.intermediateKeyIn = intermediateKeyIn;
	this.intermediateKeyOut = intermediateKeyOut;
	this.foreignEntityDefinition = foreignEntityDefinition;
	this.foreignKey = foreignKey;
	this.foreignDescriptionUiControls = foreignDescriptionUiControls;
    }

    @Override
    public String getVisibleDescription()
    {
	return visibleDescription;
    }

    @Override
    public TreeMap<String, String> getAvailableElementList() throws Exception
    {
	Vector<AbstractOrmEntity> entityList = Orm.select(
		foreignEntityDefinition, null);

	TreeMap<String, String> availableElementList = new TreeMap<String, String>();

	String description;
	for (AbstractOrmEntity entity : entityList)
	{
	    description = getForeignDescription(entity);

	    while (availableElementList.containsKey(description))
		description += "_bis";

	    availableElementList.put(description, intermediateEntityDefinition
		    .getClass().getSimpleName()
		    + "."
		    + intermediateKeyOut
		    + "."
		    + entity.getDataString(foreignKey));
	}

	return availableElementList;
    }

    private String getForeignDescription(AbstractOrmEntity entity)
    {
	String description = "";
	String currentValue;
	for (String key : foreignDescriptionUiControls)
	{
	    currentValue = entity.getDataString(key);
	    if (currentValue == null)
		currentValue = "null";

	    if (!currentValue.equals("null") || description.equals(""))
		description += currentValue + " ";
	    else
		description += " - ";
	}
	return description.trim();
    }

    @Override
    public HashSet<String> getCheckedElementList() throws Exception

    {
	if (sourceKeyName == null || sourceKeyValue == null)
	    throw new Exception(
		    "Vous devez préablablement faire une query(clef, valeur) avant d'intéroger les éléments du flag pool");

	HashSet<String> checkedElementList = new HashSet<String>();

	// TODO Auto-generated method stub

	try
	{
	    intermediateEntityDefinition.setData(intermediateKeyIn,
		    sourceKeyValue);
	} catch (FieldNotCompatibleException e)
	{
	    int sourceKeyValueNumber = Integer.parseInt(sourceKeyValue);
	    intermediateEntityDefinition.setData(intermediateKeyIn,
		    sourceKeyValueNumber);
	}

	Vector<AbstractOrmEntity> checkedEntityList = Orm
		.select(intermediateEntityDefinition);

	for (AbstractOrmEntity entity : checkedEntityList)
	    checkedElementList.add(intermediateEntityDefinition.getClass()
		    .getSimpleName()
		    + "."
		    + intermediateKeyOut
		    + "."
		    + entity.getDataString(intermediateKeyOut));

	return checkedElementList;
    }

    public void query(String sourceKeyName, Integer sourceKeyValue)
    {
	this.sourceKeyName = sourceKeyName;
	this.sourceKeyValue = sourceKeyValue.toString();
    }

    public AbstractOrmEntity getIntermediateEntityDefinition()
    {
	return intermediateEntityDefinition;
    }

    public AbstractOrmEntity getForeignEntityDefinition()
    {
	return foreignEntityDefinition;
    }

    public String getIntermediateKeyIn()
    {
	return intermediateKeyIn;
    }

    public String getIntermediateKeyOut()
    {
	return intermediateKeyOut;
    }

    public String getSourceKeyValue()
    {
	return sourceKeyValue;
    }

    public AbstractOrmEntity getSourceEntityDefinition()
    {
	return sourceEntityDefinition;
    }
}
