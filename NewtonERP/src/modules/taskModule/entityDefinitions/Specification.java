package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.Type.FieldInt;
import newtonERP.orm.field.Type.FieldText;

/**
 * Spécification d'une tâche
 * @author Guillaume Lacasse
 */
public class Specification extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public Specification() throws Exception
    {
	super();
	setVisibleName("Spécification");
	AccessorManager.addAccessor(this, new SearchEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldText("Description", "name", false));
	fieldList.add(new FieldInt("Entité de recherche", new SearchEntity()
		.getForeignKeyName()));
	return new Fields(fieldList);
    }

    /**
     * @return true si la spécification est satisfaite
     * @throws Exception si test fail
     */
    public boolean isSatisfied() throws Exception
    {
	System.out
		.println("Vérification à savoir si une spécification est satisfaite");

	AbstractOrmEntity searchEntity = getSearchEntity();

	Vector<AbstractOrmEntity> result = Orm.select(searchEntity);

	if (result.size() > 0)
	    return true;
	return false;
    }

    private AbstractOrmEntity getSearchEntity() throws Exception
    {
	SearchEntity searchEntity = (SearchEntity) getSingleAccessor(new SearchEntity()
		.getForeignKeyName());

	return searchEntity.getEntity();
    }
}
