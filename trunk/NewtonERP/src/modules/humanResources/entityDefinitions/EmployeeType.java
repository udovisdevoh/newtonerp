package modules.humanResources.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Représente le type d'employer
 * @author CloutierJo
 */
public class EmployeeType extends AbstractOrmEntity
{

    /**
     * @throws Exception si création fails
     */
    public EmployeeType() throws Exception
    {
	super();
	setVisibleName("Type d'employé");
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldString employeeType = new FieldString("type", "EmployeeType");
	employeeType.setNaturalKey(true);

	Vector<Field<?>> fieldsData = new Vector<Field<?>>();
	fieldsData.add(new FieldInt("Numéro de type", getPrimaryKeyName()));
	fieldsData.add(employeeType);
	return new Fields(fieldsData);
    }

}
