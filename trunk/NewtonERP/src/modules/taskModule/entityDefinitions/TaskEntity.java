package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldBool;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.Fields;

/**
 * entité représentant une task
 * @author Guillaume Lacasse
 * 
 */
public class TaskEntity extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public TaskEntity() throws Exception
    {
	super();
	setVisibleName("Tâche automatisée");
	AccessorManager.addAccessor(this, new Specification());
	AccessorManager.addAccessor(this, new EffectEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldInt specification = new FieldInt("Specification",
		new Specification().getForeignKeyName());
	specification.setNaturalKey(true);

	FieldInt effet = new FieldInt("Effet", new EffectEntity()
		.getForeignKeyName());
	effet.setNaturalKey(true);

	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldBool("Est active", "isActive"));
	fieldList.add(new FieldBool("Recherche directe", "straightSearch"));
	fieldList.add(specification);
	fieldList.add(effet);
	return new Fields(fieldList);
    }

    /**
     * @return true si la spécification de la tâche est satisfaite
     * @throws Exception si vérification fail
     */
    public boolean isSatisfied() throws Exception
    {
	return getSpecification().isSatisfied();
    }

    private Specification getSpecification() throws Exception
    {
	return (Specification) getSingleAccessor(new Specification()
		.getForeignKeyName());
    }

    /**
     * Execute l'effet de la tâche
     * @return entité viewable, résultat de la tâche
     * @throws Exception si execution fail
     */
    public AbstractEntity execute() throws Exception
    {
	return getEffect().execute();
    }

    private EffectEntity getEffect() throws Exception
    {
	return (EffectEntity) getSingleAccessor(new EffectEntity()
		.getForeignKeyName());
    }

    /**
     * @return si la tâche est présentement active
     */
    public boolean isActive()
    {
	return (Boolean) (getData("isActive"));
    }
}
