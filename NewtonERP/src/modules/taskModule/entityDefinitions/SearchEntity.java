package modules.taskModule.entityDefinitions;

// TODO: clean up that file

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.FieldFactory;
import newtonERP.orm.fields.field.FieldType;
import newtonERP.orm.fields.field.type.FieldInt;

/**
 * Entité de recherche pour spécification
 * 
 * @author Guillaume Lacasse
 */
public class SearchEntity extends AbstractOrmEntity {
	/**
	 */
	public SearchEntity() {
		super();
		setVisibleName("Entité de recherche");
		AccessorManager.addAccessor(this, new EntityEntity());
	}

	@Override
	public Fields initFields() {
		Vector<Field> fieldList = new Vector<Field>();
		fieldList.add(FieldFactory.newField(FieldType.TEXT, "name"));
		fieldList.add(new FieldInt("Entité", new EntityEntity().getForeignKeyName()));
		return new Fields(fieldList);
	}

	/**
	 * @return vraie entité de recherche
	 */
	public AbstractOrmEntity getEntity() {
		EntityEntity entityEntity = (EntityEntity) getSingleAccessor(new EntityEntity().getForeignKeyName());

		AbstractOrmEntity entity = entityEntity.getEntityDefinition();

		Vector<SearchCriteria> searchCriteriaList = getSearchCriteriaList();

		for(SearchCriteria searchCriteria : searchCriteriaList){
			addSearchCriteria(entity, searchCriteria);
		}

		return entity;
	}

	private Vector<SearchCriteria> getSearchCriteriaList() {
		PluralAccessor searchCriteriaList = getPluralAccessor("SearchCriteria");
		Vector<SearchCriteria> searchCriteriaVector = new Vector<SearchCriteria>();
		for(AbstractOrmEntity entity : searchCriteriaList){
			searchCriteriaVector.add((SearchCriteria) entity);
		}
		return searchCriteriaVector;
	}

	private void addSearchCriteria(AbstractOrmEntity entity, SearchCriteria searchCriteria) {
		String key = searchCriteria.getKey();
		String value = searchCriteria.getValue();
		String operator = searchCriteria.getOperator();
		entity.setData(key, value);
		entity.getFields().getField(key).setOperator(operator);
	}
}
