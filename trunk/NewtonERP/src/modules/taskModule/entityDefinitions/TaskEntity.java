package modules.taskModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldBool;
import newtonERP.orm.field.type.FieldInt;

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
     * @param entityParameters paramètres de l'entité
     * @param isStraightSearch si c'est une recherche directe
     * @return true si la spécification de la tâche est satisfaite
     * @throws Exception si vérification fail
     */
    public boolean isSatisfied(Hashtable<String, String> entityParameters,
	    boolean isStraightSearch) throws Exception
    {
	return getSpecification().isSatisfied(entityParameters,
		isStraightSearch);
    }

    private Specification getSpecification() throws Exception
    {
	return (Specification) getSingleAccessor(new Specification()
		.getForeignKeyName());
    }

    /**
     * Execute l'effet de la tâche
     * @param entityParameters paramètres de l'entité
     * @param isStraightSearch si c'est une recherche directe
     * @return entité viewable, résultat de la tâche
     * @throws Exception si execution fail
     */
    public AbstractEntity execute(Hashtable<String, String> entityParameters,
	    boolean isStraightSearch) throws Exception
    {
	return getEffect().execute(entityParameters, isStraightSearch);
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

    /**
     * @return si c'est une recherche directe
     */
    public boolean isStraightSearch()
    {
	return (Boolean) (getData("straightSearch"));
    }
}
