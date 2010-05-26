package modules.production.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.production.actions.GetEquipmentSchedule;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldDateTime;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.viewers.viewerData.PromptViewerData;

/**
 * A schedule for equipment
 * 
 * @author r3hallejo
 */
public class EquipmentSchedule extends AbstractOrmEntity
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public EquipmentSchedule() throws Exception
    {
	setVisibleName("Horaire d'équipements");
	AccessorManager.addAccessor(this, new Equipment());
	AccessorManager.addAccessor(this, new EquipmentPeriodType());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsData = new Vector<Field<?>>();

	fieldsData.add(new FieldInt("Numéro d'horaire", getPrimaryKeyName()));

	fieldsData.add(new FieldInt("Numéro d'équipement", new Equipment()
		.getForeignKeyName()));
	fieldsData.add(new FieldDateTime("Début de la période", "timeStart"));
	fieldsData.add(new FieldDateTime("Fin de la période", "timeStop"));
	fieldsData.add(new FieldInt("Type de la période",
		new EquipmentPeriodType().getForeignKeyName()));

	return new Fields(fieldsData);
    }

    public PromptViewerData editUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	PromptViewerData data = (PromptViewerData) super.editUI(parameters);
	data.setBackLink(new ActionLink("Voir l'horaire",
		new GetEquipmentSchedule()));
	return data;
    }
}
