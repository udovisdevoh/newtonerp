package modules.home;

import modules.home.actions.GetHome;
import newtonERP.module.Module;

/**
 * module servant a générer les information de la page d'aceuil
 * @author cloutierJo LemayPa
 */
public class Home extends Module
{
    /**
     * constructeur
     * @throws Exception remonte
     */
    public Home() throws Exception
    {
	super();
	setDefaultAction(new GetHome());
    }
}