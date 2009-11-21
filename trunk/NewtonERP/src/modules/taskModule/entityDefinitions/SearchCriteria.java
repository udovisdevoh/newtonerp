package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.orm.field.type.FieldText;

/**
 * Critère de recherche pour une entité de recherche
 * @author Guillaume Lacasse
 */
public class SearchCriteria extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public SearchCriteria() throws Exception
    {
	super();
	setVisibleName("Critère de rercherche");
	AccessorManager.addAccessor(this, new SearchCriteriaOperator());
	AccessorManager.addAccessor(this, new SearchEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldString key = new FieldString("Nom de clef", "key");
	key.setNaturalKey(true);

	FieldInt specOperator = new FieldInt("Opérateur",
		new SearchCriteriaOperator().getForeignKeyName());
	specOperator.setNaturalKey(true);

	FieldText value = new FieldText("Valeur", "value", false);
	value.setNaturalKey(true);

	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldInt("Entité de recherche", new SearchEntity()
		.getForeignKeyName()));
	fieldList.add(key);
	fieldList.add(specOperator);
	fieldList.add(value);
	return new Fields(fieldList);
    }

    /**
     * @return clef du critère de recherche
     */
    public String getKey()
    {
	return (String) getData("key");
    }

    /**
     * @return valeur du critère de recherche
     */
    public String getValue()
    {
	return getDataString("value");
    }

    /**
     * @return opérateur en string
     * @throws Exception si optention fail
     */
    public String getOperator() throws Exception
    {
	return getOperatorEntity().getOperator();
    }

    private SearchCriteriaOperator getOperatorEntity() throws Exception
    {
	return (SearchCriteriaOperator) getSingleAccessor(new SearchCriteriaOperator()
		.getForeignKeyName());
    }
}
