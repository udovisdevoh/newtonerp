package newtonERP.orm.associations; 
 // TODO: clean up that file

import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import newtonERP.common.ModuleLoader;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.FlagPool;
import newtonERP.orm.Orm;

/**
 * Cette classe gère les comportement d'association d'entités faites par le FlagPool
 * 
 * @author Guillaume
 */
public class FlagPoolManager {
	/**
	 * @param sourceEntity the source entity
	 * @param flagPoolList liste de FlagPool
	 * @param parameters paramêtres (mêmes type que dans doAction
	 */
	public static void applyFlagPoolChanges(AbstractOrmEntity sourceEntity, Iterable<FlagPool> flagPoolList,
	        Hashtable<String, String> parameters) {
		for(FlagPool flagPool : flagPoolList){
			applyFlagPoolChanges(sourceEntity, flagPool, parameters);
		}
	}

	private static void applyFlagPoolChanges(AbstractOrmEntity sourceEntity, FlagPool flagPool,
	        Hashtable<String, String> parameters)

	{
		flagPool.query(sourceEntity.getPrimaryKeyName(), sourceEntity.getPrimaryKeyValue());

		Collection<String> availableElementList = flagPool.getAvailableElementList().values();

		for(String availableElement : availableElementList){

			AbstractOrmEntity searchEntity = getSearchEntity(sourceEntity, flagPool, availableElement);

			if(parameters.containsKey(availableElement)){
				Orm.getInstance().insertUnique(searchEntity);
			}else{
				Orm.getInstance().delete(searchEntity);
			}

		}

		flagPool.query(sourceEntity.getPrimaryKeyName(), sourceEntity.getPrimaryKeyValue());

	}

	private static AbstractOrmEntity getSearchEntity(AbstractOrmEntity sourceEntity, FlagPool flagPool,
	        String elementName) {
		if(elementName.contains(".")){
			elementName = elementName.substring(elementName.lastIndexOf('.') + 1).trim();
		}

		int keyIn = sourceEntity.getPrimaryKeyValue();
		int keyOut = Integer.parseInt(elementName);

		String intermediateKeyInName = flagPool.getIntermediateKeyIn();
		String intermediateKeyOutName = flagPool.getIntermediateKeyOut();

		AbstractOrmEntity searchEntity;
		try{
			searchEntity = flagPool.getIntermediateEntityDefinition().getClass().newInstance();
		}catch(InstantiationException e){
			throw new RuntimeException(e);
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}
		searchEntity.setData(intermediateKeyInName, keyIn);
		searchEntity.setData(intermediateKeyOutName, keyOut);
		return searchEntity;
	}

	/**
	 * @param sourceEntity the source entity
	 * @param foreignEntityDefinition entity of the foreign entity
	 */
	public static final void addFlagPool(AbstractOrmEntity sourceEntity, AbstractOrmEntity foreignEntityDefinition) {

		AbstractOrmEntity intermediateEntityDefinition = buildIntermediateEntityDefinition(sourceEntity,
		        foreignEntityDefinition);

		String intermediateKeyIn = sourceEntity.getForeignKeyName();
		String intermediateKeyOut = foreignEntityDefinition.getForeignKeyName();

		addFlagPool(sourceEntity, foreignEntityDefinition.getNaturalKeyName(), intermediateEntityDefinition,
		        intermediateKeyIn, intermediateKeyOut, foreignEntityDefinition,
		        foreignEntityDefinition.getPrimaryKeyName(), foreignEntityDefinition.getNaturalKeyNameList());
	}

	private static AbstractOrmEntity buildIntermediateEntityDefinition(AbstractOrmEntity entity1,
	        AbstractOrmEntity entity2)

	{
		String entityName1, entityName2, intermediateEntityName;

		entityName1 = entity1.getSystemName();
		entityName2 = entity2.getSystemName();

		if(entityName1.compareTo(entityName2) < 0){
			intermediateEntityName = entityName1 + entityName2;
		}else{
			intermediateEntityName = entityName2 + entityName1;
		}

		String classPath = entity1.getClass().getName();
		classPath = classPath.substring(0, classPath.lastIndexOf('.'));

		intermediateEntityName = classPath + "." + intermediateEntityName;

		AbstractOrmEntity intermediateEntityDefinition;
		try{
			intermediateEntityDefinition = (AbstractOrmEntity) ModuleLoader.loadClass(intermediateEntityName)
			        .newInstance();
		}catch(InstantiationException e){
			throw new RuntimeException(e);
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}

		intermediateEntityDefinition.initFields();

		return intermediateEntityDefinition;
	}

	/**
	 * @param sourceEntityDefinition Definition de l'entité de source, exemple: groupe
	 * @param visibleDescription Description visible du flag pool
	 * @param intermediateEntityDefinition Entité de table intermédiaire, exemple: GroupsRight
	 * @param intermediateKeyIn Colonne d'entré de table intermédiaire, exemple: groupID
	 * @param intermediateKeyOut Colonne de sortie de table intermédiaire, exemple: rightID
	 * @param foreignEntityDefinition entité de table étrangère, exemple: Right
	 * @param foreignKey clef d'identification de table étrangère, exemple: PKrightID
	 * @param foreignDescriptionUiControls liste de colonne de description de table étrangère, exemple: Action, Module
	 */
	private static void addFlagPool(AbstractOrmEntity sourceEntity, String visibleDescription,
	        AbstractOrmEntity intermediateEntityDefinition, String intermediateKeyIn, String intermediateKeyOut,
	        AbstractOrmEntity foreignEntityDefinition, String foreignKey, Vector<String> foreignDescriptionUiControls) {
		FlagPool flagPool = new FlagPool(sourceEntity, visibleDescription, intermediateEntityDefinition,
		        intermediateKeyIn, intermediateKeyOut, foreignEntityDefinition, foreignKey,
		        foreignDescriptionUiControls);

		sourceEntity.addPositiveFlagPool(visibleDescription, flagPool);
		foreignEntityDefinition.addNegativeFlagPool(visibleDescription, flagPool);
	}
}
