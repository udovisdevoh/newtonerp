package newtonERP.orm.associations; 
 // TODO: clean up that file

import java.util.TreeMap;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.ListOfValue;
import newtonERP.orm.Orm;

/**
 * Cette classe s'occupe de la gestion des accessor 1 à 1 ou plusieurs à 1
 * 
 * @author Guillaume
 */
public class SingleAccessorManager {
	/**
	 * @param entity entité source
	 * @return liste des accessors plusieurs à plusieurs pour l'entité source
	 */
	public static final TreeMap<String, AbstractOrmEntity> getSingleAccessorList(AbstractOrmEntity entity) {
		TreeMap<String, AbstractOrmEntity> singleAccessorList = new TreeMap<String, AbstractOrmEntity>();

		AbstractOrmEntity foreignEntityDefinition, realForeignEntity;

		ListOfValue listOfValue;

		for(String listOfValueName : entity.getPositiveListOfValueList().keySet()){
			listOfValue = entity.getPositiveListOfValueList().get(listOfValueName);
			foreignEntityDefinition = listOfValue.getForeignEntityDefinition();

			realForeignEntity = getForeignEntity(entity, foreignEntityDefinition);

			if(realForeignEntity != null){
				singleAccessorList.put(realForeignEntity.getSystemName(), realForeignEntity);
			}
		}

		return singleAccessorList;
	}

	private static AbstractOrmEntity getForeignEntity(AbstractOrmEntity sourceEntity,
	        AbstractOrmEntity foreignEntityDefinition) {
		foreignEntityDefinition.setData(foreignEntityDefinition.getPrimaryKeyName(),
		        sourceEntity.getData(foreignEntityDefinition.getForeignKeyName()));

		Vector<AbstractOrmEntity> resultSet = Orm.getInstance().select(foreignEntityDefinition);

		if(resultSet.size() > 0){
			return resultSet.get(0);
		}

		return null;
	}

	/**
	 * @param abstractOrmEntity entité
	 * @param listOfValueName nom de l'accessor
	 * @return accessor singulier
	 */
	public static AbstractOrmEntity getSingleAccessor(AbstractOrmEntity abstractOrmEntity, String listOfValueName)

	{
		AbstractOrmEntity foreignEntityDefinition, realForeignEntity;

		ListOfValue listOfValue = abstractOrmEntity.tryMatchListOfValue(listOfValueName);

		if(listOfValue != null){
			listOfValue = abstractOrmEntity.getPositiveListOfValueList().get(listOfValueName);
			foreignEntityDefinition = listOfValue.getForeignEntityDefinition();

			realForeignEntity = getForeignEntity(abstractOrmEntity, foreignEntityDefinition);

			return realForeignEntity;
		}
		return null;
	}
}
