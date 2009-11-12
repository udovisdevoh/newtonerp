package modules.production.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDate;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Represents any machine forming a production line.
 * 
 * TODO : Link the maintenance tickets on machines when it will be done
 * 
 * @author r3hallejo
 */
public class Machine extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception a general exception
     */
    public Machine() throws Exception
    {
	super();
	setVisibleName("Machinerie");
	AccessorManager.addAccessor(this, new MachineStatus());
	AccessorManager.addAccessor(this, new MachineDimension());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom", "name"));
	fieldsInit.add(new FieldDate("Date d'entrée en fonction", "entryDate"));
	fieldsInit.add(new FieldInt("Status", new MachineStatus()
		.getForeignKeyName()));
	fieldsInit.add(new FieldInt("Dimensions", new MachineDimension()
		.getForeignKeyName()));
	fieldsInit.add(new FieldString("Fonction", "function"));
	return new Fields(fieldsInit);
    }
}
