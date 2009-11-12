package newtonERP.orm.associations;

import java.util.TreeMap;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.ListOfValue;
import newtonERP.orm.Orm;

/**
 * Cette classe s'occupe de la gestion des accessor 1 à 1 ou plusieurs à 1
 * @author Guillaume
 */
public class SingleAccessorManager
{
    /**
     * @param entity entité source
     * @return liste des accessors plusieurs à plusieurs pour l'entité source
     * @throws Exception exceptions si obtention fail
     */
    public static final TreeMap<String, AbstractOrmEntity> getSingleAccessorList(
	    AbstractOrmEntity entity) throws Exception
    {
	TreeMap<String, AbstractOrmEntity> singleAccessorList = new TreeMap<String, AbstractOrmEntity>();

	AbstractOrmEntity foreignEntityDefinition, realForeignEntity;

	ListOfValue listOfValue;

	for (String listOfValueName : entity.getPositiveListOfValueList()
		.keySet())
	{
	    listOfValue = entity.getPositiveListOfValueList().get(
		    listOfValueName);
	    foreignEntityDefinition = listOfValue.getForeignEntityDefinition();

	    realForeignEntity = getForeignEntity(entity,
		    foreignEntityDefinition);

	    if (realForeignEntity != null)
		singleAccessorList.put(realForeignEntity.getSystemName(),
			realForeignEntity);
	}

	return singleAccessorList;
    }

    private static AbstractOrmEntity getForeignEntity(
	    AbstractOrmEntity sourceEntity,
	    AbstractOrmEntity foreignEntityDefinition) throws Exception
    {
	foreignEntityDefinition.setData(foreignEntityDefinition
		.getPrimaryKeyName(), sourceEntity
		.getData(foreignEntityDefinition.getForeignKeyName()));

	Vector<AbstractOrmEntity> resultSet = Orm
		.select(foreignEntityDefinition);

	if (resultSet.size() > 0)
	    return resultSet.get(0);

	return null;
    }

    /**
     * @param abstractOrmEntity entité
     * @param listOfValueName nom de l'accessor
     * @return accessor singulier
     * @throws Exception si obtention fail
     */
    public static AbstractOrmEntity getSingleAccessor(
	    AbstractOrmEntity abstractOrmEntity, String listOfValueName)
	    throws Exception
    {
	AbstractOrmEntity foreignEntityDefinition, realForeignEntity;

	ListOfValue listOfValue = abstractOrmEntity
		.tryMatchListOfValue(listOfValueName);

	if (listOfValue != null)
	{
	    listOfValue = abstractOrmEntity.getPositiveListOfValueList().get(
		    listOfValueName);
	    foreignEntityDefinition = listOfValue.getForeignEntityDefinition();

	    realForeignEntity = getForeignEntity(abstractOrmEntity,
		    foreignEntityDefinition);

	    return realForeignEntity;
	}
	return null;
    }
}
