package newtonERP.orm;

import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.orm.field.Field;
import newtonERP.orm.sgbd.AbstractSgbd;
import newtonERP.orm.sgbd.sqlite.SgbdSqlite;
import newtonERP.serveur.ConfigManager;

/**
 * Basic class for the orm. It is used to put the objects in the databse using a sgdb and its java binding. The orm will
 * receive an entity from which the orm will perform various tasks such as generating the query and executing it
 * obviously. Then it's gonna send the query to the SgbdSqlite class to execute it.
 * http://www.sqlite.org/lang_keywords.html TODO: Drop a module (maybe a an array of the entities to drop?)
 * 
 * @author r3hallejo, r3lacasgu, r3cloutjo
 */
public final class Orm {

	private static Hashtable<String, Orm> instances;
	private AbstractSgbd sgdb;

	/**
	 * Instantiates a new orm.
	 * 
	 * @param databaseName the database name
	 */
	private Orm(String databaseName) {
		sgdb = buildSGDB(databaseName);
		sgdb.connect();
		// sgdb.createNonExistentTables();
	}

	/**
	 * Gets the sgbd.
	 * 
	 * @param databaseName the data base name (need to be set in config file)
	 * @return abstractSgdb that was asked
	 */
	private AbstractSgbd buildSGDB(String databaseName) {
		if(ConfigManager.loadStringProperty("dmbs-name").equals("sqlite")){
			return new SgbdSqlite();// On crée la référence
		}
		throw new OrmException("Invalid DBMS type");
	}

	/**
	 * Gets the single instance of Orm.
	 * 
	 * @return single instance of Orm
	 */
	@Deprecated
	public static Orm getInstance() {
		return getInstance("test");
	}

	/**
	 * Gets the single instance of Orm representing the database asked.
	 * 
	 * @param databaseName the database name
	 * @return single instance of Orm asked
	 */
	public static Orm getInstance(String databaseName) {
		if(instances == null){
			instances = new Hashtable<String, Orm>();
		}
		Orm database = instances.get(databaseName);
		if(database != null){
			return database;
		}
		database = new Orm(databaseName);
		instances.put(databaseName, database);
		database.sgdb.createNonExistentTables();
		return database;
	}

	/**
	 * Used to disconnect from the db
	 */
	public void disconnect() {
		sgdb.disconnect();
		for(Entry<String, Orm> instance : instances.entrySet()){
			if(instance.getValue().equals(this)){
				instances.remove(instance.getKey());
				break;
			}
		}
	}

	/**
	 * @param entityAsType entity as a type reference or empty entity to fill data from orm
	 * @param fieldName key
	 * @param fieldValue value
	 * @return new entity or found entity
	 */
	@Deprecated
	public AbstractOrmEntity getOrCreateEntity(AbstractOrmEntity entityAsType, String fieldName, String fieldValue) {
		entityAsType.setData(fieldName, fieldValue);

		Vector<AbstractOrmEntity> entityList = entityAsType.get();

		if(entityList.size() > 0){
			return entityList.get(0);
		}

		entityAsType.newE();
		return entityAsType;
	}

	/**
	 * @param entityAsType entity as a type reference or empty entity to fill
	 * @param fieldName1 key1
	 * @param fieldValue1 value2
	 * @param fieldName2 key2
	 * @param fieldValue2 value2
	 * @return new entity or found entity
	 */
	@Deprecated
	public AbstractOrmEntity getOrCreateEntity(AbstractOrmEntity entityAsType, String fieldName1, String fieldValue1,
	        String fieldName2, String fieldValue2) {
		entityAsType.setData(fieldName1, fieldValue1);
		entityAsType.setData(fieldName2, fieldValue2);

		Vector<AbstractOrmEntity> entityList = entityAsType.get();

		if(entityList.size() > 0){
			return entityList.get(0);
		}

		entityAsType.newE();
		return entityAsType;
	}

	/**
	 * Alter table
	 * 
	 * @param entity the entity containing the new field
	 * @param field the field to add
	 * @return ?
	 */
	public ResultSet addColumnToTable(AbstractOrmEntity entity, Field<?> field) {
		return sgdb.addColumnToTable(entity, field);
	}

	/**
	 * Sert à ajouter un index dans la table SQL de l'entité pour un field en particulier
	 * 
	 * @param entity entité
	 * @param field champ
	 */
	public void createIndex(AbstractOrmEntity entity, Field<?> field) {
		createIndex(entity.getSystemName(), field.getSystemName());
	}

	/**
	 * Sert à ajouter un index dans la table SQL de l'entité pour un field en particulier
	 * 
	 * @param entityName nom de l'entité
	 * @param fieldName nom du field
	 */
	public void createIndex(String entityName, String fieldName) {
		sgdb.createIndex(entityName, fieldName);
	}

	/**
	 * @param entitySystemName nom système d'une entité
	 * @return true si l'entité a une table dans la base de donnée, sinon false
	 */
	public boolean isEntityExists(String entitySystemName) {
		return sgdb.isEntityExists(entitySystemName);
	}

	/**
	 * @param searchEntity entité de recherche
	 * @return nombre d'occurence du type de l'entité de recherche
	 */
	public int count(AbstractOrmEntity searchEntity) {
		return sgdb.count(searchEntity);
	}

	/**
	 * @param searchEntity entité de recherche
	 * @param searchParameterList liste de paramètres de recherche
	 * @return nombre d'occurence du type de l'entité de recherche
	 */
	@Deprecated
	public int count(AbstractOrmEntity searchEntity, Vector<String> searchParameterList) {
		return sgdb.count(searchEntity, searchParameterList);
	}

	/**
	 * @param searchEntity entité de recherche
	 * @param searchCriteriasParam critères de recherche
	 * @param limit limite de résultats
	 * @param offset offset de début de résultats
	 * @return liste d'entités trouvées
	 */
	public Vector<AbstractOrmEntity> select(AbstractOrmEntity searchEntity, Vector<String> searchCriteriasParam,
	        int limit, int offset) {
		return select(searchEntity, searchCriteriasParam, limit, offset, null);
	}

	/**
	 * @param searchEntity entité
	 * @param searchParameters critère de recherche
	 * @param limit limite
	 * @param offset offset
	 * @param orderBy ordre
	 * @return liste d'entité
	 */
	@Deprecated
	public Vector<AbstractOrmEntity> select(AbstractOrmEntity searchEntity, Vector<String> searchParameters, int limit,
	        int offset, String orderBy) {
		return EntityCreator.createEntitiesFromResultSet(
		        sgdb.select(searchEntity, searchParameters, limit, offset, orderBy), searchEntity);
	}

	/**
	 * Use only for complex queries. Use the select that takes only a vector of entities instead Method used to do
	 * search queries done from the views to the databse. The search criterias that has been passed in parameter are a
	 * list of string that has been generated by the view modules
	 * 
	 * @param searchEntity the entity that has to be researched
	 * @param searchCriteriasParam the search criterias formatted into strings
	 * @return a vector of ormizable entities
	 */
	@Deprecated
	public Vector<AbstractOrmEntity> select(AbstractOrmEntity searchEntity, Vector<String> searchCriteriasParam) {
		return EntityCreator.createEntitiesFromResultSet(sgdb.select(searchEntity, searchCriteriasParam), searchEntity);
	}

	/**
	 * Uses the new where builder Method used to do search queries done from the views to the databse. The search
	 * criterias that has been passed in parameter are a list of string that has been generated by the view modules
	 * 
	 * @param searchEntities the entities from which we will perform the search
	 * @return the entities
	 */
	public Vector<AbstractOrmEntity> select(Vector<AbstractOrmEntity> searchEntities) {
		return EntityCreator.createEntitiesFromResultSet(sgdb.select(searchEntities), searchEntities.get(0));
	}

	/**
	 * @param searchEntity the single search entity
	 * @return the entities that have been selected in the db
	 */
	public Vector<AbstractOrmEntity> select(AbstractOrmEntity searchEntity) {
		return select(vectorize(searchEntity));
	}

	/**
	 * Select. Use only if you are sure that it will return only 1 entity (or that you want the first entity)
	 * 
	 * @param searchEntity the entity from which we will perform our search
	 * @return the first entity from the result set
	 */
	public AbstractOrmEntity selectUnique(AbstractOrmEntity searchEntity) {
		return select(searchEntity).get(0);
	}

	/**
	 * Select. Use only if you are sure that it will return only 1 entity (or that you want the first entity)
	 * 
	 * @param searchEntities the entity from which we will perform our search
	 * @return the first entity from the result set
	 */
	public AbstractOrmEntity selectUnique(Vector<AbstractOrmEntity> searchEntities) {
		return select(searchEntities).get(0);
	}

	/**
	 * Method used to insert an entity in the databse based into the entity passed in parameter
	 * 
	 * @param newEntity the entity to add
	 * @return le id de clé primaire ajoutée
	 */
	public int insert(AbstractOrmEntity newEntity) {
		return sgdb.insert(newEntity);
	}

	/**
	 * Insert an entity if no entity matches current field
	 * 
	 * @param newUniqueEntity New unique entity to insert
	 */
	public void insertUnique(AbstractOrmEntity newUniqueEntity) {
		if(select(newUniqueEntity).size() < 1){
			insert(newUniqueEntity);
		}
	}

	/**
	 * @param entityAsType entité en tant que référence de type ou entité vide pour ajouter des champs
	 * @param fieldName1 key1
	 * @param fieldValue1 value1
	 * @param fieldName2 key2
	 * @param fieldValue2 value2
	 */
	@Deprecated
	public void delete(AbstractOrmEntity entityAsType, String fieldName1, String fieldValue1, String fieldName2,
	        String fieldValue2) {
		entityAsType.setData(fieldName1, fieldValue1);
		entityAsType.setData(fieldName2, fieldValue2);

		Vector<AbstractOrmEntity> entityList = entityAsType.get();

		if(entityList.size() > 0){
			entityList.get(0).delete();
		}

	}

	/**
	 * Method used to delete an entity from the database
	 * 
	 * @param searchEntity the entity to be researched
	 * @param searchCriterias the search criterias for the where clause
	 */
	@Deprecated
	public void delete(AbstractOrmEntity searchEntity, Vector<String> searchCriterias) {
		sgdb.delete(searchEntity, searchCriterias);
	}

	/**
	 * Uses the new where builder Method used to delete an entity from the database
	 * 
	 * @param searchEntities the entities from which we will build our where clause
	 */
	public void delete(Vector<AbstractOrmEntity> searchEntities) {
		sgdb.delete(searchEntities);
	}

	/**
	 * Using new where builder Method used to delete an entity from the database
	 * 
	 * @param searchEntity the entity from which we will build our where
	 */
	public void delete(AbstractOrmEntity searchEntity) {
		delete(vectorize(searchEntity));
	}

	/**
	 * Method used to update / change an entity
	 * 
	 * @param entityContainingChanges the entity that has been changed and will be in the orm
	 * @param searchCriterias the criterias used by the update
	 */
	@Deprecated
	public void update(AbstractOrmEntity entityContainingChanges, Vector<String> searchCriterias) {
		sgdb.update(entityContainingChanges, searchCriterias);
	}

	/**
	 * Uses the new where builder Method used to update / change an entity
	 * 
	 * @param searchEntities the entities from which we will build our where clause
	 * @param entityContainingChanges the changes to apply
	 */
	public void update(Vector<AbstractOrmEntity> searchEntities, AbstractOrmEntity entityContainingChanges) {
		sgdb.update(searchEntities, entityContainingChanges);
	}

	/**
	 * Vectorize.
	 * 
	 * @param <T> the generic type
	 * @param object the object
	 * @return the vector
	 */
	private <T> Vector<T> vectorize(T object) {
		Vector<T> objectVector = new Vector<T>();
		objectVector.add(object);
		return objectVector;
	}
}
