package modules.production.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Different status types for a maintenance ticket
 * 
 * @author r3hallejo
 */
public class MaintenanceStatusType extends AbstractOrmEntity
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public MaintenanceStatusType() throws Exception
    {
	super();
	setVisibleName("Status possible");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numero du status", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Status", "status"));
	return new Fields(fieldsInit);
    }

}
