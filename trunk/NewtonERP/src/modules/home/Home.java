package modules.home;

import modules.home.actions.GetHome;
import newtonERP.module.Module;

/**
 * @author cloutierJo LemayPa module servant a généré les information de la page
 *         d'aceuil
 */
public class Home extends Module
{
    /**
     * constructeur
     */
    public Home()
    {
	super();
	setDefaultAction(new GetHome());
    }
}