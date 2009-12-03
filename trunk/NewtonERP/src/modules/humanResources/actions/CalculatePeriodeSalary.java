package modules.humanResources.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.finances.entityDefinitions.PayableEmployee;
import modules.finances.entityDefinitions.PayablePeriod;
import modules.humanResources.entityDefinitions.Employee;
import modules.humanResources.entityDefinitions.Punch;
import modules.humanResources.entityDefinitions.SalaryType;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;

/**
 * On cré un nouveau user pour un employé et on lui assigne
 * @author Guillaume Lacasse
 */
public class CalculatePeriodeSalary extends AbstractAction
{
    /**
     * @throws Exception si création fail
     */
    public CalculatePeriodeSalary() throws Exception
    {
	super(new PayableEmployee());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	PayableEmployee payEmp = (PayableEmployee) entity;
	PayablePeriod payPer = (PayablePeriod) payEmp
		.getSingleAccessor(new PayablePeriod().getForeignKeyName());
	Employee emp = (Employee) payEmp.getSingleAccessor(new Employee()
		.getForeignKeyName());

	String salaryType = (String) emp.getSingleAccessor(
		new SalaryType().getForeignKeyName()).getData("SalaryType");
	double salaire = (Double) emp.getData("salary");
	double gains = 0;
	if (salaryType.equals("horaire"))
	{
	    Vector<AbstractOrmEntity> search = new Vector<AbstractOrmEntity>();
	    Punch punchSearch = new Punch();
	    punchSearch.setData("in", payPer.getData("beginning"));
	    search.add(punchSearch);
	    punchSearch = new Punch();
	    punchSearch.setData("in", payPer.getData("end"));
	    search.add(punchSearch);

	    Vector<AbstractOrmEntity> punchs = punchSearch.get(punchSearch);
	    double totalTime = 0;
	    for (AbstractOrmEntity punch : punchs)
	    {
		totalTime += (Double) punch.getData("diff");
	    }
	    gains = salaire * totalTime;
	}
	else if (salaryType.equals("annuel"))
	{
	    gains = salaire / 26;
	}

	payEmp.setData("gains", gains);
	return payEmp;
    }
}
