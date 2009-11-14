package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.FieldText;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Représente l'effet d'une task son action son entité de recherche ses
 * paramètres custom
 * @author Guillaume Lacasse
 */
public class Effect extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception si création fail
     */
    public Effect() throws Exception
    {
	super();
	setVisibleName("Effet");
	addNaturalKey("name");
	AccessorManager.addAccessor(this, new SearchEntity());
	AccessorManager.addAccessor(this, new Parameter());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldText("Description", "name"));
	fieldList.add(new FieldInt("Entité de recherche", new SearchEntity()
		.getForeignKeyName()));
	fieldList.add(new FieldString("Nom système d'action",
		"actionSystemName"));
	return new Fields(fieldList);
    }
}