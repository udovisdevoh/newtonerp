package modules.production.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * A machine status
 * 
 * @author r3hallejo
 */
public class MachineStatus extends AbstractOrmEntity
{

	/**
	 * Default constructor
	 * 
	 * @throws Exception a general exception
	 */
	public MachineStatus() throws Exception
	{
		super();
		setVisibleName("Status");
	}

	@Override
	public Fields initFields() throws Exception
	{
		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
		fieldsInit.add(new FieldString("Status", "status"));
		return new Fields(fieldsInit);
	}

}
