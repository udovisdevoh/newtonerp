package modules.humanResources;

import modules.humanResources.entityDefinitions.Department;
import modules.humanResources.entityDefinitions.Employee;
import modules.humanResources.entityDefinitions.EmployeeType;
import modules.humanResources.entityDefinitions.PeriodeType;
import modules.humanResources.entityDefinitions.SalaryType;
import modules.humanResources.entityDefinitions.Schedule;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * Module de gestion des resources humaines
 * @author Guillaume Lacasse
 */
public class HumanResources extends Module
{

    /**
     * @throws Exception si création fail
     */
    public HumanResources() throws Exception
    {
	super();
	setDefaultAction(new BaseAction("GetList", new Employee()));
	addGlobalActionMenuItem("Employés ", new BaseAction("GetList",
		new Employee()));
	addGlobalActionMenuItem("Départements", new BaseAction("GetList",
		new Department()));
	addGlobalActionMenuItem("Horaire", new BaseAction("GetList",
		new Schedule()));
	addGlobalActionMenuItem("Type d'employé", new BaseAction("GetList",
		new EmployeeType()));
	addGlobalActionMenuItem("Type de salaire", new BaseAction("GetList",
		new SalaryType()));
	addGlobalActionMenuItem("Type de période", new BaseAction("GetList",
		new PeriodeType()));

	setVisibleName("Ressources humaines");
    }

    public void initDB() throws Exception
    {
	super.initDB();
	SalaryType salTyp1 = new SalaryType();
	salTyp1.setData("SalaryType", "horaire");
	salTyp1.newE();
	SalaryType salTyp2 = new SalaryType();
	salTyp2.setData("SalaryType", "annuel");
	salTyp2.newE();

	PeriodeType perTyp1 = new PeriodeType();
	perTyp1.setData("PeriodeType", "vaccance");
	perTyp1.newE();
	PeriodeType perTyp2 = new PeriodeType();
	perTyp2.setData("PeriodeType", "travail");
	perTyp2.newE();

	EmployeeType empTyp1 = new EmployeeType();
	empTyp1.setData("EmployeeType", "contractuel");
	empTyp1.newE();
	EmployeeType empTyp2 = new EmployeeType();
	empTyp2.setData("EmployeeType", "permanent");
	empTyp2.newE();

	Department dep = new Department();
	dep.setData("departmentName", "direction");
	dep.newE();

	Employee emp1 = new Employee();
	emp1.setData("firstName", "John");
	emp1.setData("lastName", "Doe");
	emp1.setData("NAS", 123456789);
	emp1.setData(new EmployeeType().getForeignKeyName(), empTyp1
		.getPrimaryKeyValue());
	emp1.setData("poste", "big boss");
	emp1.setData(new Department().getForeignKeyName(), dep
		.getPrimaryKeyValue());
	emp1.setData(new SalaryType().getForeignKeyName(), salTyp1
		.getPrimaryKeyValue());
	emp1.setData("salary", 200000);
	emp1.setData("nbVacancyDays", 28);
	emp1.setData("nbSicknessDays", 6);
	emp1.newE();

	Employee emp2 = new Employee();
	emp2.setData("firstName", "Michel");
	emp2.setData("lastName", "Tremblay");
	emp2.setData("NAS", 987654321);
	emp2.setData(new EmployeeType().getForeignKeyName(), empTyp2
		.getPrimaryKeyValue());
	emp2.setData("poste", "secrétaire du big boss");
	emp2.setData(new Department().getForeignKeyName(), dep
		.getPrimaryKeyValue());
	emp2.setData(new SalaryType().getForeignKeyName(), salTyp2
		.getPrimaryKeyValue());
	emp2.setData("salary", 13);
	emp2.setData("nbVacancyDays", 14);
	emp2.setData("nbSicknessDays", 3);
	emp2.newE();

    }
}