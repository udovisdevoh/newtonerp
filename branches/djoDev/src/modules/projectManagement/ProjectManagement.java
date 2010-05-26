package modules.projectManagement;

import modules.projectManagement.entityDefinitions.Project;
import modules.projectManagement.entityDefinitions.ProjectType;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * Project Management class
 * 
 * @author r3hallejo
 */
public class ProjectManagement extends Module
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public ProjectManagement() throws Exception
    {
	super();
	setVisibleName("Gestion de projets");
	setDefaultAction(new BaseAction("GetList", new Project()));
	addGlobalActionMenuItem("Projets", new BaseAction("GetList",
		new Project()));
	addGlobalActionMenuItem("Type de projets", new BaseAction("GetList",
		new ProjectType()));
	setVisible(false);
    }

    public void initDB() throws Exception
    {
	super.initDB();

	ProjectType type = new ProjectType();
	type.setData("type", "Migration");
	type.newE();

	ProjectType type1 = new ProjectType();
	type1.setData("type", "Implémentation");
	type1.newE();

	ProjectType type2 = new ProjectType();
	type2.setData("type", "Mise à jour");
	type2.newE();

	ProjectType type3 = new ProjectType();
	type3.setData("type", "Autre");
	type3.newE();
    }

}
