package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import modules.finances.entityDefinitions.BankAccount;
import modules.finances.entityDefinitions.PayableEmployee;
import modules.finances.entityDefinitions.StateType;
import modules.humanResources.entityDefinitions.Employee;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Action CreateUpdatePayableEmployee: Update les valeurs en fonction de la
 * période de paye actuelle.
 * 
 * Cette action sert également à ajouter un "PayableEmployee" si il n'existe pas
 * encore.(nouvellement engagé)
 * 
 * ps trucs à voir avec jo.C
 * @author Pascal Lemay
 */
public class CreateUpdatePayableEmployee extends AbstractAction
{
    /**
     * constructeur
     * @throws Exception si création fail
     */
    public CreateUpdatePayableEmployee() throws Exception
    {
	super(null);
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Employee emp = (Employee) entity;
	PayableEmployee newEmployee;

	PayableEmployee searchEmp = new PayableEmployee();
	searchEmp.setData(emp.getForeignKeyName(), emp.getData(emp
		.getForeignKeyName()));
	AbstractOrmEntity employee = Orm.selectUnique(searchEmp);

	if (employee != null)// update
	{
	    employee.setData("begin", new GregorianCalendar());// parameters.get("key?");
	    employee.setData("end", new GregorianCalendar());// parameters.get("key?");
	    employee.setData("paymentDate", new GregorianCalendar());
	    employee.setData("balance", Double.parseDouble(parameters
		    .get("key?")));
	    employee.setData(new StateType().getForeignKeyName(), 1);
	    employee.save();
	}
	else
	// create
	{
	    newEmployee = new PayableEmployee();
	    newEmployee.setData(new Employee().getForeignKeyName(), emp
		    .getPrimaryKeyValue());
	    newEmployee.setData("begin", new GregorianCalendar());// parameters.get("key?");
	    newEmployee.setData("end", new GregorianCalendar());// parameters.get("key?");
	    newEmployee.setData("paymentDate", new GregorianCalendar());
	    newEmployee.setData("balance", Double.parseDouble(parameters
		    .get("key?")));
	    newEmployee.setData(new StateType().getForeignKeyName(), 1);
	    newEmployee.setData(new BankAccount().getForeignKeyName(), 1);

	    newEmployee.newE();
	}

	return null;
    }
}
