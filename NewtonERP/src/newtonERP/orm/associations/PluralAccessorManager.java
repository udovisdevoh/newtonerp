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

	addFromFlagPoolList(pluralAccessorList, entity);
	addFromNegativeListOfValueList(pluralAccessorList, entity);

	return pluralAccessorList;
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

	    foreignEntityDefinition = listOfValue.getSourceEntityDefinition();
	    foreignEntityDefinition.initFields();

	    try
	    {
		foreignEntityDefinition.setData(entity.getForeignKeyName(),
			entity.getPrimaryKeyValue());

		Vector<AbstractOrmEntity> resultSet = Orm
			.select(foreignEntityDefinition);

		if (resultSet.size() > 0)
		    pluralAccessorList.put(foreignEntityDefinition.getClass()
			    .getSimpleName(), resultSet);
	    } catch (Exception e)
	    {
		// Catch vide car il faut que ça skip les list of values pas
		// concernées
	    }
	}
    }

    private static void addFromFlagPoolList(
	    TreeMap<String, Vector<AbstractOrmEntity>> pluralAccessorList,
	    AbstractOrmEntity entity) throws Exception
    {
	Vector<AbstractOrmEntity> pluralAccessor;
	FlagPool flagPool;

	for (String flagPoolName : entity.getFlagPoolList().keySet())
	{
	    flagPool = entity.getFlagPoolList().get(flagPoolName);
	    pluralAccessor = getPluralAccessor(entity, flagPool);

	    if (pluralAccessor != null)
		pluralAccessorList.put(flagPool.getForeignEntityDefinition()
			.getClass().getSimpleName(), pluralAccessor);
	}
    }

    private static Vector<AbstractOrmEntity> getPluralAccessor(
	    AbstractOrmEntity entity, FlagPool flagPool) throws Exception
    {
	flagPool.query(entity.getPrimaryKeyName(), entity.getPrimaryKeyValue());

	return flagPool.getPluralForeignAccessor();
    }
}
