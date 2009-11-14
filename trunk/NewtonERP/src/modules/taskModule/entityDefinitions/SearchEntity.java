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
 * Entité de recherche pour spécification
 * @author Guillaume Lacasse
 */
public class SearchEntity extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception si création fail
     */
    public SearchEntity() throws Exception
    {
	super();
	setVisibleName("Entité de recherche");
	addNaturalKey("name");
	AccessorManager.addAccessor(this, new EntityEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldText("Description", "name"));
	fieldList.add(new FieldInt("Entité", new EntityEntity()
		.getForeignKeyName()));
	return new Fields(fieldList);
    }
}
