package modules.materialResourcesManagement.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.viewers.viewerData.BaseViewerData;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * A work order status
 * 
 * @author r3hallejo
 */
public class WorkOrderStatus extends AbstractOrmEntity
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public WorkOrderStatus() throws Exception
    {
	setVisibleName("Type de work order");
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
}