package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldDateTime;
import newtonERP.orm.field.type.FieldInt;

/**
 * A work order for product
 * 
 * @author r3hallejo
 */
public class WorkOrder extends AbstractOrmEntity
{
	/**
	 * Default constructor
	 * 
	 * @throws Exception a general exception
	 */
	public WorkOrder() throws Exception
	{
		setVisibleName("Ordre de travail");
		AccessorManager.addAccessor(this, new Product());
		AccessorManager.addAccessor(this, new WorkOrderStatus());
	}

	@Override
	public Fields initFields() throws Exception
	{
		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
		fieldsInit.add(new FieldInt("Produit associé", new Product()
				.getForeignKeyName()));
		fieldsInit.add(new FieldInt("Quantité", "quantity"));
		fieldsInit.add(new FieldDateTime("Date de création", "creationDate"));
		fieldsInit.add(new FieldInt("Status", new WorkOrderStatus()
				.getForeignKeyName()));
		return new Fields(fieldsInit);
	}

}
