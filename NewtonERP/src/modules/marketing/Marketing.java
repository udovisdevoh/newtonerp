package modules.marketing;

import modules.marketing.entityDefinitions.Promotion;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * Module de gestion des ressources publicitaires
 * @author Gabriel Therrien
 */
public class Marketing extends Module
{

    /**
     * @throws Exception si le contructeur ne fontionne pas
     */
    public Marketing() throws Exception
    {
	super();
	setDefaultAction(new BaseAction("GetList", new Promotion()));
	addGlobalActionMenuItem("Promotion", new BaseAction("GetList",
		new Promotion()));
	setVisibleName("Publicité");
    }
}
