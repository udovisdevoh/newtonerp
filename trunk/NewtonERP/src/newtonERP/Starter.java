package newtonERP;

import java.net.BindException;

import modules.userRightModule.actions.CreateAllRight;
import newtonERP.common.ListModule;
import newtonERP.logging.Logger;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.serveur.ConfigManager;
import newtonERP.serveur.Servlet;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;

/**
 * @author JoCloutier
 * 
 *         Program starter
 */
public class Starter {
    private static Server server;

    /**
     * lance l'aplication et effectue toute les action d'initialisation
     * 
     * @param args aucun
     * @throws Exception hein ouai c sa...
     */
    public static void main(String[] args) throws Exception {
        ListModule.initAllModule();
        startOrm();
        startWebWerver();
    }

    /**
     * @throws Exception en cas de probleme avec le serveur
     */
    private static void startWebWerver() throws Exception {
        server = new Server(ConfigManager.loadIntProperty("port", 47098));
        server.setGracefulShutdown(2000);
        server.setStopAtShutdown(true);
        Context context = new Context(server, "/", Context.SESSIONS);
        context.setServletHandler(new Servlet());

        try {
            server.start();
            doWeExit();
        } catch (BindException e) {
            Logger.error("      *****serveur déja partie********");
            shutdown();
        }
    }

    /**
     * il ne sera pas nescessaire d'initialisé l'orm lorsqu'il deviendra multi
     * instance puisque chaqu'une d'elle devra s'auto initialisé
     */
    @Deprecated
    private static void startOrm() {
        try {
            Orm.getInstance();
        } catch (OrmException e) {
            e.printStackTrace();
        }

        new CreateAllRight().perform(null);
    }

    /**
     * methode qui est apeller a la fermetur de l'aplication
     * @throws Exception en cas de probleme avec le serveur
     * 
     */
    public static void shutdown() throws Exception {
        server.stop();
        Logger.close();
    }

    /**
     * boucle qui demande si l'on ferme le serveur
     * @throws Exception en cas de probleme avec le serveur
     * 
     */
    private static void doWeExit() throws Exception {
        boolean exit = false;
        while (!exit) {
            System.out.println("Voulez-vous quitter? (o)");
            if (System.in.read() == 'o') {
                exit = true;
                shutdown();
            }
        }
    }
}
