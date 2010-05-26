package modules.humanResources.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.humanResources.actions.GetOneTimeTable;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldDateTime;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.viewers.viewerData.PromptViewerData;

/**
 * Représente l'horaire
 * @author CloutierJo
 */
public class Schedule extends AbstractOrmEntity
{

    /**
     * @throws Exception si création fails
     */
    public Schedule() throws Exception
    {
	super();

	setVisibleName("Horaire");
	AccessorManager.addAccessor(this, new Employee());
	AccessorManager.addAccessor(this, new PeriodeType());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsData = new Vector<Field<?>>();

	fieldsData.add(new FieldInt("Numéro d'horaire", getPrimaryKeyName()));

	fieldsData.add(new FieldInt("Numéro d'employé", new Employee()
		.getForeignKeyName()));
	fieldsData.add(new FieldDateTime("Début de la période", "timeStart"));
	fieldsData.add(new FieldDateTime("Fin de la période", "timeStop"));
	fieldsData.add(new FieldInt("Type de la période", new PeriodeType()
		.getForeignKeyName()));

	return new Fields(fieldsData);
    }

    public PromptViewerData editUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	PromptViewerData data = (PromptViewerData) super.editUI(parameters);
	data
		.setBackLink(new ActionLink("Voir l'horaire",
			new GetOneTimeTable()));
	return data;
    }
}