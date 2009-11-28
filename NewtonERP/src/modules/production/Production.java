package modules.production;

import java.util.GregorianCalendar;

import modules.humanResources.entityDefinitions.Employee;
import modules.production.actions.GetEquipmentSchedule;
import modules.production.entityDefinitions.Equipment;
import modules.production.entityDefinitions.EquipmentPeriodType;
import modules.production.entityDefinitions.EquipmentSchedule;
import modules.production.entityDefinitions.EquipmentType;
import modules.production.entityDefinitions.Machine;
import modules.production.entityDefinitions.MachineDimension;
import modules.production.entityDefinitions.MachineStatus;
import modules.production.entityDefinitions.MaintenanceStatusType;
import modules.production.entityDefinitions.MaintenanceTicket;
import modules.production.entityDefinitions.ProductionProject;
import modules.production.entityDefinitions.ProductionProjectType;
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
		new ProductionProject()));
	addGlobalActionMenuItem("Types de projets", new BaseAction("GetList",
		new ProductionProjectType()));
	addGlobalActionMenuItem("Formations", new BaseAction("GetList",
		new Training()));
	addGlobalActionMenuItem("Types de formations", new BaseAction(
		"GetList", new TrainingType()));
	addGlobalActionMenuItem("Équipements", new BaseAction("GetList",
		new Equipment()));
	addGlobalActionMenuItem("Type d'équipements", new BaseAction("GetList",
		new EquipmentType()));
	addGlobalActionMenuItem("Horaire d'équipements", new BaseAction(
		"GetList", new EquipmentSchedule()));
	addGlobalActionMenuItem("Type de période d'équipements",
		new BaseAction("GetList", new EquipmentPeriodType()));
	addGlobalActionMenuItem("Voir l'horaire", new GetEquipmentSchedule());
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

	ProductionProjectType type5 = new ProductionProjectType();
	type5.setData("type", "");
	type5.newE();

	ProductionProjectType type = new ProductionProjectType();
	type.setData("type", "Ajout");
	type.newE();

	ProductionProjectType type1 = new ProductionProjectType();
	type1.setData("type", "Mise à jour");
	type1.newE();

	ProductionProjectType type2 = new ProductionProjectType();
	type2.setData("type", "Suppression");
	type2.newE();

	TrainingType type3 = new TrainingType();
	type3.setData("name", "Machineries");
	type3.newE();

	TrainingType type4 = new TrainingType();
	type4.setData("name", "Logiciel");
	type4.newE();

	ProductionProject projet1 = new ProductionProject();
	projet1.setData("projectName", "Nous");
	projet1.setData(new ProductionProjectType().getForeignKeyName(), 1);
	projet1.setData("description", "");
	projet1.newE();

	ProductionProject projet = new ProductionProject();
	projet.setData("projectName", "Ajout ligne production 2");
	projet.setData(new ProductionProjectType().getForeignKeyName(), 1);
	projet
		.setData(
			"description",
			"Projet visant à ajouter à notre usine de montréal une deuxième ligne de production");
	projet.newE();

	Training formation = new Training();
	formation.setData(new ProductionProject().getForeignKeyName(), 1);
	formation.setData(new TrainingType().getForeignKeyName(), 1);
	formation.setData("description", "Formation requise pour la presse 2");
	formation.newE();

	EquipmentType typeEquipement = new EquipmentType();
	typeEquipement.setData("name", "Outils");
	typeEquipement.newE();

	EquipmentPeriodType periode = new EquipmentPeriodType();
	periode.setData("periodType", "Réservé");
	periode.newE();

	Equipment equipement = new Equipment();
	equipement.setData("name", "Chariot Élévateur");
	equipement.assign(typeEquipement);
	equipement.newE();
    }
}
