package newtonERP.orm.sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

import newtonERP.logging.Logger;

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

    /**
     * Method used to initialize the connection to the database. Sends a message
     * to the logger if the connexion has been succesful else it sends exception
     * to the logger,
     */
    private static void initializeConnectionToDb()
    {
	try
	{
	    Logger.log("Initialion of connection to db", Logger.State.INFO);
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
    private static void disconnectFromDb()
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
	// TODO Auto-generated method stub
	return null;
    }
}
