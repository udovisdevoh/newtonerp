package modules.production.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * @author r3hallejo
 * 
 */
public class ProductionProjectType extends AbstractOrmEntity
{

	/**
	 * Default constructor
	 * 
	 * @throws Exception a general exception
	 */
	public ProductionProjectType() throws Exception
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
