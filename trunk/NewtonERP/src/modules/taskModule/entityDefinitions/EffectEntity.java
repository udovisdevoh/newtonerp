package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldText;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Représente l'effet d'une task son action son entité de recherche ses
 * paramètres custom
 * @author Guillaume Lacasse
 */
public class EffectEntity extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception si création fail
     */
    public EffectEntity() throws Exception
    {
	super();
	setVisibleName("Effet");
	addNaturalKey("name");
	AccessorManager.addAccessor(this, new SearchEntity());
	AccessorManager.addAccessor(this, new Parameter());
	AccessorManager.addAccessor(this, new ActionEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldText("Description", "name", false));
	fieldList.add(new FieldInt("Entité de recherche", new SearchEntity()
		.getForeignKeyName()));
	fieldList.add(new FieldInt("Action", new ActionEntity()
		.getForeignKeyName()));
	return new Fields(fieldList);
    }

    /**
     * Exécute l'effet d'une tâche
     */
    public void execute()
    {
	System.out.println("Exécution d'une tâche");
	// TODO Auto-generated method stub
    }
}
