package newtonERP;

import newtonERP.logging.Logger;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.serveur.Servlet;

import org.mortbay.jetty.Server;

/**
 * @author JoCloutier
 * 
 */
public class Starter
{
    private static Server server;

    /**
     * lance l'aplication et effectue toute les action d'initialisation
     * 
     * @param args aucun
     * @throws Exception -
     */
    public static void main(String[] args) throws Exception
    {
	// For testing
	// Vector<String> searchCriterias = new Vector<String>();
	// searchCriterias.add("name like '%Sylvain%'");
	// searchCriterias.add(" AND age like '%23%';");

	ListModule.initAllModule();

	try
	{
	    Orm.connect();

	    // Orm.insert(new TestEntity());
	    // Orm.select(new TestEntityModified(), searchCriterias);
	    // Orm.delete(new TestEntity(), searchCriterias);
	    // Orm.update(new TestEntity(), new TestEntityModified(),
	    // searchCriterias);
	} catch (OrmException e)
	{
	    Logger.log(e.getMessage(), Logger.State.ERROR);
	}

	// lance le serveur web
	server = new Server(47098);
	server.setGracefulShutdown(2000);
	server.setStopAtShutdown(true);
	server.setHandler(new Servlet());
	server.start();

	doWeExit();
    }

    /**
     * methode qui est apeller a la fermetur de l'aplication
     * 
     * @throws Exception -
     */
    public static void shutdown() throws Exception
    {
	server.stop();
    }

    /**
     * boucle qui demande si l'on ferme le serveur
     * 
     * @throws Exception
     */
    private static void doWeExit() throws Exception
    {
	boolean exit = false;
	while (!exit)
	{
	    System.out.println("voulez vous quitté (o)");
	    if (System.in.read() == 'o')
	    {
		System.out.println("fermeture en cour");
		exit = true;
		shutdown();
	    }
	}
    }
}