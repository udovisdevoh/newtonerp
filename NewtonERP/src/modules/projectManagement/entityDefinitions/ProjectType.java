package modules.projectManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * A project type
 * 
 * @author r3hallejo
 */
public class ProjectType extends AbstractOrmEntity
{
	/**
	 * @throws Exception a general exception
	 */
	public ProjectType() throws Exception
	{
		super();
		setVisibleName("Type de projet");
	}

	@Override
	public Fields initFields() throws Exception
	{
		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
		fieldsInit.add(new FieldString("Type", "type"));
		return new Fields(fieldsInit);
	}
}
