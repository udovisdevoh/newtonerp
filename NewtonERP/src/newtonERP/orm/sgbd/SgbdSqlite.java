package newtonERP.orm.sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import newtonERP.logging.Logger;
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

		// TODO: Remove the next line when properly debugged
		System.out.println("Executed the select statement");

		return rs;
	    }

	    // Sinon peut importe l'action on retourne a rien!
	    stat.execute(request);

	    // TODO: Remove the next line when properly debugged
	    System.out.println("Executed the statement");

	    return null;

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
	    Logger.log("Attempting to close the connexion to db",
		    Logger.State.INFO);
	    connexion.close();
	    Logger.log("Connexion has been succesfully closed",
		    Logger.State.INFO);
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
	    Logger.log("Initialisation of connection to db", Logger.State.INFO);
	    Class.forName("org.sqlite.JDBC");
	    connexion = DriverManager.getConnection("jdbc:sqlite:test.db");
	    Logger.log("Connexion has been succesfully initialized",
		    Logger.State.INFO);
	} catch (Exception e)
	{
	    throw new OrmSqlException("Error connecting to the database : "
		    + e.getMessage());
	}
    }
}
