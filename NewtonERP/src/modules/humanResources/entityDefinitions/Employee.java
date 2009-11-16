package modules.humanResources.entityDefinitions;

import java.util.Vector;

import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldCurrency;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;
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
	AccessorManager.addAccessor(this, new User());

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
	fieldsData.add(new FieldInt("Type d'employé", new EmployeeType()
		.getForeignKeyName()));
	fieldsData.add(new FieldString("Poste", "poste"));
	fieldsData.add(new FieldInt("Department", new Department()
		.getForeignKeyName()));
	fieldsData.add(new FieldInt("Type de salaire", new SalaryType()
		.getForeignKeyName()));
	fieldsData.add(new FieldCurrency("Salaire", "salary"));
	fieldsData.add(new FieldInt("Nombre de jour de vacance possible",
		"nbVacancyDays"));
	fieldsData.add(new FieldInt("Nombre de jour de maladie possible",
		"nbSicknessDays"));
	fieldsData.add(new FieldInt("Compte système", new User()
		.getForeignKeyName()));

	return new Fields(fieldsData);
    }
}
