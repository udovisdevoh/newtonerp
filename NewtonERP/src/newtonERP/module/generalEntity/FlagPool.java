package newtonERP.module.generalEntity;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.viewers.viewables.CheckListViewable;

/**
 * @author Guillaume Lacasse cette classe sert à faire des liste de flag qu'on
 *         peut attribuer à une entité lorsque ces flags sont dans une table
 *         étrangère passant par une table intermédiaire. Exemple: attribution
 *         de droits pour un groupe (ça passe par GroupsRight)
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
    private Vector<String> foreignDescriptionUiControls;

    /**
     * @param sourceEntityDefinition Definition de l'entité de source, exemple:
     *            groupe
     * @param visibleDescription Description visible
     * @param intermediateEntityDefinition Entité de table intermédiaire,
     *            exemple: GroupsRight
     * @param intermediateKeyIn Colonne d'entré de table intermédiaire, exemple:
     *            groupID
     * @param intermediateKeyOut Colonne de sortie de table intermédiaire,
     *            exemple: rightID
     * @param foreignEntityDefinition entité de table étrangère, exemple: Right
     * @param foreignKey clef d'identification de table étrangère, exemple:
     *            PKrightID
     * @param foreignDescriptionUiControls liste de colonne de description de
     *            table étrangère, exemple: Action, Module
     * @throws Exception si création fail
     */
    public FlagPool(AbstractOrmEntity sourceEntityDefinition,
	    String visibleDescription,
	    AbstractOrmEntity intermediateEntityDefinition,
	    String intermediateKeyIn, String intermediateKeyOut,
	    AbstractOrmEntity foreignEntityDefinition, String foreignKey,
	    Vector<String> foreignDescriptionUiControls) throws Exception
    {
	if (sourceEntityDefinition == null || foreignEntityDefinition == null)
	    throw new Exception("Source entity and target entity can't be null");

	this.sourceEntityDefinition = sourceEntityDefinition;
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
		    .getSystemName()
		    + "."
		    + intermediateKeyOut
		    + "."
		    + entity.getDataString(foreignKey));
	}

	return availableElementList;
    }

    private String getForeignDescription(AbstractOrmEntity entity)
	    throws Exception
    {
	String description = "";
	String currentValue;
	for (String key : foreignDescriptionUiControls)
	{
	    currentValue = entity.getDataString(key);

	    if (currentValue == null)
		currentValue = "null";

	    if (!currentValue.equals("null") || description.equals(""))
	    {
		ListOfValue listOfValue = entity.tryMatchListOfValue(key);

		if (listOfValue != null)
		    description += listOfValue.getElements().get(currentValue);
		else
		    description += currentValue;

		description += " ";
	    }
	    else
		description += " - ";
	}
	return description.trim();
    }

    @Override
    public HashSet<String> getCheckedElementList() throws Exception

    {
	HashSet<String> checkedElementList = new HashSet<String>();

	for (AbstractOrmEntity entity : getPositivePluralIntermediateAccessor())
	    checkedElementList.add(intermediateEntityDefinition.getSystemName()
		    + "." + intermediateKeyOut + "."
		    + entity.getDataString(intermediateKeyOut));

	return checkedElementList;
    }

    /**
     * @param currentSourceKeyName nom de clef
     * @param currentSourceKeyValue valeur de clef
     */
    public void query(String currentSourceKeyName, Integer currentSourceKeyValue)
    {
	sourceKeyName = currentSourceKeyName;
	sourceKeyValue = currentSourceKeyValue.toString();
    }

    /**
     * @return définition d'entité de table intermédiaire
     */
    public AbstractOrmEntity getIntermediateEntityDefinition()
    {
	return intermediateEntityDefinition;
    }

    /**
     * @return définition d'entité de table étrangère
     */
    public AbstractOrmEntity getForeignEntityDefinition()
    {
	return foreignEntityDefinition;
    }

    /**
     * @return clef d'entré dans table étrangère
     */
    public String getIntermediateKeyIn()
    {
	return intermediateKeyIn;
    }

    /**
     * @return clef de sortie dans table étrangère (cible)
     */
    public String getIntermediateKeyOut()
    {
	return intermediateKeyOut;
    }

    /**
     * @return valeur de clef primaire de table d'entité de table source
     */
    public String getSourceKeyValue()
    {
	return sourceKeyValue;
    }

    /**
     * @return définition d'entité source (entité de départ dans laquelle on met
     *         un flagPool)
     */
    public AbstractOrmEntity getSourceEntityDefinition()
    {
	return sourceEntityDefinition;
    }

    /**
     * @return retourne un accessor multiple
     * @throws Exception si obtention d'accessor multiple fail
     */
    public Vector<AbstractOrmEntity> getPositivePluralIntermediateAccessor()
	    throws Exception
    {
	// searchCriteriaEntity can be null

	if (sourceKeyName == null || sourceKeyValue == null)
	    throw new Exception(
		    "Vous devez préablablement faire une query(clef, valeur) avant d'intéroger les éléments du flag pool");

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

	Vector<AbstractOrmEntity> pluralAccessor = Orm
		.select(intermediateEntityDefinition);

	return pluralAccessor;
    }

    /**
     * @param searchCriteriaEntity critères de recherche facultatifs
     * @return accessor multiple vers entité étrangère
     * @throws Exception si obtention fail
     */
    public PluralAccessor getPositivePluralForeignAccessor(
	    AbstractOrmEntity searchCriteriaEntity) throws Exception
    {
	// searchCriteriaEntity can be null

	PluralAccessor pluralForeignAccessor = new PluralAccessor(
		foreignEntityDefinition);

	Vector<AbstractOrmEntity> resultSet;

	if (searchCriteriaEntity == null)
	{
	    Vector<AbstractOrmEntity> pluralIntermediateAccessor = getPositivePluralIntermediateAccessor();
	    for (AbstractOrmEntity intermediateEntity : pluralIntermediateAccessor)
	    {
		foreignEntityDefinition.setData(foreignKey, intermediateEntity
			.getData(intermediateKeyOut));

		resultSet = Orm.select(foreignEntityDefinition);

		for (AbstractOrmEntity result : resultSet)
		{
		    pluralForeignAccessor.add(result);
		}
	    }
	}
	else
	{
	    resultSet = Orm.select(searchCriteriaEntity);

	    AbstractOrmEntity criteredIntermediateEntityDefinition = intermediateEntityDefinition
		    .getClass().newInstance();
	    criteredIntermediateEntityDefinition.initFields();

	    for (AbstractOrmEntity result : resultSet)
	    {
		Vector<AbstractOrmEntity> intermediateResultSet;

		criteredIntermediateEntityDefinition.setData(
			intermediateKeyOut, result.getPrimaryKeyValue());
		criteredIntermediateEntityDefinition.setData(intermediateKeyIn,
			sourceEntityDefinition.getPrimaryKeyValue());

		intermediateResultSet = Orm
			.select(criteredIntermediateEntityDefinition);

		if (intermediateResultSet.size() > 0)
		    pluralForeignAccessor.add(result);
	    }
	}

	return pluralForeignAccessor;
    }
}
