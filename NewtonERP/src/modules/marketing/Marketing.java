package modules.marketing;

import modules.marketing.entityDefinitions.Offer;
import modules.marketing.entityDefinitions.Promotion;
import modules.marketing.entityDefinitions.Sector;
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
		addGlobalActionMenuItem("Secteur", new BaseAction("GetList",
				new Sector()));
		addGlobalActionMenuItem("Offre", new BaseAction("GetList", new Offer()));
		setVisibleName("Publicité");

	}
}
