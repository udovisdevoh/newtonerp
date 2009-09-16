package newtonERP.orm;

import java.sql.Connection;
import java.sql.DriverManager;

import newtonERP.logging.Logger;

/**
 * 
 * @author r3hallejo
 * 
 * Basic class for the orm. It is used to put the objects in the databse using SqLite3 and it's java binding.
 * In general what it's gonna do else than initializing the connection to the database or closing it, the orm
 * will receive an entity from which the orm will perform various tasks.
 */
public class Orm {
	private static Connection connexion;
	
	/**
	 * Method used to initialize the connection to the database. Sends a message to the logger if the connexion
	 * has been succesful else it sends exception to the logger,
	 */
	public static void initializeConnectionToDb () {
		try {
			Logger.log("Initialion of connection to db", Logger.State.INFO);
			Class.forName("org.sqlite.JDBC");
			connexion = DriverManager.getConnection("jdbc:sqlite:test.db");
			Logger.log("Connexion has been succesfully initialized", Logger.State.INFO);
		} catch (Exception ex) {
			Logger.log(ex.getMessage(), Logger.State.ERROR);
		}
	}
	
	/**
	 * Method used to disconnect from the database. Sends a message to the logger if succesful else it sends the exception 
	 * to the logger.
	 */
	public static void disconnectFromDb () {
		try {
			Logger.log("Attempting to close the connexion to db", Logger.State.INFO);
			connexion.close();
			Logger.log("Connexion has been succesfully closed", Logger.State.INFO);
		}
		catch (Exception ex) {
			Logger.log(ex.getMessage(), Logger.State.ERROR);
		}
	}
}
