package modules.production;

import java.util.GregorianCalendar;

import modules.humanResources.entityDefinitions.Employee;
import modules.production.entityDefinitions.Machine;
import modules.production.entityDefinitions.MachineDimension;
import modules.production.entityDefinitions.MachineStatus;
import modules.production.entityDefinitions.MaintenanceStatusType;
import modules.production.entityDefinitions.MaintenanceTicket;
import modules.production.entityDefinitions.Project;
import modules.production.entityDefinitions.ProjectType;
import modules.production.entityDefinitions.Training;
import modules.production.entityDefinitions.TrainingType;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * Module used for the production / maintenance of the warehouse
 * 
 * @author r3hallejo
 */
public class Production extends Module
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public Production() throws Exception
    {
	super();
	setDefaultAction(new BaseAction("GetList", new MaintenanceTicket()));
	addGlobalActionMenuItem("Tickets de maintenance", new BaseAction(
		"GetList", new MaintenanceTicket()));
	addGlobalActionMenuItem("Status", new BaseAction("GetList",
		new MaintenanceStatusType()));
	addGlobalActionMenuItem("Machines", new BaseAction("GetList",
		new Machine()));
	addGlobalActionMenuItem("Dimensions", new BaseAction("GetList",
		new MachineDimension()));
	addGlobalActionMenuItem("Status de machines", new BaseAction("GetList",
		new MachineStatus()));
	addGlobalActionMenuItem("Projets", new BaseAction("GetList",
		new Project()));
	addGlobalActionMenuItem("Types de projets", new BaseAction("GetList",
		new ProjectType()));
	addGlobalActionMenuItem("Formations", new BaseAction("GetList",
		new Training()));
	addGlobalActionMenuItem("Types de formations", new BaseAction(
		"GetList", new TrainingType()));
    }

    @SuppressWarnings("static-access")
    public void initDB() throws Exception
    {
	GregorianCalendar cd = new GregorianCalendar();
	cd.add(GregorianCalendar.HOUR, 1);

	super.initDB();

	MachineStatus status4 = new MachineStatus();
	status4.setData("status", "Brisée");
	status4.newE();

	MachineStatus status5 = new MachineStatus();
	status5.setData("status", "En production");
	status5.newE();

	MachineDimension dimension = new MachineDimension();
	dimension.setData("width", 42.25);
	dimension.setData("height", 24.50);
	dimension.setData("depth", 96);
	dimension.setData("weight", 2148);
	dimension.setData("unitTypes", "Pouces et Kg");
	dimension.newE();

	Machine machine = new Machine();
	machine.setData("name", "Presse 1");
	machine.setData("entryDate", new GregorianCalendar());
	machine.setData(new MachineStatus().getForeignKeyName(), 1);
	machine.setData(new MachineDimension().getForeignKeyName(), 1);
	machine
		.setData("function",
			"Compresser les feuilles sortie de la cuve");
	machine.newE();

	MaintenanceStatusType status3 = new MaintenanceStatusType();
	status3.setData("status", "Non assigne");
	status3.newE();

	MaintenanceStatusType status = new MaintenanceStatusType();
	status.setData("status", "Assigne");
	status.newE();

	MaintenanceStatusType status1 = new MaintenanceStatusType();
	status1.setData("status", "En cours");
	status1.newE();

	MaintenanceStatusType status2 = new MaintenanceStatusType();
	status2.setData("status", "Resolu");
	status2.newE();

	MaintenanceTicket ticket = new MaintenanceTicket();
	ticket.setData("problemType", "Cylindre hydraulique gauche fuit");
	ticket.setData(new Employee().getForeignKeyName(), 1);
	ticket.setData(new Machine().getForeignKeyName(), 1);
	ticket.setData("startDate", new GregorianCalendar());
	ticket.setData("endDate", cd);
	ticket.setData(new MaintenanceStatusType().getForeignKeyName(), 3);
	ticket.setData("comment", "");
	ticket.newE();

	ProjectType type = new ProjectType();
	type.setData("type", "Ajout");
	type.newE();

	ProjectType type1 = new ProjectType();
	type1.setData("type", "Mise à jour");
	type1.newE();

	ProjectType type2 = new ProjectType();
	type2.setData("type", "Suppression");
	type2.newE();

	TrainingType type3 = new TrainingType();
	type3.setData("name", "Machineries");
	type3.newE();

	TrainingType type4 = new TrainingType();
	type4.setData("name", "Logiciel");
	type4.newE();
    }
}
