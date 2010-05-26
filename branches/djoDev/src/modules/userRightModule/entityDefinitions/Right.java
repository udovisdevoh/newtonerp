package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.viewers.viewerData.BaseViewerData;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Entity defenition class representing a right
 * @author CloutierJo, r3hallejo
 */
public class Right extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public Right() throws Exception
    {
	super();
	setVisibleName("Droit");
    }

    public Fields initFields() throws InvalidOperatorException
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
    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	/*
	 * On ne veut pas permettre l'effacement de droit alors on redirige
	 * l'effacement vers GetList
	 */
	ListViewerData entityList = super.getList();
	return entityList;
    }

    @Override
    public BaseViewerData editUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	/*
	 * On ne veut pas permettre la modification de droit alors on redirige
	 * vers GetList
	 */
	ListViewerData entityList = super.getList();
	return entityList;
    }

    @Override
    public final ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	ListViewerData entityList = super.getList(parameters);
	return entityList;
    }
}
