package newtonERP;

import java.net.BindException;

import modules.userRightModule.actions.CreateAllRight;
import newtonERP.common.ListModule;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.serveur.Servlet;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;

/**
 * @author JoCloutier
 * 
 *         Program starter
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
	ListModule.initAllModule();

	try
	{
	    Orm.connect();
	    Orm.createNonExistentTables();
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}

	new CreateAllRight().perform(null);

	// lance le serveur web
	server = new Server(47098);
	server.setGracefulShutdown(2000);
	server.setStopAtShutdown(true);
	Context context = new Context(server, "/", Context.SESSIONS);
	context.setServletHandler(new Servlet());

	try
	{
	    server.start();
	    doWeExit();
	} catch (BindException e)
	{
	    System.err.println("      *****serveur déja partie********");
	    shutdown();
	}
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
	    System.out.println("Voulez-vous quitter? (o)");
	    if (System.in.read() == 'o')
	    {
		exit = true;
		shutdown();
	    }
	}
    }
}
