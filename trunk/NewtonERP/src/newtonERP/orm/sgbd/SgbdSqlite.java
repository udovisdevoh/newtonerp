package newtonERP.orm.sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import newtonERP.orm.OrmActions;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.orm.exceptions.OrmSqlException;

/**
 * Class who's role is only for executing the statements that has been sent from
 * the orm
 * 
 * @author r3lacasgu, r3hallejo
 */
public class SgbdSqlite implements Sgbdable
{
    private static Connection connexion;

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

	    // Sinon peut importe l'action on retourne a rien!
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
	    connexion = DriverManager.getConnection("jdbc:sqlite:test.db");
	} catch (Exception e)
	{
	    throw new OrmSqlException("Error connecting to the database : "
		    + e.getMessage());
	}
    }
}
