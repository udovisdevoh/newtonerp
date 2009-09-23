package newtonERP.orm.sgbd;

import java.util.Vector;

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
    public Vector<String> Execute(String request);
}
