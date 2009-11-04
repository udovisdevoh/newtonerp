package modules.finances;

import modules.finances.entityDefinitions.CompteFournisseur;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * Représente le module finances
 * @author r3lemaypa
 */
public class Finances extends Module
{
    /**
     * constructeur
     * @throws Exception remonte
     */
    public Finances() throws Exception
    {
	super();

	setDefaultAction(new BaseAction("GetList", new CompteFournisseur()));

	addGlobalActionMenuItem("Comptes Fournisseurs", new BaseAction(
		"GetList", new CompteFournisseur()));

	//
	setVisibleName("Finances");

    }

}
