package newtonERP.serveur;

import java.util.Hashtable;

/**
 * @author Gabriel Therrien
 * 
 */
public class Servlet
{
    public Servlet()
    {
	CommandRouteur theCommandRouteur = new CommandRouteur();
    }

    public Hashtable<String, String> setCommandRouteurModule(
	    Hashtable<String, String> allModules)
    {
	return allModules;
    }
}
