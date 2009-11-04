package modules.humanResources.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDouble;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Représente un entité d'employé
 * @author Guillaume
 */
public class Employee extends AbstractOrmEntity implements PromptViewable
{

    /**
     * @throws Exception lorsque la création d'un employé fail
     */
    public Employee() throws Exception
    {
	super();

	AccessorManager.addAccessor(this, new Department());
	AccessorManager.addAccessor(this, new EmployeeType());
	AccessorManager.addAccessor(this, new SalaryType());
	addCurrencyFormat("salary");

	setVisibleName("Employé");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsData = new Vector<Field>();
	fieldsData.add(new FieldInt("Numéro d'employé", getPrimaryKeyName()));
	fieldsData.add(new FieldString("Prénom", "firstName"));
	fieldsData.add(new FieldString("Nom de famille", "lastName"));
	fieldsData.add(new FieldInt("Numéro d'assurance social", "NAS"));
	fieldsData.add(new FieldInt("type d'employer", new EmployeeType()
		.getForeignKeyName()));
	fieldsData.add(new FieldString("poste", "poste"));
	fieldsData.add(new FieldInt("department", new Department()
		.getForeignKeyName()));
	fieldsData.add(new FieldInt("Type de salaire", new SalaryType()
		.getForeignKeyName()));
	fieldsData.add(new FieldDouble("Salaire annuel", "salary"));
	fieldsData.add(new FieldInt("Nombre de jour de vaccance possible",
		"nbVacancyDays"));
	fieldsData.add(new FieldInt("Nombre de jour de maladie possible",
		"nbSicknessDays"));

	return new Fields(fieldsData);
    }
}
