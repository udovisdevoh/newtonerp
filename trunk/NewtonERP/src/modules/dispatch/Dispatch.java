package modules.dispatch;

import modules.dispatch.entityDefinitions.ClientType;
import modules.dispatch.entityDefinitions.CommandeEtat;
import modules.dispatch.entityDefinitions.LivraisonEtat;
import modules.dispatch.entityDefinitions.Secteur;
import newtonERP.module.Module;

/**
 * Dispatch
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Dispatch extends Module
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Dispatch() throws Exception
    {
	super();
	setVisibleName("Dispatch");
    }

    public void initDB() throws Exception
    {
	super.initDB();

	initClientTypes();
	initPriorityTypes();
	initOrderStatusTypes();
	initSectors();
    }

    private void initClientTypes() throws Exception
    {
	ClientType clientType;
	clientType = new ClientType();
	clientType.setData("Nom", "Charge");
	clientType.newE();

	clientType = new ClientType();
	clientType.setData("Nom", "Cash");
	clientType.newE();
    }

    private void initSectors() throws Exception
    {
	Secteur secteur;

	secteur = new Secteur();
	secteur.setData("Nom", "Est");
	secteur.newE();

	secteur = new Secteur();
	secteur.setData("Nom", "Ouest");
	secteur.newE();

	secteur = new Secteur();
	secteur.setData("Nom", "Nord");
	secteur.newE();

	secteur = new Secteur();
	secteur.setData("Nom", "Sud");
	secteur.newE();
    }

    private void initOrderStatusTypes() throws Exception
    {
	CommandeEtat normal = new CommandeEtat();
	normal.setData("Description", "Normal");
	normal.newE();

	CommandeEtat rush = new CommandeEtat();
	rush.setData("Description", "Rush");
	rush.newE();

	CommandeEtat aCommander = new CommandeEtat();
	aCommander.setData("Description", "À commander");
	aCommander.newE();

	CommandeEtat commandee = new CommandeEtat();
	commandee.setData("Description", "Commandée");
	commandee.newE();

	CommandeEtat recue = new CommandeEtat();
	recue.setData("Description", "Reçue");
	recue.newE();

	CommandeEtat finalisee = new CommandeEtat();
	finalisee.setData("Description", "Finalisée");
	finalisee.newE();
    }

    private void initPriorityTypes() throws Exception
    {
	LivraisonEtat normal = new LivraisonEtat();
	normal.setData("Description", "Normal");
	normal.newE();

	LivraisonEtat rush = new LivraisonEtat();
	rush.setData("Description", "Rush");
	rush.newE();

	LivraisonEtat enPreparation = new LivraisonEtat();
	enPreparation.setData("Description", "En préparation");
	enPreparation.newE();

	LivraisonEtat prete = new LivraisonEtat();
	prete.setData("Description", "Prête");
	prete.newE();

	LivraisonEtat prise = new LivraisonEtat();
	prise.setData("Description", "Prise");
	prise.newE();

	LivraisonEtat finalisee = new LivraisonEtat();
	finalisee.setData("Description", "Finalisée");
	finalisee.newE();
    }
}
