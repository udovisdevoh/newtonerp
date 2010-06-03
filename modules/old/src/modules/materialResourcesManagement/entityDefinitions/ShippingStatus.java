package modules.materialResourcesManagement.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * A shipping status
 * 
 * @author r3hallejo
 */
public class ShippingStatus extends AbstractOrmEntity
{
	/**
	 * Default constructor
	 * 
	 * @throws Exception a general exception
	 */
	public ShippingStatus() throws Exception
	{
		setVisibleName("Status de locations");
	}

	@Override
	public Fields initFields() throws Exception
	{
		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
		fieldsInit.add(new FieldString("Status", "status"));
		return new Fields(fieldsInit);
	}

	@Override
	public final ListViewerData getList(Hashtable<String, String> parameters)
			throws Exception
	{
		Hashtable<String, String> actionParameters = new Hashtable<String, String>();
		actionParameters.put(getPrimaryKeyName(), "&");

		ListViewerData entityList = super.getList(parameters);
		entityList.removeSpecificActions("Effacer");
		entityList.removeSpecificActions("Modifier");

		return entityList;
	}
}
