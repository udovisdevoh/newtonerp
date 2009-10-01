package newtonERP.orm.sgbd;

import java.sql.ResultSet;

import newtonERP.orm.OrmActions;

/**
 * 
 * @author r3lacasgu, r3hallejo
 * 
 *         Interface with methods for executing the requests to be implemented
 *         in the sgdb. Useful if for something we change lets say from Sqlite
 *         to Oracle
 */
public interface Sgbdable
{
    /**
     * Method that executes the sql query passed in paramter plus de action from
     * the OrmAction
     * 
     * @param request l'action a effectue
     * @param action the OrmActions that will be done
     * @return le resultat sous forme de strings
     */
    public ResultSet Execute(String request, OrmActions action);
}
