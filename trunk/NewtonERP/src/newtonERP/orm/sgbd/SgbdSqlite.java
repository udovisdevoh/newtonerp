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
 * 
 * @author r3lacasgu, r3hallejo
 * 
 *         Class who's role is only for executing the statements that has been
 *         sent from the orm
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

	    // TODO: Remove this once we will have our tables created
	    // automatically by the orm
	    // stat.execute("DROP TABLE IF EXISTS Employee");

	    // TODO: Remove this once the table is automatically created
	    // stat
	    // .execute("CREATE TABLE Employee ( name VARCHAR(30), age VARCHAR(30), color VARCHAR(30))");

	    if (action.equals(OrmActions.SEARCH))
	    {
		ResultSet rs = stat.executeQuery(request);

		// TODO: Remove the next line when properly debugged
		System.out.println("Executed the select statement");

		return rs;
	    }
	    else if (action.equals(OrmActions.INSERT))
	    {
		stat.execute(request);

		// TODO: Remove the next line when properly debugged
		System.out.println("Executed the insert statement");

		return null;
	    }
	    else if (action.equals(OrmActions.UPDATE))
	    {
		stat.execute(request);

		// TODO: Remove the next line when properly debugged
		System.out.println("Executed the update statement");

		return null;
	    }
	    else if (action.equals(OrmActions.DELETE))
	    {
		stat.execute(request);

		// TODO: Remove the next line when properly debugged
		System.out.println("Executed the delete statement");

		return null;
	    }
	} catch (SQLException e)
	{
	    throw new OrmSqlException(e.getMessage());
	}
	return null;
    }

    @Override
    public void connect() throws OrmException
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
	    throw new OrmSqlException(e.getMessage());
	}
    }

    @Override
    public void disconnect() throws OrmException
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
	    throw new OrmSqlException(e.getMessage());
	}
    }
}
