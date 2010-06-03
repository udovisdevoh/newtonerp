package modules.projectManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * A project
 * 
 * @author r3hallejo
 */
public class Project extends AbstractOrmEntity
{
	/**
	 * Default constructor
	 * 
	 * @throws Exception a general exception
	 */
	public Project() throws Exception
	{
		super();
		setVisibleName("Projets");
		AccessorManager.addAccessor(this, new ProjectType());
	}

	@Override
	public Fields initFields() throws Exception
	{
		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
		fieldsInit.add(new FieldString("Nom", "name"));
		fieldsInit.add(new FieldInt("Type de projet", new ProjectType()
				.getForeignKeyName()));
		return new Fields(fieldsInit);
	}
}
