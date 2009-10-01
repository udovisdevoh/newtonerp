package newtonERP;

import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.serveur.Servlet;
import newtonERP.logging.*;

/**
 * @author JoCloutier
 * 
 */
public class Starter
{
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
	} catch (OrmException e)
	{
	    Logger.log(e.getMessage(), Logger.State.ERROR);
	}

    }
}
