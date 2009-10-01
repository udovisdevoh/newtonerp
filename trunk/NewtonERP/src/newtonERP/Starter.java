package newtonERP;

import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.serveur.Servlet;

/**
 * @author JoCloutier
 * 
 */
public class Starter
{
    private static Orm orm;

    /**
     * lance l'aplication et effectue toute les action d'initialisation
     * 
     * @param args aucun
     */
    public static void main(String[] args)
    {
	// ListModule.initAllModule();
	new Servlet(47098);

	try
	{
	    Orm.initializeConnectionToDb();
	    Orm.disconnectFromDb();
	} catch (OrmException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
