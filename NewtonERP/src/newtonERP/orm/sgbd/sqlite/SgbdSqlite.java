package newtonERP.orm.sgbd.sqlite;

// TODO: clean up that file

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import newtonERP.common.logging.Logger;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.exceptions.OrmSqlException;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.type.FieldBool;
import newtonERP.orm.fields.field.type.FieldDateTime;
import newtonERP.orm.fields.field.type.FieldDouble;
import newtonERP.orm.fields.field.type.FieldInt;
import newtonERP.orm.fields.field.type.FieldString;
import newtonERP.orm.sgbd.AbstractSgbd;
import newtonERP.orm.sgbd.OrmActions;

// TODO: Auto-generated Javadoc
/**
 * Class who's role is only for executing the statements that has been sent from the orm.
 * 
 * @author r3lacasgu, r3hallejo
 */
public class SgbdSqlite extends AbstractSgbd {

	/** The prefix. */
	private static String prefix = "Bee_";

	/** The data base file name. */
	private static String dataBaseFileName = "test.db";

	/** The connexion. */
	private Connection connexion;

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#execute(java.lang.String, newtonERP.orm.OrmActions)
	 */
	@Override
	protected ResultSet execute(String request, OrmActions action)

	{
		Statement stat = null;
		try{
			stat = connexion.createStatement();

			if(action.equals(OrmActions.SEARCH)){
				ResultSet rs = stat.executeQuery(request);
				return rs;
			}

			stat.execute(request);
			return stat.getGeneratedKeys();

		}catch(SQLException e){
			throw new OrmSqlException(
			        "SqlException when executing the request. Check for entity names matching SqLite keyword could be done : "
			                + e.getMessage());
		}finally{
			if(stat != null){
				try{
					stat.close();
				}catch(SQLException e){
					throw new OrmSqlException("SqlException when closing the statements" + e.getMessage());
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#disconnect()
	 */
	@Override
	public void disconnect() {
		try{
			connexion.close();
		}catch(SQLException e){
			throw new OrmSqlException("Error whe disconnecting from the database : " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#connect()
	 */
	@Override
	public void connect() {
		try{
			Class.forName("org.sqlite.JDBC");
			connexion = DriverManager.getConnection("jdbc:sqlite:" + dataBaseFileName);
		}catch(Exception e){
			throw new OrmSqlException("Error connecting to the database : " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#addColumnToTable(newtonERP.module.AbstractOrmEntity,
	 * newtonERP.orm.field.Field)
	 */
	@Override
	public ResultSet addColumnToTable(AbstractOrmEntity entity, Field field)

	{
		String sqlQuery = "ALTER TABLE " + prefix + entity.getSystemName() + " ADD COLUMN ";

		if(field instanceof FieldDouble){
			sqlQuery += " " + field.getSystemName() + " DOUBLE PRECISION;";
		}else if(field instanceof FieldString || field instanceof FieldDateTime){
			sqlQuery += " " + field.getSystemName() + " STRING;";
		}else if(field instanceof FieldBool || field instanceof FieldInt){
			sqlQuery += " " + field.getSystemName() + " INTEGER;";
		}

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);

		return execute(sqlQuery, OrmActions.OTHER);

		// todo: mettre une valeur par defaut dans la colone ajoute
	}

	/**
	 * This is the new where builder! Method used to build the where clause for the query.
	 * 
	 * @param searchEntities the entities used for the search
	 * @return the sqlQuery
	 */
	private static String buildWhereClauseForQuery(Vector<AbstractOrmEntity> searchEntities) {
		String whereClause = "";
		int entityPosition = 0;
		boolean addedCriteriaToWhereCondition = false;
		whereClause += " WHERE ";

		for(AbstractOrmEntity entity : searchEntities){
			// Si les fields de cette entité ne contiennent que des null
			if(!entity.getFields().containsValues()){
				continue;
			}

			entityPosition += 1;
			whereClause += "( ";

			for(Field field : entity.getFields().getFields()){
				if(field.getCalcul() == null && field.getData() != null){
					whereClause += field.getSystemName() + " " + field.getOperator() + " '" + field.getDataString(true)
					        + "'";

					whereClause += " AND ";
				}
			}

			whereClause = whereClause.substring(0, whereClause.length() - 4);

			if(entity.getFields().getFields().size() < entityPosition){
				whereClause += " OR ";
			}

			whereClause += ")";
			// au moins un field était
			// utilisable donc on cré
			// un where
			addedCriteriaToWhereCondition = true;
		}

		if(addedCriteriaToWhereCondition){
			return whereClause; // On retourne la clause du where car elle n'est
		}
		// pas vide
		return "";// Sinon, aucune clause where ne doit être ajoutée
	}

	/**
	 * Method used internally by the update method to build the set statement.
	 * 
	 * @param fields the data from the entities
	 * @param sqlQuery the non-finished sqlQuery
	 * @return the sqlQuery
	 */
	private static String buildSetClauseForQuery(Fields fields, String sqlQuery) {
		Iterator<Field> dataIterator = fields.iterator();

		while(dataIterator.hasNext()){
			// Retrieve key
			Field data = dataIterator.next();
			if(!data.getSystemName().matches("PK.*") && data.getCalcul() == null && data.getData() != null){
				sqlQuery += data.getSystemName() + "='" + data.getDataString(true) + "', ";
			}
		}

		return sqlQuery.substring(0, sqlQuery.length() - 2);
	}

	/**
	 * Use only for complex queries. Use buildWhereClauseForQuery instead Method used to build the where clause for the
	 * delete, select and update methods.
	 * 
	 * @param sqlQuery the non-finished sqlQuery that has been produced
	 * @param searchCriterias the parameters of the where clause under form of strings
	 * @return sqlQuery the sqlQuery with the where statement
	 */
	private static String buildWhereClauseForQuery(String sqlQuery, Vector<String> searchCriterias) {
		return sqlQuery + buildWhereClause(searchCriterias) + ";";
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#select(newtonERP.module.AbstractOrmEntity, java.util.Vector)
	 */
	@Override
	@Deprecated
	public ResultSet select(AbstractOrmEntity searchEntity, Vector<String> searchCriteriasParam) {
		String sqlQuery = "SELECT * FROM " + prefix + searchEntity.getSystemName();

		if(searchCriteriasParam != null){
			sqlQuery = buildWhereClauseForQuery(sqlQuery, searchCriteriasParam);
		}

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);

		ResultSet rs = execute(sqlQuery, OrmActions.SEARCH);

		return rs;
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#select(newtonERP.module.AbstractOrmEntity, java.util.Vector, int, int,
	 * java.lang.String)
	 */
	@Deprecated
	@Override
	public ResultSet select(AbstractOrmEntity searchEntity, Vector<String> searchCriteriasParam, int limit, int offset,
	        String orderBy) {
		String sqlQuery = "SELECT * FROM " + prefix + searchEntity.getSystemName();

		if(searchCriteriasParam != null){
			sqlQuery += buildWhereClause(searchCriteriasParam);
		}

		if(orderBy != null && orderBy.length() > 0){
			sqlQuery += " ORDER BY " + orderBy;
		}

		sqlQuery = sqlQuery + " LIMIT " + offset + ", " + limit + ";";

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);

		ResultSet rs = execute(sqlQuery, OrmActions.SEARCH);

		return rs;
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#select(java.util.Vector)
	 */
	@Override
	public ResultSet select(Vector<AbstractOrmEntity> searchEntities)

	{
		String sqlQuery = "SELECT * FROM " + prefix + searchEntities.get(0).getSystemName();

		if(!searchEntities.isEmpty()){
			sqlQuery += buildWhereClauseForQuery(searchEntities) + ";";
		}

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);

		ResultSet rs = execute(sqlQuery, OrmActions.SEARCH);

		return rs;
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#select(java.util.Vector, int, int, boolean)
	 */
	@Override
	public ResultSet select(Vector<AbstractOrmEntity> searchEntities, int limit, int offset, boolean orderByAsc) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#insert(newtonERP.module.AbstractOrmEntity)
	 */
	@Override
	public int insert(AbstractOrmEntity newEntity) {
		String sqlQuery = "INSERT INTO " + prefix + newEntity.getSystemName() + "( ";

		Iterator<?> keyIterator = newEntity.getFields().iterator();

		while(keyIterator.hasNext()){
			Field field = (Field) keyIterator.next();

			if(!field.getSystemName().matches("PK.*")){
				if(field.getCalcul() == null && field.getData() != null){
					sqlQuery += "'" + field.getSystemName() + "', ";
				}
			}

		}

		sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
		sqlQuery += ") VALUES (";

		Iterator<?> dataIterator = newEntity.getFields().iterator();

		while(dataIterator.hasNext()){
			Field field = (Field) dataIterator.next();

			if(!field.getSystemName().matches("PK.*")){
				if(field.getCalcul() == null && field.getData() != null){
					sqlQuery += "'" + field.getDataString(true) + "', ";
				}
			}
		}

		sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
		sqlQuery += ");";

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);

		ResultSet rs = execute(sqlQuery, OrmActions.INSERT);

		int primaryKeyValue;
		try{
			primaryKeyValue = rs.getInt(1);

		}catch(SQLException e){
			// s'il n'y a pas de cle primaire dans cette table, on ne throw donc
			// pas cette exception
			primaryKeyValue = 0;
		}

		return primaryKeyValue;
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#delete(newtonERP.module.AbstractOrmEntity, java.util.Vector)
	 */
	@Override
	@Deprecated
	public void delete(AbstractOrmEntity searchEntity, Vector<String> searchCriterias) {
		String sqlQuery = "DELETE FROM " + prefix + searchEntity.getSystemName();

		sqlQuery = buildWhereClauseForQuery(sqlQuery, searchCriterias);

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);

		execute(sqlQuery, OrmActions.DELETE);
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#delete(java.util.Vector)
	 */
	@Override
	public void delete(Vector<AbstractOrmEntity> searchEntities)

	{
		String sqlQuery = "DELETE FROM " + prefix + searchEntities.get(0).getSystemName();

		sqlQuery += buildWhereClauseForQuery(searchEntities) + ";";

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);

		execute(sqlQuery, OrmActions.DELETE);
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#update(newtonERP.module.AbstractOrmEntity, java.util.Vector)
	 */
	@Deprecated
	@Override
	public void update(AbstractOrmEntity entityContainingChanges, Vector<String> searchCriterias) {
		String sqlQuery = "UPDATE " + prefix + entityContainingChanges.getSystemName() + " SET ";

		sqlQuery = buildSetClauseForQuery(entityContainingChanges.getFields(), sqlQuery);
		sqlQuery = buildWhereClauseForQuery(sqlQuery, searchCriterias);

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);

		execute(sqlQuery, OrmActions.UPDATE);
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#update(java.util.Vector, newtonERP.module.AbstractOrmEntity)
	 */
	@Override
	public void update(Vector<AbstractOrmEntity> searchEntities, AbstractOrmEntity entityContainingChanges) {
		String sqlQuery = "UPDATE " + prefix + entityContainingChanges.getSystemName() + " SET ";

		sqlQuery = buildSetClauseForQuery(entityContainingChanges.getFields(), sqlQuery);
		sqlQuery += buildWhereClauseForQuery(searchEntities) + ";";

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);

		execute(sqlQuery, OrmActions.UPDATE);
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#isEntityExists(java.lang.String)
	 */
	@Override
	public boolean isEntityExists(String entitySystemName) {
		String sqlQuery = "SELECT name FROM sqlite_master where name='" + prefix + entitySystemName + "';";

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);

		ResultSet rs = execute(sqlQuery, OrmActions.SEARCH);

		try{
			return rs.next();
		}catch(SQLException e){
			throw new OrmSqlException("probleme ici");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#createIndex(java.lang.String, java.lang.String)
	 */
	@Override
	public void createIndex(String entityName, String fieldName)

	{
		String indexName = fieldName + "_index";
		String tableName = prefix + entityName;

		String sqlQuery = "CREATE INDEX IF NOT EXISTS " + indexName + " ON " + tableName + " (" + fieldName + ");";

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);
		execute(sqlQuery, OrmActions.OTHER);
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#createTableForEntity(newtonERP.module.AbstractOrmEntity)
	 */
	@Override
	public void createTableForEntity(AbstractOrmEntity entity)

	{
		// Be sure to create the table only if it doesn't already
		// exists
		String sqlQuery = "CREATE TABLE IF NOT EXISTS ";
		Collection<Field> fields = ((AbstractEntity) entity).getFields().getFields();

		sqlQuery += prefix + entity.getSystemName() + " ( ";

		// For each field into my entity
		for(Field field : fields){
			// If it is a primary because it matches PK, else we
			// check the datatypes and match them with a datatype
			// good for the database
			if(field.getCalcul() != null){
				// do not do anything
			}else if(field.getSystemName().matches("PK.*")){
				sqlQuery += field.getSystemName() + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
			}else if(field instanceof FieldDouble){
				sqlQuery += field.getSystemName() + " DOUBLE PRECISION, ";
			}else if(field instanceof FieldString || field instanceof FieldDateTime){
				sqlQuery += field.getSystemName() + " STRING, ";
			}else if(field instanceof FieldBool || field instanceof FieldInt){
				sqlQuery += field.getSystemName() + " INTEGER, ";
			}
		}
		sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2) + " );";

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);

		execute(sqlQuery, OrmActions.CREATE);
	}

	/**
	 * Builds the where clause.
	 * 
	 * @param searchCriteriasParam the search criterias param
	 * @return the string
	 */
	private static String buildWhereClause(Vector<String> searchCriteriasParam) {
		String whereClause = " WHERE ( ";

		// We add each string to the sqlQuery
		for(String parameter : searchCriteriasParam){
			whereClause += parameter + " ";
		}

		return whereClause + " )";
	}

	/*
	 * (non-Javadoc)
	 * @see newtonERP.orm.sgbd.AbstractSgbd#count(newtonERP.module.AbstractOrmEntity, java.util.Vector)
	 */
	@Deprecated
	@Override
	public int count(AbstractOrmEntity searchEntity, Vector<String> searchParameterList) {
		// Pour une raison quelconque, la fonction COUNT de SQLite empêche la
		// transaction de se terminer
		// Alors on utilise un count fait à bras

		String sqlQuery = "SELECT " + searchEntity.getPrimaryKeyName() + " FROM " + prefix
		        + searchEntity.getSystemName();

		if(searchParameterList != null){
			sqlQuery += buildWhereClause(searchParameterList);
		}

		sqlQuery += ";";

		Logger.debug("[SGDB_SQLITE] SQL query produced : " + sqlQuery);

		ResultSet rs = execute(sqlQuery, OrmActions.SEARCH);

		int count = 0;
		try{
			while(rs.next()){
				count++;
			}

		}catch(SQLException e){
			throw new OrmSqlException("probleme ici");
		}
		return count;
	}
}
