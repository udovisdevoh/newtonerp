package modules.userRightModule.entityDefinitions;

// TODO: clean up that file

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.FieldFactory;
import newtonERP.orm.fields.field.FieldType;
import newtonERP.orm.fields.field.property.NaturalKey;
import newtonERP.viewers.viewerData.BaseViewerData;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Entity defenition class representing a right
 * 
 * @author CloutierJo, r3hallejo
 */
public class Right extends AbstractOrmEntity {
	/**
	 */
	public Right() {
		super();
		setVisibleName("Droit");
	}

	@Override
	public Fields initFields() {
		Field moduleName = FieldFactory.newField(FieldType.STRING, "moduleName");
		moduleName.addProperty(new NaturalKey());

		Field entityName = FieldFactory.newField(FieldType.STRING, "entityName");
		entityName.addProperty(new NaturalKey());

		Field actionName = FieldFactory.newField(FieldType.STRING, "actionName");
		actionName.addProperty(new NaturalKey());

		Vector<Field> fieldsInit = new Vector<Field>();
		fieldsInit.add(moduleName);
		fieldsInit.add(entityName);
		fieldsInit.add(actionName);

		return new Fields(fieldsInit);
	}

	@Override
	public AbstractEntity deleteUI(Hashtable<String, String> parameters)

	{
		/*
		 * On ne veut pas permettre l'effacement de droit alors on redirige l'effacement vers GetList
		 */
		ListViewerData entityList = super.getList();
		return entityList;
	}

	@Override
	public BaseViewerData editUI(Hashtable<String, String> parameters)

	{
		/*
		 * On ne veut pas permettre la modification de droit alors on redirige vers GetList
		 */
		ListViewerData entityList = super.getList();
		return entityList;
	}

	@Override
	public final ListViewerData getList(Hashtable<String, String> parameters)

	{
		ListViewerData entityList = super.getList(parameters);
		return entityList;
	}
}
