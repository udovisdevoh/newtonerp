package modules.humanResources.entityDefinitions;

import java.util.Vector;

import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldCalcule;
import newtonERP.orm.field.FieldCurrency;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.FieldValidator;
import newtonERP.orm.field.Fields;

/**
 * Représente un entité d'employé
 * @author Guillaume
 */
public class Employee extends AbstractOrmEntity
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
	Vector<Field<?>> fieldsData = new Vector<Field<?>>();
	fieldsData.add(new FieldInt("Numéro d'employé", getPrimaryKeyName()));
	fieldsData.add(new FieldString("Prénom", "firstName"));
	fieldsData.add(new FieldString("Nom de famille", "lastName"));
	// ******* NAS *******
	FieldInt fieldNAS = new FieldInt("Numéro d'assurance social", "NAS");
	fieldNAS.setValidator(new FieldValidator<Integer>()
	{
	    public boolean validate(Integer value)
	    {
		return value.toString().length() == 9;
	    }
	});
	fieldsData.add(fieldNAS);
	// ******* *******
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
	FieldInt fieldUser = new FieldInt("Compte système", new User()
		.getForeignKeyName());
	fieldUser.setReadOnly(true);
	fieldsData.add(fieldUser);
	FieldInt fieldCalc = new FieldInt("calcule", "calcul");
	fieldCalc.setCalcul(new FieldCalcule<Integer>()
	{
	    public Integer calculate(Fields entityFields)
	    {
		return (Integer) entityFields.getField("nbVacancyDays")
			.getData()
			- (Integer) entityFields.getField("nbSicknessDays")
				.getData();
	    }
	});
	fieldsData.add(fieldCalc);

	return new Fields(fieldsData);
    }
}
