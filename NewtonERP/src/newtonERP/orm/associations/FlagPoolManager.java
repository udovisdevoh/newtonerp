package newtonERP.orm.associations;

import java.util.Collection;
import java.util.Hashtable;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.FlagPool;
import newtonERP.orm.Orm;

/**
 * Cette classe gère les comportement d'association d'entités faites par le
 * FlagPool
 * @author Guillaume
 */
public class FlagPoolManager
{
    /**
     * @param flagPoolList liste de FlagPool
     * @param parameters paramêtres (mêmes type que dans doAction
     * @throws Exception
     */
    public static void applyFlagPoolChanges(AbstractOrmEntity sourceEntity,
	    Iterable<FlagPool> flagPoolList,
	    Hashtable<String, String> parameters) throws Exception
    {
	for (FlagPool flagPool : flagPoolList)
	    applyFlagPoolChanges(sourceEntity, flagPool, parameters);
    }

    private static void applyFlagPoolChanges(AbstractOrmEntity sourceEntity,
	    FlagPool flagPool, Hashtable<String, String> parameters)
	    throws Exception
    {
	flagPool.query(sourceEntity.getPrimaryKeyName(), sourceEntity
		.getPrimaryKeyValue());

	Collection<String> availableElementList = flagPool
		.getAvailableElementList().values();

	for (String availableElement : availableElementList)
	{

	    AbstractOrmEntity searchEntity = getSearchEntity(sourceEntity,
		    flagPool, availableElement);

	    if (parameters.containsKey(availableElement))
	    {
		Orm.insertUnique(searchEntity);
	    }
	    else
	    {
		Orm.delete(searchEntity);
	    }

	}

	flagPool.query(sourceEntity.getPrimaryKeyName(), sourceEntity
		.getPrimaryKeyValue());

    }

    private static AbstractOrmEntity getSearchEntity(
	    AbstractOrmEntity sourceEntity, FlagPool flagPool,
	    String elementName) throws Exception
    {
	if (elementName.contains("."))
	{
	    elementName = elementName.substring(
		    elementName.lastIndexOf('.') + 1).trim();
	}

	int keyIn = sourceEntity.getPrimaryKeyValue();
	int keyOut = Integer.parseInt(elementName);

	String intermediateKeyInName = flagPool.getIntermediateKeyIn();
	String intermediateKeyOutName = flagPool.getIntermediateKeyOut();

	AbstractOrmEntity searchEntity = flagPool
		.getIntermediateEntityDefinition().getClass().newInstance();
	searchEntity.setData(intermediateKeyInName, keyIn);
	searchEntity.setData(intermediateKeyOutName, keyOut);
	return searchEntity;
    }

    /**
     * @param sourceEntityDefinition Definition de l'entité de source, exemple:
     *            groupe
     * @param visibleDescription Description visible du flag pool
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
     */
    public static void addFlagPool(AbstractOrmEntity sourceEntity,
	    String visibleDescription,
	    AbstractOrmEntity intermediateEntityDefinition,
	    String intermediateKeyIn, String intermediateKeyOut,
	    AbstractOrmEntity foreignEntityDefinition, String foreignKey,
	    String[] foreignDescriptionUiControls)
    {
	FlagPool flagPool = new FlagPool(sourceEntity, visibleDescription,
		intermediateEntityDefinition, intermediateKeyIn,
		intermediateKeyOut, foreignEntityDefinition, foreignKey,
		foreignDescriptionUiControls);

	if (sourceEntity.getFlagPoolList() == null)
	    sourceEntity.setFlagPoolList(new Hashtable<String, FlagPool>());

	sourceEntity.getFlagPoolList().put(visibleDescription, flagPool);
    }
}
