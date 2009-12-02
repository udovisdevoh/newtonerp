package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import modules.finances.entityDefinitions.BankAccount;
import modules.finances.entityDefinitions.PayableEmployee;
import modules.finances.entityDefinitions.StateType;
import modules.humanResources.entityDefinitions.Employee;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

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
public class CreatePayableEmployee extends AbstractAction
{
    /**
     * constructeur
     * @throws Exception si création fail
     */
    public CreatePayableEmployee() throws Exception
    {
	super(new Employee());
    }

    // noms de clés du hashtable pas déterminés, à voir avec jo.C
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Employee emp = (Employee) entity;

	/*
	 * PayableEmployee searchEmp = new PayableEmployee();
	 * searchEmp.setData(emp.getForeignKeyName(), emp.getData(emp
	 * .getForeignKeyName())); AbstractOrmEntity employee =
	 * Orm.selectUnique(searchEmp);
	 */

	// keys pas encore déterminées, à voir avec jo.C
	// je doit recevoir en param date en string comme:Ex:2001-01-01
	String[] dateBegin = parameters.get("key?").split("-");
	String[] dateEnd = parameters.get("key?").split("-");

	/*
	 * if (employee != null)// update { employee.setData("beginning", new
	 * GregorianCalendar(Integer .parseInt(dateBegin[0]),
	 * Integer.parseInt(dateBegin[1]) - 1, Integer
	 * .parseInt(dateBegin[2])));
	 * 
	 * employee.setData("end", new GregorianCalendar(Integer
	 * .parseInt(dateEnd[0]), Integer.parseInt(dateEnd[1]) - 1,
	 * Integer.parseInt(dateEnd[2])));
	 * 
	 * employee.setData("paymentDate", new GregorianCalendar(0, 0, 0));
	 * 
	 * employee.setData("gains", Double
	 * .parseDouble(parameters.get("key?")));
	 * 
	 * employee.setData("balance", Double.parseDouble(parameters
	 * .get("key?")));
	 * 
	 * employee.setData(new StateType().getForeignKeyName(), 1);
	 * employee.save(); } else // create
	 */
	{
	    PayableEmployee newEmployee = new PayableEmployee();

	    newEmployee.setData(new Employee().getForeignKeyName(), emp
		    .getPrimaryKeyValue());

	    newEmployee.setData("beginning", new GregorianCalendar(Integer
		    .parseInt(dateBegin[0]),
		    Integer.parseInt(dateBegin[1]) - 1, Integer
			    .parseInt(dateBegin[2])));

	    newEmployee.setData("end", new GregorianCalendar(Integer
		    .parseInt(dateEnd[0]), Integer.parseInt(dateEnd[1]) - 1,
		    Integer.parseInt(dateEnd[2])));

	    newEmployee.setData("paymentDate", new GregorianCalendar(0, 0, 0));

	    newEmployee.setData("fedTax", 0.0);

	    newEmployee.setData("provTax", 0.0);

	    newEmployee.setData("gains", 0.0);

	    newEmployee.setData("balance", 0.0);

	    newEmployee.setData(new StateType().getForeignKeyName(), 1);

	    newEmployee.setData(new BankAccount().getForeignKeyName(), 1);
	    newEmployee.newE();
	    // new CalculatePeriodeSalary().doAction(newEmployee, null);
	}
	return null;
    }
}
