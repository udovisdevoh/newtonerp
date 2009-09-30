package newtonERP.orm.sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import newtonERP.logging.Logger;
import newtonERP.logging.Logger.State;
import newtonERP.orm.OrmActions;

/**
 * 
 * @author r3lacasgu, r3hallejo
 * 
 *         Class who's role is only for executing the statements that has been
 *         sent from the orm
 */
public class SgbdSqlite implements Sgbdable
{
    private Connection connexion;

    /**
     * Method used to initialize the connection to the database. Sends a message
     * to the logger if the connexion has been succesful else it sends exception
     * to the logger,
     */
    private void initializeConnectionToDb()
    {
	try
	{
	    Logger.log("Initialisation of connection to db", Logger.State.INFO);
	    Class.forName("org.sqlite.JDBC");
	    connexion = DriverManager.getConnection("jdbc:sqlite:test.db");
	    Logger.log("Connexion has been succesfully initialized",
		    Logger.State.INFO);
	} catch (Exception ex)
	{
	    Logger.log(ex.getMessage(), Logger.State.ERROR);
	}
    }

    /**
     * Method used to disconnect from the database. Sends a message to the
     * logger if succesful else it sends the exception to the logger.
     */
    private void disconnectFromDb()
    {
	try
	{
	    Logger.log("Attempting to close the connexion to db",
		    Logger.State.INFO);
	    connexion.close();
	    Logger.log("Connexion has been succesfully closed",
		    Logger.State.INFO);
	} catch (Exception ex)
	{
	    Logger.log(ex.getMessage(), Logger.State.ERROR);
	}
    }

    public ResultSet Execute(String request, OrmActions action)
    {
	initializeConnectionToDb();

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

		// TODO: Remove the next lines when properly debugged
		while (rs.next())
		{
		    System.out.println("name = " + rs.getString("name"));
		    System.out.println("age = " + rs.getString("age"));
		    System.out.println("color = " + rs.getString("color"));
		}

		rs.close();

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
		// To be implemented
	    }
	    else if (action.equals(OrmActions.DELETE))
	    {
		// To be implemented
	    }
	} catch (SQLException e)
	{
	    Logger.log(e.getMessage(), State.ERROR);
	}

	disconnectFromDb();

	// TODO Auto-generated method stub
	return null;
    }
}
