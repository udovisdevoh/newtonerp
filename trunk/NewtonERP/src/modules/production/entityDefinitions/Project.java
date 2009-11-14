package modules.production.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.FieldText;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * A production project
 * 
 * @author r3hallejo
 */
public class Project extends AbstractOrmEntity implements PromptViewable
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public Project() throws Exception
    {
	super();
	setVisibleName("Projets");
	AccessorManager.addAccessor(this, new ProjectType());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom du projet", "projectName"));
	fieldsInit.add(new FieldInt("Type du projet", new ProjectType()
		.getForeignKeyName()));
	fieldsInit.add(new FieldText("Description", "description", false));
	return new Fields(fieldsInit);
    }
}
