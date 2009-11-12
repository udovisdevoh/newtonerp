package modules.production;

import java.util.GregorianCalendar;

import modules.humanResources.entityDefinitions.Employee;
import modules.production.entityDefinitions.Machine;
import modules.production.entityDefinitions.MachineDimension;
import modules.production.entityDefinitions.MachineStatus;
import modules.production.entityDefinitions.MaintenanceTicket;
import modules.production.entityDefinitions.StatusType;
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
		new StatusType()));
	addGlobalActionMenuItem("Machines", new BaseAction("GetList",
		new Machine()));
	addGlobalActionMenuItem("Dimensions", new BaseAction("GetList",
		new MachineDimension()));
	addGlobalActionMenuItem("Status de machines", new BaseAction("GetList",
		new MachineStatus()));
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

	StatusType status3 = new StatusType();
	status3.setData("status", "Non assigne");
	status3.newE();

	StatusType status = new StatusType();
	status.setData("status", "Assigne");
	status.newE();

	StatusType status1 = new StatusType();
	status1.setData("status", "En cours");
	status1.newE();

	StatusType status2 = new StatusType();
	status2.setData("status", "Resolu");
	status2.newE();

	MaintenanceTicket ticket = new MaintenanceTicket();
	ticket.setData("problemType", "Cylindre hydraulique gauche fuit");
	ticket.setData(new Employee().getForeignKeyName(), 1);
	ticket.setData(new Machine().getForeignKeyName(), 1);
	ticket.setData("startDate", new GregorianCalendar());
	ticket.setData("endDate", cd);
	ticket.setData(new StatusType().getForeignKeyName(), 3);
	ticket.setData("comment", "");
	ticket.newE();
    }
}
