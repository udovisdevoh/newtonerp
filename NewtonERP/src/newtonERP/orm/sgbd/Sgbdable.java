package newtonERP.orm.sgbd;

import java.sql.ResultSet;

import newtonERP.orm.OrmActions;
import newtonERP.orm.exceptions.OrmException;

/**
 * Interface with methods for executing the requests to be implemented in the
 * sgdb. Useful if for something we change lets say from Sqlite to Oracle
 * 
 * @author r3lacasgu, r3hallejo
 */
public interface Sgbdable
{
    /**
     * Method that executes the sql query passed in paramter plus de action from
     * the OrmAction
     * 
     * @param request l'action a effectue
     * @param action the OrmActions that will be done
     * @return le resultat sous forme d'un result set
     * @throws OrmException an exception that can occur in the orm
     */
    public ResultSet execute(String request, OrmActions action)
	    throws OrmException;

    /**
     * Method used to initialize the connection to the databse
     * 
     * @throws OrmException an exception that can occur in the orm
     */
    public void connect() throws OrmException;

    /**
     * Method used to disconnect from the database
     * 
     * @throws OrmException an exception that can occur in the orm
     */
    public void disconnect() throws OrmException;
}
