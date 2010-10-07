package modules.userRightModule.entityDefinitions;

import java.util.Vector;

import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Entity defenition class representing a right
 * @author CloutierJo, r3hallejo
 */
public class Right extends newtonERP.module.AbstractOrmEntity
{
	public Right()
	{
		super();
		setVisibleName("Droit");
	}

	public newtonERP.orm.field.Fields initFields()
	{
		FieldString moduleName = new FieldString("Module", "moduleName");
		moduleName.setNaturalKey(true);

		FieldString entityName = new FieldString("Entité", "entityName");
		entityName.setNaturalKey(true);

		FieldString actionName = new FieldString("Action", "actionName");
		actionName.setNaturalKey(true);

		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
		fieldsInit.add(moduleName);
		fieldsInit.add(entityName);
		fieldsInit.add(actionName);

		return new Fields(fieldsInit);
	}

	@Override
	public newtonERP.module.AbstractEntity deleteUI(
			Hashtable<String, String> parameters)
	{
		/*
		 * On ne veut pas permettre l'effacement de droit alors on redirige
		 * l'effacement vers GetList
		 */
		ListViewerData entityList = super.getList();
		return entityList;
	}

	@Override
	public newtonERP.viewers.viewerData.BaseViewerData editUI(
			Hashtable<String, String> parameters)
	{
		/*
		 * On ne veut pas permettre la modification de droit alors on redirige
		 * vers GetList
		 */
		ListViewerData entityList = super.getList();
		return entityList;
	}

	@Override
	public final newtonERP.viewers.viewerData.ListViewerData getList(
			Hashtable<String, String> parameters)
	{
		ListViewerData entityList = super.getList(parameters);
		return entityList;
	}

}
