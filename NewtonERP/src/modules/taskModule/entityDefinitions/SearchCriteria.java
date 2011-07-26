package modules.taskModule.entityDefinitions;

// TODO: clean up that file

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.FieldFactory;
import newtonERP.orm.fields.field.FieldType;
import newtonERP.orm.fields.field.property.NaturalKey;
import newtonERP.orm.fields.field.type.FieldInt;

/**
 * Critère de recherche pour une entité de recherche
 * 
 * @author Guillaume Lacasse
 */
public class SearchCriteria extends AbstractOrmEntity {
	/**
	 */
	public SearchCriteria() {
		super();
		setVisibleName("Critère de rercherche");
		AccessorManager.addAccessor(this, new SearchCriteriaOperator());
		AccessorManager.addAccessor(this, new SearchEntity());
	}

	@Override
	public Fields initFields() {
		Field key = FieldFactory.newField(FieldType.STRING, "key");
		key.addProperty(new NaturalKey());

		Field specOperator = new FieldInt("Opérateur", new SearchCriteriaOperator().getForeignKeyName());
		specOperator.addProperty(new NaturalKey());

		Field value = FieldFactory.newField(FieldType.TEXT, "value");
		value.addProperty(new NaturalKey());

		Vector<Field> fieldList = new Vector<Field>();
		fieldList.add(new FieldInt("Entité de recherche", new SearchEntity().getForeignKeyName()));
		fieldList.add(key);
		fieldList.add(specOperator);
		fieldList.add(value);
		return new Fields(fieldList);
	}

	/**
	 * @return clef du critère de recherche
	 */
	public String getKey() {
		return (String) getData("key");
	}

	/**
	 * @return valeur du critère de recherche
	 */
	public String getValue() {
		return getDataString("value");
	}

	/**
	 * @return opérateur en string
	 */
	public String getOperator() {
		return getOperatorEntity().getOperator();
	}

	private SearchCriteriaOperator getOperatorEntity() {
		return (SearchCriteriaOperator) getSingleAccessor(new SearchCriteriaOperator().getForeignKeyName());
	}
}
