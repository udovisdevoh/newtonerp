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
	addCurrencyFormat("salary");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsData = new Vector<Field>();// Renamé en fieldsData
	fieldsData.add(new FieldInt("Numéro d'employé", getPrimaryKeyName()));
	fieldsData.add(new FieldString("Prénom", "firstName"));
	fieldsData.add(new FieldString("Nom de famille", "lastName"));
	fieldsData.add(new FieldInt("Numéro department", "departmentID"));
	fieldsData.add(new FieldDouble("Salaire annuel", "salary"));
	return new Fields(fieldsData);
    }
}
