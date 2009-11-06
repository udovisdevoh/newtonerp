package modules.production.entityDefinitions;

import java.util.Vector;

import modules.humanResources.entityDefinitions.Employee;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDateTime;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * A new maintenance work order to do because of a broken machine
 * 
 * @author r3hallejo
 */
public class MaintenanceTicket extends AbstractOrmEntity implements
	PromptViewable
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
	AccessorManager.addAccessor(this, new StatusType());
	addNaturalKey(getPrimaryKeyName());
	addNaturalKey("problemType");
	addNaturalKey(new StatusType().getForeignKeyName());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro du ticket", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Problème rencontré", "problemType"));
	fieldsInit.add(new FieldInt("Assigné à ", new Employee()
		.getForeignKeyName()));
	fieldsInit.add(new FieldDateTime("Date de début", "startDate"));
	fieldsInit.add(new FieldDateTime("Date de fin", "endDate"));
	fieldsInit.add(new FieldInt("Status", new StatusType()
		.getForeignKeyName()));
	fieldsInit.add(new FieldString("Commentaire", "comment"));
	return new Fields(fieldsInit);
    }
}
