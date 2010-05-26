package newtonERP.orm.sgbd.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.BackupManager;
import newtonERP.orm.OrmActions;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.orm.exceptions.OrmSqlException;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldBool;
import newtonERP.orm.field.type.FieldDateTime;
import newtonERP.orm.field.type.FieldDouble;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.orm.sgbd.AbstractSgbd;

/**
 * Class who's role is only for executing the statements that has been sent from
 * the orm
 * 
 * @author r3lacasgu, r3hallejo
 */
public class SgbdSqlite extends AbstractSgbd
{
    private static String prefix = "Bee_";

    private static String dataBaseFileName = "test.db";

    private static Connection connexion;

    private SgbdSqliteBackupManager sgbdSqliteBackupManager = new SgbdSqliteBackupManager();

    @Override
    public ResultSet execute(String request, OrmActions action)
	    throws OrmException
    {
	try
	{
	    Statement stat = connexion.createStatement();

	    if (action.equals(OrmActions.SEARCH))
	    {
		ResultSet rs = stat.executeQuery(request);
		return rs;
	    }

	    stat.execute(request);
	    return stat.getGeneratedKeys();

	} catch (SQLException e)
	{
	    throw new OrmSqlException(
		    "SqlException when executing the request. Check for entity names matching SqLite keyword could be done : "
			    + e.getMessage());
	}
    }

    @Override
    public void disconnect() throws OrmException
    {
	try
	{
	    connexion.close();
	} catch (SQLException e)
	{
	    throw new OrmSqlException(
		    "Error whe disconnecting from the database : "
			    + e.getMessage());
	}
    }

    @Override
    public void connect() throws OrmException
    {
	try
	{
	    Class.forName("org.sqlite.JDBC");
	    connexion = DriverManager.getConnection("jdbc:sqlite:"
		    + dataBaseFileName);
	} catch (Exception e)
	{
	    throw new OrmSqlException("Error connecting to the database : "
		    + e.getMessage());
	}
    }

    @Override
    public ResultSet addColumnToTable(AbstractOrmEntity entity, Field<?> field)
	    throws OrmException
    {
	String sqlQuery = "ALTER TABLE " + prefix + entity.getSystemName()
		+ " ADD COLUMN ";

	if (field instanceof FieldDouble)
	{
	    sqlQuery += " " + field.getShortName() + " DOUBLE PRECISION;";
	}
	else if (field instanceof FieldString || field instanceof FieldDateTime)
	{
	    sqlQuery += " " + field.getShortName() + " STRING;";
	}
	else if (field instanceof FieldBool || field instanceof FieldInt)
	{
	    sqlQuery += " " + field.getShortName() + " INTEGER;";
	}

	// TODO: Remove the next line when it will be properly debugged
	System.out.println("SQL query produced : " + sqlQuery);

	return execute(sqlQuery, OrmActions.OTHER);

	// todo: mettre une valeur par defaut dans la colone ajoute
    }

    @Override
    public ResultSet select(AbstractOrmEntity searchEntity,
	    Vector<String> searchCriteriasParam) throws OrmException
    {
	String sqlQuery = "SELECT * FROM " + prefix
		+ searchEntity.getSystemName();

	if (searchCriteriasParam != null)
	    sqlQuery = buildWhereClauseForQuery(sqlQuery, searchCriteriasParam);

	// TODO: Remove the next line when it will be properly debugged
	System.out.println("SQL query produced : " + sqlQuery);

	ResultSet rs = execute(sqlQuery, OrmActions.SEARCH);

	return rs;
    }

    /**
     * This is the new where builder!
     * 
     * Method used to build the where clause for the query
     * 
     * @param sqlQuery the non-finished sqlQuery
     * @param searchEntities the entities used for the search
     * @return the sqlQuery
     */
    private static String buildWhereClauseForQuery(
	    Vector<AbstractOrmEntity> searchEntities)
    {
	String whereClause = "";
	int entityPosition = 0;
	boolean addedCriteriaToWhereCondition = false;
	whereClause += " WHERE ";

	for (AbstractOrmEntity entity : searchEntities)
	{
	    // Si les fields de
	    // cette entité
	    // ne contiennent que des null
	    if (!entity.getFields().containsValues())
		continue;

	    entityPosition += 1;
	    whereClause += "( ";

	    for (Field<?> field : entity.getFields().getFields())
	    {
		if (field.getCalcul() == null && field.getData() != null)
		{
		    whereClause += field.getShortName() + " "
			    + field.getOperator() + " '"
			    + field.getDataString(true) + "'";

		    whereClause += " AND ";
		}
	    }

	    whereClause = whereClause.substring(0, whereClause.length() - 4);

	    if (entity.getFields().getFields().size() < entityPosition)
		whereClause += " OR ";

	    whereClause += ")";
	    // au moins un field était
	    // utilisable donc on cré
	    // un where
	    addedCriteriaToWhereCondition = true;
	}

	if (addedCriteriaToWhereCondition)
	    return whereClause; // On retourne la clause du where car elle n'est
	// pas vide
	return "";// Sinon, aucune clause where ne doit être ajoutée
    }

    /**
     * This is the new where builder!
     * 
     * Method used to build the where clause for the query
     * 
     * @param sqlQuery the non-finished sqlQuery
     * @param searchEntities the entities used for the search
     * @return the sqlQuery
     */
    private static String buildWhereClauseForQuery(
	    AbstractOrmEntity searchEntity, String sqlQuery)
    {
	sqlQuery += " WHERE ( ";

	for (Field<?> field : searchEntity.getFields().getFields())
	{
	    if (field.getCalcul() == null && field.getData() != null)
	    {
		sqlQuery += field.getShortName() + " " + field.getOperator()
			+ " '" + field.getDataString(true) + "'";

		sqlQuery += " AND ";
	    }
	}

	sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 4);

	return sqlQuery += ");";
    }

    /**
     * Method used internally by the update method to build the set statement
     * 
     * @param fields the data from the entities
     * @param sqlQuery the non-finished sqlQuery
     * @return the sqlQuery
     */
    private static String buildSetClauseForQuery(Fields fields, String sqlQuery)
    {
	Iterator<Field<?>> dataIterator = fields.iterator();

	while (dataIterator.hasNext())
	{
	    // Retrieve key
	    Field<?> data = dataIterator.next();
	    if (!data.getShortName().matches("PK.*")
		    && data.getCalcul() == null && data.getData() != null)
	    {
		sqlQuery += data.getShortName() + "='"
			+ data.getDataString(true) + "', ";
	    }
	}

	return sqlQuery.substring(0, sqlQuery.length() - 2);
    }

    /**
     * Use only for complex queries. Use buildWhereClauseForQuery instead
     * 
     * Method used to build the where clause for the delete, select and update
     * methods.
     * 
     * @param sqlQuery the non-finished sqlQuery that has been produced
     * @param searchCriterias the parameters of the where clause under form of
     *            strings
     * @return sqlQuery the sqlQuery with the where statement
     */
    private static String buildWhereClauseForQuery(String sqlQuery,
	    Vector<String> searchCriterias)
    {
	return sqlQuery + buildWhereClause(searchCriterias) + ";";
    }

    @Override
    public ResultSet select(Vector<AbstractOrmEntity> searchEntities)
	    throws OrmException
    {
	String sqlQuery = "SELECT * FROM " + prefix
		+ searchEntities.get(0).getSystemName();

	if (!searchEntities.isEmpty())
	    sqlQuery += buildWhereClauseForQuery(searchEntities) + ";";

	// TODO: Remove the next line when it will be properly debugged
	System.out.println("SQL query produced : " + sqlQuery);

	ResultSet rs = execute(sqlQuery, OrmActions.SEARCH);

	return rs;
    }

    @Override
    public int insert(AbstractOrmEntity newEntity) throws OrmException
    {
	String sqlQuery = "INSERT INTO " + prefix + newEntity.getSystemName()
		+ "( ";

	Iterator<?> keyIterator = newEntity.getFields().iterator();

	while (keyIterator.hasNext())
	{
	    Field<?> field = (Field<?>) keyIterator.next();

	    if (!field.getShortName().matches("PK.*"))
	    {
		if (field.getCalcul() == null && field.getData() != null)
		{
		    sqlQuery += "'" + field.getShortName() + "', ";
		}
	    }

	}

	sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
	sqlQuery += ") VALUES (";

	Iterator<?> dataIterator = newEntity.getFields().iterator();

	while (dataIterator.hasNext())
	{
	    Field<?> field = (Field<?>) dataIterator.next();

	    if (!field.getShortName().matches("PK.*"))
	    {
		if (field.getCalcul() == null && field.getData() != null)
		{
		    sqlQuery += "'" + field.getDataString(true) + "', ";
		}
	    }
	}

	sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
	sqlQuery += ");";

	// TODO: Remove the next line once this will be properly debugged
	System.out.println("SQL query produced : " + sqlQuery);

	ResultSet rs = execute(sqlQuery, OrmActions.INSERT);

	int primaryKeyValue;
	try
	{
	    primaryKeyValue = rs.getInt(1);

	} catch (SQLException e)
	{
	    // s'il n'y a pas de cle primaire dans cette table, on ne throw donc
	    // pas cette exception
	    primaryKeyValue = 0;
	}

	return primaryKeyValue;
    }

    @Override
    public void delete(AbstractOrmEntity searchEntity,
	    Vector<String> searchCriterias) throws OrmException
    {
	String sqlQuery = "DELETE FROM " + prefix
		+ searchEntity.getSystemName();

	sqlQuery = buildWhereClauseForQuery(sqlQuery, searchCriterias);

	// TODO: Remove the next line once this will be properly debugged
	System.out.println("Sql query produced : " + sqlQuery);

	execute(sqlQuery, OrmActions.DELETE);
    }

    @Override
    public void delete(Vector<AbstractOrmEntity> searchEntities)
	    throws OrmException
    {
	String sqlQuery = "DELETE FROM " + prefix
		+ searchEntities.get(0).getSystemName();

	sqlQuery += buildWhereClauseForQuery(searchEntities) + ";";

	// TODO: Remove the next line once this will be properly debugged
	System.out.println("Sql query produced : " + sqlQuery);

	execute(sqlQuery, OrmActions.DELETE);
    }

    @Override
    public void update(AbstractOrmEntity entityContainingChanges,
	    Vector<String> searchCriterias) throws OrmException
    {
	String sqlQuery = "UPDATE " + prefix
		+ entityContainingChanges.getSystemName() + " SET ";

	sqlQuery = buildSetClauseForQuery(entityContainingChanges.getFields(),
		sqlQuery);
	sqlQuery = buildWhereClauseForQuery(sqlQuery, searchCriterias);

	// TODO: Remove this once it will be properly debugged
	System.out.println("Sql query produced : " + sqlQuery);

	execute(sqlQuery, OrmActions.UPDATE);
    }

    @Override
    public void update(Vector<AbstractOrmEntity> searchEntities,
	    AbstractOrmEntity entityContainingChanges) throws OrmException
    {
	String sqlQuery = "UPDATE " + prefix
		+ entityContainingChanges.getSystemName() + " SET ";

	sqlQuery = buildSetClauseForQuery(entityContainingChanges.getFields(),
		sqlQuery);
	sqlQuery += buildWhereClauseForQuery(searchEntities) + ";";

	// TODO: Remove this once it will be properly debugged
	System.out.println("Sql query produced : " + sqlQuery);

	execute(sqlQuery, OrmActions.UPDATE);
    }

    @Override
    public boolean isEntityExists(String entitySystemName) throws Exception
    {
	String sqlQuery = "SELECT name FROM sqlite_master where name='"
		+ prefix + entitySystemName + "';";

	// TODO: Remove the next line when it will be properly debugged
	System.out.println("SQL query produced : " + sqlQuery);

	ResultSet rs = execute(sqlQuery, OrmActions.SEARCH);

	return rs.next();
    }

    @Override
    public void createIndex(String entityName, String fieldName)
	    throws OrmException
    {
	String indexName = fieldName + "_index";
	String tableName = prefix + entityName;

	String sqlQuery = "CREATE INDEX IF NOT EXISTS " + indexName + " ON "
		+ tableName + " (" + fieldName + ");";

	System.out.println("Sql query produced : " + sqlQuery);
	execute(sqlQuery, OrmActions.OTHER);
    }

    @Override
    public void createTableForEntity(AbstractOrmEntity entity)
	    throws OrmException
    {
	// Be sure to create the table only if it doesn't already
	// exists
	String sqlQuery = "CREATE TABLE IF NOT EXISTS ";
	Collection<Field<?>> fields = ((AbstractEntity) entity).getFields()
		.getFields();

	sqlQuery += prefix + entity.getSystemName() + " ( ";

	// For each field into my entity
	for (Field<?> field : fields)
	{
	    // If it is a primary because it matches PK, else we
	    // check the datatypes and match them with a datatype
	    // good for the database
	    // TODO : Jo je ne comprend pas pourquoi tu fesait ca,
	    // bref ca buggait car il arrivait sur le if, ce n'était
	    // pas vrai alors il n'insérait aucun champs. Svp dire
	    // c'est quoi tu veut faire. La je l'ai enlevé, anyway
	    // ca sert a rien de faire un if avec aucun traitement
	    // non?
	    if (field.getCalcul() != null)
	    {
		// do not do anything
	    }
	    else if (field.getShortName().matches("PK.*"))
	    {
		sqlQuery += field.getShortName()
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, ";
	    }
	    else if (field instanceof FieldDouble)
	    {
		sqlQuery += field.getShortName() + " DOUBLE PRECISION, ";
	    }
	    else if (field instanceof FieldString
		    || field instanceof FieldDateTime)
	    {
		sqlQuery += field.getShortName() + " STRING, ";
	    }
	    else if (field instanceof FieldBool || field instanceof FieldInt)
	    {
		sqlQuery += field.getShortName() + " INTEGER, ";
	    }
	}
	sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2) + " );";

	// TODO: Remove the next line when properly debugged
	System.out.println("Sql query produced : " + sqlQuery);

	execute(sqlQuery, OrmActions.CREATE);
    }

    @Override
    public void updateUnique(AbstractOrmEntity searchEntity,
	    AbstractOrmEntity entityContainingChanges) throws OrmException
    {
	String sqlQuery = "UPDATE " + prefix
		+ entityContainingChanges.getSystemName() + " SET ";

	sqlQuery = buildSetClauseForQuery(entityContainingChanges.getFields(),
		sqlQuery);
	sqlQuery = buildWhereClauseForQuery(searchEntity, sqlQuery);

	// TODO: Remove this once it will be properly debugged
	System.out.println("Sql query produced : " + sqlQuery);

	execute(sqlQuery, OrmActions.UPDATE);
    }

    @Override
    public ResultSet select(AbstractOrmEntity searchEntity,
	    Vector<String> searchCriteriasParam, int limit, int offset,
	    String orderBy) throws OrmException
    {
	String sqlQuery = "SELECT * FROM " + prefix
		+ searchEntity.getSystemName();

	if (searchCriteriasParam != null)
	    sqlQuery += buildWhereClause(searchCriteriasParam);

	if (orderBy != null && orderBy.length() > 0)
	    sqlQuery += " ORDER BY " + orderBy;

	sqlQuery = sqlQuery + " LIMIT " + offset + ", " + limit + ";";

	// TODO: Remove the next line when it will be properly debugged
	System.out.println("SQL query produced : " + sqlQuery);

	ResultSet rs = execute(sqlQuery, OrmActions.SEARCH);

	return rs;
    }

    private static String buildWhereClause(Vector<String> searchCriteriasParam)
    {
	String whereClause = " WHERE ( ";

	// We add each string to the sqlQuery
	for (String parameter : searchCriteriasParam)
	    whereClause += parameter + " ";

	return whereClause + " )";
    }

    @Override
    public int count(AbstractOrmEntity searchEntity,
	    Vector<String> searchParameterList) throws Exception
    {
	// Pour une raison quelconque, la fonction COUNT de SQLite empêche la
	// transaction de se terminer
	// Alors on utilise un count fait à bras

	String sqlQuery = "SELECT " + searchEntity.getPrimaryKeyName()
		+ " FROM " + prefix + searchEntity.getSystemName();

	if (searchParameterList != null)
	    sqlQuery += buildWhereClause(searchParameterList);

	sqlQuery += ";";

	// TODO: Remove the next line when it will be properly debugged
	System.out.println("SQL query produced : " + sqlQuery);

	ResultSet rs = execute(sqlQuery, OrmActions.SEARCH);

	int count = 0;
	while (rs.next())
	    count++;

	return count;
    }

    @Override
    public void doBackup()
    {
	System.out.println("Backup de la DB");
	sgbdSqliteBackupManager.doBackup(dataBaseFileName, BackupManager
		.getMaximumBackupInstanceCount());
    }

    @Override
    public long getLatestBackupTime()
    {
	return sgbdSqliteBackupManager.getLatestBackupTime();
    }
}
