package newtonERP.orm.associations;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.FlagPool;
import newtonERP.module.generalEntity.ListOfValue;
import newtonERP.orm.Orm;

/**
 * Cette classe s'occupe de la gestion des accessor plusieurs à plusieurs
 * @author Guillaume
 */
public class PluralAccessorManager
{

    /**
     * @param entity entité source
     * @return liste des accessors 1 à 1 et plusieurs à 1 pour l'entité source
     * @throws Exception si obtention de l'accessor multiple fail
     */
    public static final TreeMap<String, Vector<AbstractOrmEntity>> getPluralAccessorList(
	    AbstractOrmEntity entity) throws Exception
    {
	TreeMap<String, Vector<AbstractOrmEntity>> pluralAccessorList = new TreeMap<String, Vector<AbstractOrmEntity>>();

	addFromPositiveFlagPoolList(pluralAccessorList, entity);
	addFromNegativeListOfValueList(pluralAccessorList, entity);
	addFromNegativeFlagPoolList(pluralAccessorList, entity);

	return pluralAccessorList;
    }

    /**
     * @param entity entité source
     * @param accessorName nom de l'accesseur
     * @return accesseur multiple
     * @throws Exception si obtention fail
     */
    public static final Vector<AbstractOrmEntity> getPluralAccessor(
	    AbstractOrmEntity entity, String accessorName) throws Exception
    {
	Vector<AbstractOrmEntity> pluralAccessor;

	TreeMap<String, Vector<AbstractOrmEntity>> pluralAccessorList = new TreeMap<String, Vector<AbstractOrmEntity>>();

	pluralAccessor = tryGetAccessorFromPositiveFlagPool(entity,
		accessorName);

	if (pluralAccessor != null)
	    return pluralAccessor;

	addFromNegativeListOfValueList(pluralAccessorList, entity);

	if (pluralAccessorList.containsKey(accessorName))
	    return pluralAccessorList.get(accessorName);

	addFromNegativeFlagPoolList(pluralAccessorList, entity);

	if (pluralAccessorList.containsKey(accessorName))
	    return pluralAccessorList.get(accessorName);

	String availableAccessorList = "";

	for (String currentAccessorName : pluralAccessorList.keySet())
	    availableAccessorList += currentAccessorName + " ";

	availableAccessorList = availableAccessorList.trim();

	if (pluralAccessorList.size() > 0)
	    throw new Exception(
		    "Accesseur introuvable. Veuillez utiliser un des accesseurs suivant dans ce contexte: "
			    + availableAccessorList);
	throw new Exception("Aucun accesseur disponible dans ce contexte");
    }

    private static void addFromNegativeFlagPoolList(
	    TreeMap<String, Vector<AbstractOrmEntity>> pluralAccessorList,
	    AbstractOrmEntity entity) throws Exception
    {
	Vector<AbstractOrmEntity> intermediatePluralAccessor;
	Vector<AbstractOrmEntity> resultSet;
	Vector<AbstractOrmEntity> pluralAccessor;
	AbstractOrmEntity intermediateEntityDefinition;
	FlagPool flagPool;

	for (String flagPoolName : entity.getNegativeFlagPoolList().keySet())
	{
	    flagPool = entity.getNegativeFlagPoolList().get(flagPoolName);

	    if (entity.getClass() != flagPool.getForeignEntityDefinition()
		    .getClass())
		continue;

	    // On skip la gestion des flag pool pas concernés
	    if (!flagPool.getIntermediateEntityDefinition().getFields()
		    .containsFieldName(entity.getForeignKeyName()))
		continue;

	    intermediateEntityDefinition = flagPool
		    .getIntermediateEntityDefinition().getClass().newInstance();
	    intermediateEntityDefinition.initFields();

	    intermediateEntityDefinition.setData(entity.getForeignKeyName(),
		    entity.getPrimaryKeyValue());

	    intermediatePluralAccessor = Orm
		    .select(intermediateEntityDefinition);

	    pluralAccessor = new Vector<AbstractOrmEntity>();
	    for (AbstractOrmEntity intermediateEntity : intermediatePluralAccessor)
	    {
		String keyOut = flagPool.getIntermediateKeyIn();
		AbstractOrmEntity foreignEntity = flagPool
			.getSourceEntityDefinition();
		foreignEntity = foreignEntity.getClass().newInstance();
		foreignEntity.initFields();
		foreignEntity.setData(foreignEntity.getPrimaryKeyName(),
			intermediateEntity.getData(keyOut));

		resultSet = Orm.select(foreignEntity);

		if (resultSet.size() > 0)
		    pluralAccessor.addAll(resultSet);
	    }

	    if (flagPool.getSourceEntityDefinition().getClass() != entity
		    .getClass())
		pluralAccessorList.put(flagPool.getSourceEntityDefinition()
			.getNaturalKeyName(), pluralAccessor);
	}
    }

    private static void addFromNegativeListOfValueList(
	    TreeMap<String, Vector<AbstractOrmEntity>> pluralAccessorList,
	    AbstractOrmEntity entity) throws Exception
    {
	AbstractOrmEntity foreignEntityDefinition;

	for (ListOfValue listOfValue : new HashSet<ListOfValue>(entity
		.getNegativeListOfValueList()))
	{
	    if (entity.getClass() != listOfValue.getForeignEntityDefinition()
		    .getClass())
		continue;

	    foreignEntityDefinition = listOfValue.getSourceEntityDefinition();
	    foreignEntityDefinition.initFields();

	    if (!foreignEntityDefinition.getFields().containsFieldName(
		    entity.getForeignKeyName()))
		continue;

	    foreignEntityDefinition.setData(entity.getForeignKeyName(), entity
		    .getPrimaryKeyValue());

	    Vector<AbstractOrmEntity> resultSet = Orm
		    .select(foreignEntityDefinition);

	    pluralAccessorList.put(foreignEntityDefinition.getClass()
		    .getSimpleName(), resultSet);
	}
    }

    private static void addFromPositiveFlagPoolList(
	    TreeMap<String, Vector<AbstractOrmEntity>> pluralAccessorList,
	    AbstractOrmEntity entity) throws Exception
    {
	Vector<AbstractOrmEntity> pluralAccessor;
	FlagPool flagPool;

	for (String flagPoolName : entity.getPositiveFlagPoolList().keySet())
	{
	    flagPool = entity.getPositiveFlagPoolList().get(flagPoolName);
	    pluralAccessor = getPositivePluralAccessor(entity, flagPool);

	    if (pluralAccessor != null)
		pluralAccessorList.put(flagPool.getForeignEntityDefinition()
			.getClass().getSimpleName(), pluralAccessor);
	}
    }

    private static Vector<AbstractOrmEntity> tryGetAccessorFromPositiveFlagPool(
	    AbstractOrmEntity entity, String accessorName) throws Exception
    {
	accessorName = accessorName.toLowerCase();
	Vector<AbstractOrmEntity> pluralAccessor;

	Hashtable<String, FlagPool> positiveFlagPoolList = entity
		.getPositiveFlagPoolList();

	FlagPool selectedFlagPool = null;

	for (FlagPool currentFlagPool : positiveFlagPoolList.values())
	{
	    if (currentFlagPool.getForeignEntityDefinition().getClass()
		    .getSimpleName().toLowerCase().equals(accessorName))
	    {
		selectedFlagPool = currentFlagPool;
		break;
	    }
	    else if (currentFlagPool.getForeignEntityDefinition()
		    .getPrimaryKeyName().toLowerCase().equals(accessorName))
	    {
		selectedFlagPool = currentFlagPool;
		break;
	    }
	    else if (currentFlagPool.getForeignEntityDefinition()
		    .getForeignKeyName().toLowerCase().equals(accessorName))
	    {
		selectedFlagPool = currentFlagPool;
		break;
	    }
	}

	if (selectedFlagPool == null)
	    selectedFlagPool = positiveFlagPoolList.get(accessorName);

	if (selectedFlagPool == null)
	    return null;

	pluralAccessor = getPositivePluralAccessor(entity, selectedFlagPool);

	return pluralAccessor;
    }

    private static Vector<AbstractOrmEntity> getPositivePluralAccessor(
	    AbstractOrmEntity entity, FlagPool flagPool) throws Exception
    {
	flagPool.query(entity.getPrimaryKeyName(), entity.getPrimaryKeyValue());

	return flagPool.getPositivePluralForeignAccessor();
    }
}
