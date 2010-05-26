package modules.production.entityDefinitions;

import java.util.Vector;

import modules.humanResources.entityDefinitions.Employee;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldDateTime;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.orm.field.type.FieldText;

/**
 * A new maintenance work order to do because of a broken machine
 * 
 * @author r3hallejo
 */
public class MaintenanceTicket extends AbstractOrmEntity
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public MaintenanceTicket() throws Exception
    {
	super();
	setVisibleName("Ticket de maintenance");
	AccessorManager.addAccessor(this, new Employee());
	AccessorManager.addAccessor(this, new MaintenanceStatusType());
	AccessorManager.addAccessor(this, new Machine());
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldInt primaryKey = new FieldInt("Numéro du ticket",
		getPrimaryKeyName());
	primaryKey.setNaturalKey(true);

	FieldString problemType = new FieldString("Problème rencontré",
		"problemType");
	problemType.setNaturalKey(true);

	FieldInt maintenanceStatusType = new FieldInt("Status",
		new MaintenanceStatusType().getForeignKeyName());
	maintenanceStatusType.setNaturalKey(true);

	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(primaryKey);
	fieldsInit.add(new FieldInt("Machine concernée", new Machine()
		.getForeignKeyName()));
	fieldsInit.add(problemType);
	fieldsInit.add(new FieldInt("Assigné à ", new Employee()
		.getForeignKeyName()));
	fieldsInit.add(new FieldDateTime("Date de début", "startDate"));
	fieldsInit.add(new FieldDateTime("Date de fin", "endDate"));
	fieldsInit.add(maintenanceStatusType);
	fieldsInit.add(new FieldText("Commentaire", "comment", false));
	return new Fields(fieldsInit);
    }
}
