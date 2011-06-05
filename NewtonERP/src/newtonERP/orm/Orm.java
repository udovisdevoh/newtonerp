package newtonERP.orm;

import java.sql.ResultSet;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.sgbd.AbstractSgbd;
import newtonERP.taskManager.TaskManager;

/**
 * Basic class for the orm. It is used to put the objects in the databse using a
 * sgdb and its java binding. The orm will receive an entity from which the orm
 * will perform various tasks such as generating the query and executing it
 * obviously. Then it's gonna send the query to the SgbdSqlite class to execute
 * it.
 * 
 * http://www.sqlite.org/lang_keywords.html
 * 
 * TODO: Drop a module (maybe a an array of the entities to drop?)
 * 
 * @author r3hallejo, r3lacasgu, r3cloutjo
 */
public class Orm {

    @Deprecated
    private static AbstractSgbd getSgbd() {
        return OrmFonction.getSgbd();
    }

    /**
     * Used to initialize the connection
     */
    @Deprecated
    public static void connect() {
        getSgbd().connect();
    }

    /**
     * Used to disconnect from the db
     */
    @Deprecated
    public static void disconnect() {
        getSgbd().disconnect();
    }

    /**
     * @param entityAsType entity as a type reference or empty entity to fill
     *            data from orm
     * @param fieldName key
     * @param fieldValue value
     * @return new entity or found entity
     */
    public static AbstractOrmEntity getOrCreateEntity(
            AbstractOrmEntity entityAsType, String fieldName, String fieldValue) {
        entityAsType.setData(fieldName, fieldValue);

        Vector<AbstractOrmEntity> entityList = entityAsType.get();

        if (entityList.size() > 0) {
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
    public static AbstractOrmEntity getOrCreateEntity(
            AbstractOrmEntity entityAsType, String fieldName1,
            String fieldValue1, String fieldName2, String fieldValue2) {
        entityAsType.setData(fieldName1, fieldValue1);
        entityAsType.setData(fieldName2, fieldValue2);

        Vector<AbstractOrmEntity> entityList = entityAsType.get();

        if (entityList.size() > 0) {
            return entityList.get(0);
        }

        entityAsType.newE();
        return entityAsType;
    }

    /**
     * Creates the non-existent table from the modules in the database
     */
    @Deprecated
    public static void createNonExistentTables() {
        OrmFonction.createNonExistentTables();
    }

    /**
     * Alter table
     * 
     * @param entity the entity containing the new field
     * @param field the field to add
     * @return ?
     */
    public static ResultSet addColumnToTable(AbstractOrmEntity entity,
            Field<?> field) {
        return getSgbd().addColumnToTable(entity, field);
    }

    /**
     * Sert à ajouter un index dans la table SQL de l'entité pour un field en
     * particulier
     * @param entity entité
     * @param field champ
     */
    public static void createIndex(AbstractOrmEntity entity, Field<?> field) {
        createIndex(entity.getSystemName(), field.getSystemName());
    }

    /**
     * Sert à ajouter un index dans la table SQL de l'entité pour un field en
     * particulier
     * @param entityName nom de l'entité
     * @param fieldName nom du field
     */
    public static void createIndex(String entityName, String fieldName) {
        getSgbd().createIndex(entityName, fieldName);
    }

    /**
     * To execute a custom query
     * 
     * @param sqlQuery the executed
     */
    @Deprecated
    public static void executeCustomQuery(String sqlQuery) {
        getSgbd().execute(sqlQuery, OrmActions.OTHER);
    }

    /**
     * @param entitySystemName nom système d'une entité
     * @return true si l'entité a une table dans la base de donnée, sinon false
     */
    public static boolean isEntityExists(String entitySystemName) {
        return getSgbd().isEntityExists(entitySystemName);
    }

    /**
     * @param searchEntity entité de recherche
     * @return nombre d'occurence du type de l'entité de recherche
     */
    public static int count(AbstractOrmEntity searchEntity) {
        return count(searchEntity, null);
    }

    /**
     * @param searchEntity entité de recherche
     * @param searchParameterList liste de paramètres de recherche
     * @return nombre d'occurence du type de l'entité de recherche
     */
    public static int count(AbstractOrmEntity searchEntity,
            Vector<String> searchParameterList) {
        return getSgbd().count(searchEntity, searchParameterList);
    }

    /**
     * @param searchEntity entité de recherche
     * @param searchCriteriasParam critères de recherche
     * @param limit limite de résultats
     * @param offset offset de début de résultats
     * @return liste d'entités trouvées
     */
    public static Vector<AbstractOrmEntity> select(
            AbstractOrmEntity searchEntity,
            Vector<String> searchCriteriasParam, int limit, int offset) {
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
    public static Vector<AbstractOrmEntity> select(
            AbstractOrmEntity searchEntity, Vector<String> searchParameters,
            int limit, int offset, String orderBy) {
        return EntityCreator.createEntitiesFromResultSet(
                getSgbd().select(searchEntity, searchParameters, limit, offset,
                        orderBy), searchEntity);
    }

    /**
     * Use only for complex queries. Use the select that takes only a vector of
     * entities instead
     * 
     * Method used to do search queries done from the views to the databse. The
     * search criterias that has been passed in parameter are a list of string
     * that has been generated by the view modules
     * 
     * @param searchEntity the entity that has to be researched
     * @param searchCriteriasParam the search criterias formatted into strings
     * @return a vector of ormizable entities
     */
    @Deprecated
    public static Vector<AbstractOrmEntity> select(
            AbstractOrmEntity searchEntity, Vector<String> searchCriteriasParam) {
        return EntityCreator.createEntitiesFromResultSet(
                getSgbd().select(searchEntity, searchCriteriasParam),
                searchEntity);
    }

    /**
     * Uses the new where builder
     * 
     * Method used to do search queries done from the views to the databse. The
     * search criterias that has been passed in parameter are a list of string
     * that has been generated by the view modules
     * 
     * @param searchEntities the entities from which we will perform the search
     * @return the entities
     */
    public static Vector<AbstractOrmEntity> select(
            Vector<AbstractOrmEntity> searchEntities) {
        return EntityCreator.createEntitiesFromResultSet(
                getSgbd().select(searchEntities), searchEntities.get(0));
    }

    /**
     * @param searchEntity the single search entity
     * @return the entities that have been selected in the db
     */
    public static Vector<AbstractOrmEntity> select(
            AbstractOrmEntity searchEntity) {
        Vector<AbstractOrmEntity> searchEntities = new Vector<AbstractOrmEntity>();
        searchEntities.add(searchEntity);
        return select(searchEntities);
    }

    /**
     * Select. Use only if you are sure that it will return only 1 entity (or
     * that you want the first entity)
     * 
     * @param searchEntity the entity from which we will perform our search
     * @return the first entity from the result set
     */
    public static AbstractOrmEntity selectUnique(AbstractOrmEntity searchEntity) {
        return select(searchEntity).get(0);
    }

    /**
     * Select. Use only if you are sure that it will return only 1 entity (or
     * that you want the first entity)
     * 
     * @param searchEntities the entity from which we will perform our search
     * @return the first entity from the result set
     */
    public static AbstractOrmEntity selectUnique(
            Vector<AbstractOrmEntity> searchEntities) {
        return select(searchEntities).get(0);
    }

    /**
     * Method used to insert an entity in the databse based into the entity
     * passed in parameter
     * 
     * @param newEntity the entity to add
     * @return le id de clé primaire ajoutée
     */
    public static int insert(AbstractOrmEntity newEntity) {
        int primaryKeyValue = getSgbd().insert(newEntity);
        // TODO: sa pas rapport ici sa...
        if (primaryKeyValue != 0)
            TaskManager.executeTasks(newEntity, primaryKeyValue);

        return primaryKeyValue;
    }

    /**
     * Insert an entity if no entity matches current field
     * 
     * @param newUniqueEntity New unique entity to insert
     */
    public static void insertUnique(AbstractOrmEntity newUniqueEntity) {
        if (select(newUniqueEntity).size() < 1)
            insert(newUniqueEntity);
    }

    /**
     * @param entityAsType entité en tant que référence de type ou entité vide
     *            pour ajouter des champs
     * @param fieldName1 key1
     * @param fieldValue1 value1
     * @param fieldName2 key2
     * @param fieldValue2 value2
     */
    public static void delete(AbstractOrmEntity entityAsType,
            String fieldName1, String fieldValue1, String fieldName2,
            String fieldValue2) {
        entityAsType.setData(fieldName1, fieldValue1);
        entityAsType.setData(fieldName2, fieldValue2);

        Vector<AbstractOrmEntity> entityList = entityAsType.get();

        if (entityList.size() > 0) {
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
    public static void delete(AbstractOrmEntity searchEntity,
            Vector<String> searchCriterias) {
        getSgbd().delete(searchEntity, searchCriterias);
    }

    /**
     * Uses the new where builder
     * 
     * Method used to delete an entity from the database
     * 
     * @param searchEntities the entities from which we will build our where
     *            clause
     */
    public static void delete(Vector<AbstractOrmEntity> searchEntities) {
        getSgbd().delete(searchEntities);
    }

    /**
     * Using new where builder
     * 
     * Method used to delete an entity from the database
     * 
     * @param searchEntity the entity from which we will build our where
     */
    public static void delete(AbstractOrmEntity searchEntity) {
        Vector<AbstractOrmEntity> searchEntities = new Vector<AbstractOrmEntity>();
        searchEntities.add(searchEntity);
        delete(searchEntities);
    }

    /**
     * Method used to update / change an entity
     * 
     * @param entityContainingChanges the entity that has been changed and will
     *            be in the orm
     * @param searchCriterias the criterias used by the update
     */
    @Deprecated
    public static void update(AbstractOrmEntity entityContainingChanges,
            Vector<String> searchCriterias) {
        getSgbd().update(entityContainingChanges, searchCriterias);
    }

    /**
     * Uses the new where builder
     * 
     * Method used to update / change an entity
     * 
     * @param searchEntities the entities from which we will build our where
     *            clause
     * @param entityContainingChanges the changes to apply
     */
    public static void update(Vector<AbstractOrmEntity> searchEntities,
            AbstractOrmEntity entityContainingChanges) {
        getSgbd().update(searchEntities, entityContainingChanges);
    }

    /**
     * Uses the new where builder
     * 
     * Method used to update / change an entity
     * 
     * @param searchEntity the entities from which we will build our where
     *            clause
     * @param entityContainingChanges the changes to apply
     */
    public static void updateUnique(AbstractOrmEntity searchEntity,
            AbstractOrmEntity entityContainingChanges) {
        getSgbd().updateUnique(searchEntity, entityContainingChanges);
    }

    /**
     * Fait un backup de la DB si l'intervale de temps est assez grande
     */
    @Deprecated
    // cette fonction ne devrai plus etre apellé a l'extérieur de l'ORM
    public static void doBackupIfTimeIntervalAllows() {
        OrmFonction.doBackupIfTimeIntervalAllows();
    }
}
