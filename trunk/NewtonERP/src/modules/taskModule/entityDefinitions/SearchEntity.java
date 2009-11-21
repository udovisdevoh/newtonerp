package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldText;

/**
 * Entité de recherche pour spécification
 * @author Guillaume Lacasse
 */
public class SearchEntity extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public SearchEntity() throws Exception
    {
	super();
	setVisibleName("Entité de recherche");
	AccessorManager.addAccessor(this, new EntityEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldText("Description", "name", false));
	fieldList.add(new FieldInt("Entité", new EntityEntity()
		.getForeignKeyName()));
	return new Fields(fieldList);
    }

    /**
     * @return vraie entité de recherche
     * @throws Exception si obtention fail
     */
    public AbstractOrmEntity getEntity() throws Exception
    {
	EntityEntity entityEntity = (EntityEntity) getSingleAccessor(new EntityEntity()
		.getForeignKeyName());

	AbstractOrmEntity entity = entityEntity.getEntityDefinition();

	Vector<SearchCriteria> searchCriteriaList = getSearchCriteriaList();

	for (SearchCriteria searchCriteria : searchCriteriaList)
	    addSearchCriteria(entity, searchCriteria);

	return entity;
    }

    private Vector<SearchCriteria> getSearchCriteriaList() throws Exception
    {
	PluralAccessor searchCriteriaList = getPluralAccessor("SearchCriteria");
	Vector<SearchCriteria> searchCriteriaVector = new Vector<SearchCriteria>();
	for (AbstractOrmEntity entity : searchCriteriaList)
	    searchCriteriaVector.add((SearchCriteria) entity);
	return searchCriteriaVector;
    }

    private void addSearchCriteria(AbstractOrmEntity entity,
	    SearchCriteria searchCriteria) throws Exception
    {
	String key = searchCriteria.getKey();
	String value = searchCriteria.getValue();
	String operator = searchCriteria.getOperator();
	entity.setData(key, value);
	entity.getFields().getField(key).setOperator(operator);
    }
}
