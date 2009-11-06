package modules.production;

import java.util.GregorianCalendar;

import modules.humanResources.entityDefinitions.Employee;
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
    }

    public void initDB() throws Exception
    {
	GregorianCalendar cd = new GregorianCalendar();
	cd.add(GregorianCalendar.HOUR, 1);

	super.initDB();

	StatusType status = new StatusType();
	status.setData("status", "Assigne");
	status.newE();

	StatusType status1 = new StatusType();
	status1.setData("status", "En cours");
	status1.newE();

	StatusType status2 = new StatusType();
	status2.setData("status", "Resolu");
	status2.newE();

	StatusType status3 = new StatusType();
	status3.setData("status", "Non assigne");
	status3.newE();

	MaintenanceTicket ticket = new MaintenanceTicket();
	ticket.setData("problemType",
		"Presse1, cylindre hydraulique gauche fuit");
	ticket.setData(new Employee().getForeignKeyName(), 1);
	ticket.setData("startDate", new GregorianCalendar());
	ticket.setData("endDate", cd);
	ticket.setData(new StatusType().getForeignKeyName(), 3);
	ticket.setData("comment", "");
	ticket.newE();
    }
}
