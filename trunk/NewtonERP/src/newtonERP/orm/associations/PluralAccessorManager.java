package newtonERP.orm.associations;

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

		if (foreignEntity.getClass() != entity.getClass())
		    pluralAccessorList.put(foreignEntity.getNaturalKeyName(),
			    pluralAccessor);
	    }
	}
    }

    private static void addFromNegativeListOfValueList(
	    TreeMap<String, Vector<AbstractOrmEntity>> pluralAccessorList,
	    AbstractOrmEntity entity) throws Exception
    {
	ListOfValue listOfValue;
	AbstractOrmEntity foreignEntityDefinition;
	for (String listOfValueName : entity.getNegativeListOfValueList()
		.keySet())
	{
	    listOfValue = entity.getNegativeListOfValueList().get(
		    listOfValueName);

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

    private static Vector<AbstractOrmEntity> getPositivePluralAccessor(
	    AbstractOrmEntity entity, FlagPool flagPool) throws Exception
    {
	flagPool.query(entity.getPrimaryKeyName(), entity.getPrimaryKeyValue());

	return flagPool.getPositivePluralForeignAccessor();
    }
}
