package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Represents a shipper (Lets say FedEx, Purolator etc...)
 * 
 * @author r3hallejo
 */
public class Shipper extends AbstractOrmEntity
{

	/**
	 * Default constructor
	 * 
	 * @throws Exception a general exception
	 */
	public Shipper() throws Exception
	{
		super();
		setVisibleName("Expéditeur");
	}

	@Override
	public Fields initFields() throws Exception
	{
		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		fieldsInit.add(new FieldInt("Numero de l'expéditeur",
				getPrimaryKeyName()));
		fieldsInit.add(new FieldString("Nom de l'expéditeur", "shipperName"));
		return new Fields(fieldsInit);
	}
}
