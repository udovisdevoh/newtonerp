package newtonERP.orm.associations; 
 // TODO: clean up that file

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
 * 
 * @author Guillaume
 */
public class PluralAccessorManager {

	/**
	 * @param entity entité source
	 * @return liste des accessors 1 à 1 et plusieurs à 1 pour l'entité source
	 */
	public static final TreeMap<String, PluralAccessor> getPluralAccessorList(AbstractOrmEntity entity) {
		TreeMap<String, PluralAccessor> pluralAccessorList = new TreeMap<String, PluralAccessor>();

		addFromPositiveFlagPoolList(pluralAccessorList, entity);
		addFromNegativeListOfValueList(pluralAccessorList, entity);
		addFromNegativeFlagPoolList(pluralAccessorList, entity);

		return pluralAccessorList;
	}

	/**
	 * @param entity définition d'entité
	 * @param accessorName nom de l'accesseur
	 * @param searchCriteriaEntity critère de recherche (facultatif, peut être null)
	 * @return accesseur multiple
	 */
	public static PluralAccessor getPluralAccessor(AbstractOrmEntity entity, String accessorName,
	        AbstractOrmEntity searchCriteriaEntity)

	{
		PluralAccessor pluralAccessor;

		TreeMap<String, PluralAccessor> pluralAccessorList = new TreeMap<String, PluralAccessor>();

		pluralAccessor = tryGetAccessorFromPositiveFlagPool(entity, accessorName, searchCriteriaEntity);

		if(pluralAccessor != null){
			return pluralAccessor;
		}

		addFromNegativeListOfValueList(pluralAccessorList, entity);

		if(pluralAccessorList.containsKey(accessorName)){
			return pluralAccessorList.get(accessorName);
		}

		addFromNegativeFlagPoolList(pluralAccessorList, entity);

		if(pluralAccessorList.containsKey(accessorName)){
			return pluralAccessorList.get(accessorName);
		}

		String availableAccessorList = "";

		for(String currentAccessorName : pluralAccessorList.keySet()){
			availableAccessorList += currentAccessorName + " ";
		}

		availableAccessorList = availableAccessorList.trim();

		if(pluralAccessorList.size() > 0){
			throw new RuntimeException(
			        "Accesseur introuvable. Veuillez utiliser un des accesseurs suivant dans ce contexte: "
			                + availableAccessorList);
		}
		throw new RuntimeException("Aucun accesseur disponible dans ce contexte");
	}

	/**
	 * @param entity entité source
	 * @param accessorName nom de l'accesseur
	 * @return accesseur multiple
	 */
	public static final PluralAccessor getPluralAccessor(AbstractOrmEntity entity, String accessorName) {
		return getPluralAccessor(entity, accessorName, null);
	}

	private static void addFromNegativeFlagPoolList(TreeMap<String, PluralAccessor> pluralAccessorList,
	        AbstractOrmEntity entity) {
		PluralAccessor intermediatePluralAccessor;
		Vector<AbstractOrmEntity> resultSet;
		PluralAccessor pluralAccessor;
		AbstractOrmEntity intermediateEntityDefinition;
		FlagPool flagPool;

		for(String flagPoolName : entity.getNegativeFlagPoolList().keySet()){
			flagPool = entity.getNegativeFlagPoolList().get(flagPoolName);

			if(entity.getClass() != flagPool.getForeignEntityDefinition().getClass()){
				continue;
			}

			// On skip la gestion des flag pool pas concernés
			if(!flagPool.getIntermediateEntityDefinition().getFields().containsFieldName(entity.getForeignKeyName())){
				continue;
			}

			try{
				intermediateEntityDefinition = flagPool.getIntermediateEntityDefinition().getClass().newInstance();
			}catch(InstantiationException e){
				throw new RuntimeException(e);
			}catch(IllegalAccessException e){
				throw new RuntimeException(e);
			}
			intermediateEntityDefinition.initFields();

			intermediateEntityDefinition.setData(entity.getForeignKeyName(), entity.getPrimaryKeyValue());

			intermediatePluralAccessor = new PluralAccessor(intermediateEntityDefinition, Orm.getInstance().select(
			        intermediateEntityDefinition));

			pluralAccessor = null;
			for(AbstractOrmEntity intermediateEntity : intermediatePluralAccessor){
				String keyOut = flagPool.getIntermediateKeyIn();
				AbstractOrmEntity foreignEntity = flagPool.getSourceEntityDefinition();
				try{
					foreignEntity = foreignEntity.getClass().newInstance();
				}catch(InstantiationException e){
					throw new RuntimeException(e);
				}catch(IllegalAccessException e){
					throw new RuntimeException(e);
				}
				foreignEntity.initFields();
				foreignEntity.setData(foreignEntity.getPrimaryKeyName(), intermediateEntity.getData(keyOut));

				resultSet = Orm.getInstance().select(foreignEntity);

				if(pluralAccessor == null){
					pluralAccessor = new PluralAccessor(foreignEntity);
				}

				if(resultSet.size() > 0){
					pluralAccessor.addAll(resultSet);
				}
			}

			if(flagPool.getSourceEntityDefinition().getClass() != entity.getClass()){
				pluralAccessorList.put(flagPool.getSourceEntityDefinition().getNaturalKeyName(), pluralAccessor);
			}
		}
	}

	private static void addFromNegativeListOfValueList(TreeMap<String, PluralAccessor> pluralAccessorList,
	        AbstractOrmEntity entity) {
		// searchCriteriaEntity can be null

		AbstractOrmEntity foreignEntityDefinition;

		for(ListOfValue listOfValue : new HashSet<ListOfValue>(entity.getNegativeListOfValueList())){
			if(!entity.getSystemName().equals(listOfValue.getForeignEntityDefinition().getSystemName())){
				continue;
			}

			try{
				foreignEntityDefinition = listOfValue.getSourceEntityDefinition().getClass().newInstance();
			}catch(InstantiationException e){
				throw new RuntimeException(e);
			}catch(IllegalAccessException e){
				throw new RuntimeException(e);
			}
			foreignEntityDefinition.initFields();

			if(!foreignEntityDefinition.getFields().containsFieldName(entity.getForeignKeyName())){
				continue;
			}

			foreignEntityDefinition.setData(entity.getForeignKeyName(), entity.getPrimaryKeyValue());

			Vector<AbstractOrmEntity> resultSet = Orm.getInstance().select(foreignEntityDefinition);

			pluralAccessorList.put(foreignEntityDefinition.getSystemName(), new PluralAccessor(foreignEntityDefinition,
			        resultSet));
		}
	}

	private static void addFromPositiveFlagPoolList(TreeMap<String, PluralAccessor> pluralAccessorList,
	        AbstractOrmEntity entity) {
		PluralAccessor pluralAccessor;
		FlagPool flagPool;

		for(String flagPoolName : entity.getPositiveFlagPoolList().keySet()){
			flagPool = entity.getPositiveFlagPoolList().get(flagPoolName);
			pluralAccessor = getPositivePluralAccessor(entity, flagPool, null);

			if(pluralAccessor != null){
				pluralAccessorList.put(flagPool.getForeignEntityDefinition().getSystemName(), pluralAccessor);
			}
		}
	}

	private static PluralAccessor tryGetAccessorFromPositiveFlagPool(AbstractOrmEntity entity, String accessorName,
	        AbstractOrmEntity searchCriteriaEntity) {
		// searchCriteriaEntity can be null

		accessorName = accessorName.toLowerCase();
		PluralAccessor pluralAccessor;

		Hashtable<String, FlagPool> positiveFlagPoolList = entity.getPositiveFlagPoolList();

		FlagPool selectedFlagPool = null;

		for(FlagPool currentFlagPool : positiveFlagPoolList.values()){
			if(currentFlagPool.getForeignEntityDefinition().getSystemName().toLowerCase().equals(accessorName)){
				selectedFlagPool = currentFlagPool;
				break;
			}else if(currentFlagPool.getForeignEntityDefinition().getPrimaryKeyName().toLowerCase()
			        .equals(accessorName)){
				selectedFlagPool = currentFlagPool;
				break;
			}else if(currentFlagPool.getForeignEntityDefinition().getForeignKeyName().toLowerCase()
			        .equals(accessorName)){
				selectedFlagPool = currentFlagPool;
				break;
			}
		}

		if(selectedFlagPool == null){
			selectedFlagPool = positiveFlagPoolList.get(accessorName);
		}

		if(selectedFlagPool == null){
			return null;
		}

		pluralAccessor = getPositivePluralAccessor(entity, selectedFlagPool, searchCriteriaEntity);

		return pluralAccessor;
	}

	private static PluralAccessor getPositivePluralAccessor(AbstractOrmEntity entity, FlagPool flagPool,
	        AbstractOrmEntity searchCriteriaEntity) {
		// searchCriteriaEntity can be null

		flagPool.query(entity.getPrimaryKeyName(), entity.getPrimaryKeyValue());

		return flagPool.getPositivePluralForeignAccessor(searchCriteriaEntity);
	}
}
