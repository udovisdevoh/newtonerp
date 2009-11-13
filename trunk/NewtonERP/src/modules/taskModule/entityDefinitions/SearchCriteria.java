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

public class SearchCriteria extends AbstractOrmEntity implements PromptViewable
{
    public SearchCriteria() throws Exception
    {
	super();
	setVisibleName("Critère de rercherche");
	addNaturalKey("key");
	addNaturalKey(new SearchCriteriaOperator().getForeignKeyName());
	addNaturalKey("value");
	AccessorManager.addAccessor(this, new SearchCriteriaOperator());
	AccessorManager.addAccessor(this, new SearchEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldInt("Entité de recherche", new SearchEntity()
		.getForeignKeyName()));
	fieldList.add(new FieldString("Nom de clef", "key"));
	fieldList.add(new FieldInt("Opérateur", new SearchCriteriaOperator()
		.getForeignKeyName()));
	fieldList.add(new FieldText("Valeur", "value"));
	return new Fields(fieldList);
    }
}
