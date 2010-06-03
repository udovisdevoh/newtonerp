package modules.finances.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import modules.finances.entityDefinitions.BankAccount;
import modules.finances.entityDefinitions.PayableEmployee;
import modules.finances.entityDefinitions.PayablePeriod;
import modules.finances.entityDefinitions.StateType;
import modules.humanResources.actions.CalculatePeriodeSalary;
import modules.humanResources.entityDefinitions.Employee;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Action CreatePayableEmployee: Creer un entité PayableEmployee en fonction de
 * la période de paie actuelle.
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
		PayablePeriod searchCurrentPeriod = new PayablePeriod();
		searchCurrentPeriod.setData("isCurrentPeriod", true);
		AbstractOrmEntity period = Orm.selectUnique(searchCurrentPeriod);

		// création PayableEmployee champs vièrges sauf date:(période de
		// paie actuelle)
		PayableEmployee newEmployee = new PayableEmployee();

		newEmployee.setData(new Employee().getForeignKeyName(), emp
				.getPrimaryKeyValue());

		newEmployee.setData(new PayablePeriod().getForeignKeyName(), period
				.getPrimaryKeyValue());

		newEmployee.setData("paymentDate", new GregorianCalendar(0, 0, 0));

		newEmployee.setData("fedTax", 0.0);

		newEmployee.setData("provTax", 0.0);

		newEmployee.setData("gains", 0.0);

		newEmployee.setData("balance", 0.0);

		newEmployee.setData(new StateType().getForeignKeyName(), 1);

		newEmployee.setData(new BankAccount().getForeignKeyName(), 1);
		newEmployee.newE();
		// passé au ressources humaines pour setter "gains"
		new CalculatePeriodeSalary().doAction(newEmployee, null);
		return null;
	}
}
