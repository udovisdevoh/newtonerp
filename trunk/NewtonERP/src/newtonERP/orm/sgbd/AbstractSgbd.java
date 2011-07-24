package newtonERP.orm.sgbd;

// TODO: clean up that file

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import newtonERP.common.ListModule;
import newtonERP.common.logging.Logger;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.Module;
import newtonERP.module.exception.ModuleException;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.orm.fields.field.Field;

/**
 * Représente tous les SGBD et leurs implémentations et déclarations communes
 * 
 * @author BeeERP team
 */
public abstract class AbstractSgbd {

	/**
	 * Creates the non-existent table from all modules in the database.
	 */
	public final void createNonExistentTables() {
		for(String key : ListModule.getAllModules()){
			try{
				Module module = ListModule.getModule(key);
				createNonExistentTables(module);
			}catch(ModuleException e){
				// PrintStackTrace nécessaire pour afficher l'information de
				// l'exception précédente. Il faudrait mettre l'ancien
				// stackTrace dans le nouveau
				e.printStackTrace();
				throw new ModuleException("Erreur à la construction de la requête pour créer les tables : "
				        + e.getMessage());
			}
		}
	}

	/**
	 * Creates the non existent tables.
	 * 
	 * @param module the module
	 */
	private final void createNonExistentTables(Module module) {
		Collection<AbstractOrmEntity> moduleEntities = module.getEntityDefinitionList().values();

		// For each entity in the list of module entities
		for(AbstractOrmEntity entity : moduleEntities){
			createTableForEntity(entity);
			addMissingColumnsForEntity(entity);
			createIndexesForEntity(entity);
		}
	}

	/**
	 * Adds the missing columns for entity.
	 * 
	 * @param entity the entity
	 */
	private final void addMissingColumnsForEntity(AbstractOrmEntity entity) {
		for(Field field : entity.getFields()){
			try{
				addColumnToTable(entity, field);
			}catch(OrmException e){
				Logger.warning("[ORM] Champ déjà dans entité");
			}
		}
	}

	/**
	 * Creates the indexes for entity.
	 * 
	 * @param entity the entity
	 */
	private final void createIndexesForEntity(AbstractOrmEntity entity) {
		// On crée des index pour chaque clef étrangère
		for(String fieldName : entity.getFields().getKey()){
			if((fieldName.endsWith("ID") && !fieldName.startsWith("PK")) || fieldName.startsWith("system")){
				createIndex(entity.getSystemName(), fieldName);
			}
		}
	}

	/**
	 * Method that executes the sql query passed in parameter plus de action from the OrmAction
	 * 
	 * @param request l'action a effectue
	 * @param action the OrmActions that will be done
	 * @return le résultat sous forme d'un result set
	 */
	protected abstract ResultSet execute(String request, OrmActions action);

	/**
	 * Method used to initialize the connection to the database
	 */
	public abstract void connect();

	/**
	 * Method used to disconnect from the database
	 */
	public abstract void disconnect();

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
	public abstract ResultSet select(AbstractOrmEntity searchEntity, Vector<String> searchCriteriasParam);

	/**
	 * Uses the new where builder Method used to do search queries done from the views to the databse. The search
	 * criterias that has been passed in parameter are a list of string that has been generated by the view modules
	 * todo: correct doc
	 * 
	 * @param searchEntities the entities from which we will perform the search
	 * @return the entities
	 */
	public abstract ResultSet select(Vector<AbstractOrmEntity> searchEntities);

	/**
	 * Use only for complex queries. Use the select that takes only a vector of entities instead Method used to do
	 * search queries done from the views to the databse. The search criterias that has been passed in parameter are a
	 * list of string that has been generated by the view modules
	 * 
	 * @param searchEntity the entity that has to be researched
	 * @param searchCriteriasParam the search criterias formatted into strings
	 * @param limit limite de résultat
	 * @param offset offset du début des résultats
	 * @param orderBy ordre facultatif
	 * @return a vector of ormizable entities
	 */
	@Deprecated
	public abstract ResultSet select(AbstractOrmEntity searchEntity, Vector<String> searchCriteriasParam, int limit,
	        int offset, String orderBy);

	/**
	 * Use only for complex queries. Use the select that takes only a vector of entities instead Method used to do
	 * search queries done from the views to the databse. The search criterias that has been passed in parameter are a
	 * list of string that has been generated by the view modules
	 * 
	 * @param searchEntities the entities from which we will perform the search
	 * @param limit limite de résultat
	 * @param offset offset du début des résultats
	 * @param orderByAsc ordre facultatif
	 * @return a vector of ormizable entities
	 */
	public abstract ResultSet select(Vector<AbstractOrmEntity> searchEntities, int limit, int offset, boolean orderByAsc);

	/**
	 * @param searchEntity entité de recherche
	 * @return nombre d'occurence du type de l'entité de recherche
	 */
	public int count(AbstractOrmEntity searchEntity) {
		return count(searchEntity, null);
	}

	/**
	 * @param searchEntity entité de recherche
	 * @param searchParameterList paramêtres de recherche
	 * @return nombre d'occurence du type de l'entité de recherche
	 */
	@Deprecated
	public abstract int count(AbstractOrmEntity searchEntity, Vector<String> searchParameterList);

	/**
	 * Method used to insert an entity in the database based into the entity passed in parameter
	 * 
	 * @param newEntity the entity to add
	 * @return le id de clé primaire ajoutée
	 */
	public abstract int insert(AbstractOrmEntity newEntity);

	/**
	 * Method used to delete an entity from the database
	 * 
	 * @param searchEntity the entity to be researched
	 * @param searchCriterias the search criterias for the where clause
	 */
	@Deprecated
	public abstract void delete(AbstractOrmEntity searchEntity, Vector<String> searchCriterias);

	/**
	 * Uses the new where builder Method used to delete an entity from the database
	 * 
	 * @param searchEntities the entities from which we will build our where clause
	 */
	public abstract void delete(Vector<AbstractOrmEntity> searchEntities);

	/**
	 * Method used to update / change an entity
	 * 
	 * @param entityContainingChanges the entity that has been changed and will be in the orm
	 * @param searchCriterias the criterias used by the update
	 */
	@Deprecated
	public abstract void update(AbstractOrmEntity entityContainingChanges, Vector<String> searchCriterias);

	/**
	 * Uses the new where builder Method used to update / change an entity
	 * 
	 * @param searchEntities the entities from which we will build our where clause
	 * @param entityContainingChanges the changes to apply
	 */
	public abstract void update(Vector<AbstractOrmEntity> searchEntities, AbstractOrmEntity entityContainingChanges);

	/**
	 * Checks if this entity exists.
	 * 
	 * @param entitySystemName nom système d'une entité
	 * @return true si l'entité a une table dans la base de donnée, sinon false
	 */
	public abstract boolean isEntityExists(String entitySystemName);

	/**
	 * Alter table
	 * 
	 * @param entity the entity containing the new field
	 * @param field the field to add
	 * @return ?
	 */
	public abstract ResultSet addColumnToTable(AbstractOrmEntity entity, Field field);

	/**
	 * Créer une table pour un type d'entité
	 * 
	 * @param entity entité
	 */
	public abstract void createTableForEntity(AbstractOrmEntity entity);

	/**
	 * Sert à ajouter un index dans la table SQL de l'entité pour un field en particulier
	 * 
	 * @param entityName nom de l'entité
	 * @param fieldName nom du field
	 */
	public abstract void createIndex(String entityName, String fieldName);
}
