package newtonERP.orm.sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import newtonERP.logging.Logger;
import newtonERP.logging.Logger.State;

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

    @Override
    public Vector<String> Execute(String request)
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

	    // We execute the sql query produced by the ORM
	    stat.execute(request);

	    // TODO: For debug purposes, will have to be removed, maybe a log
	    // file instead
	    System.out.println("Executed");

	    /*
	     * TODO: For debug purposes, will eventually be removed ResultSet rs
	     * = stat.executeQuery("SELECT * FROM Employee;"); while (rs.next())
	     * { System.out.println("name = " + rs.getString("name"));
	     * System.out.println("age = " + rs.getString("age"));
	     * System.out.println("color = " + rs.getString("color")); }
	     * 
	     * rs.close();
	     */
	} catch (SQLException e)
	{
	    Logger.log(e.getMessage(), State.ERROR);
	}

	disconnectFromDb();

	// TODO Auto-generated method stub
	return null;
    }
}
