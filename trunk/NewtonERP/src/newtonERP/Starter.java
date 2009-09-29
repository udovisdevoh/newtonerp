package newtonERP;

import newtonERP.serveur.Servlet;

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
	ListModule.initAllModule();
	new Servlet(47098);
    }
}
