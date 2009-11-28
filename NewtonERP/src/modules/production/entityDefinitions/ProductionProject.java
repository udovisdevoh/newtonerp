package modules.production.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.orm.field.type.FieldText;

/**
 * A production project
 * 
 * @author r3hallejo
 */
public class ProductionProject extends AbstractOrmEntity
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public ProductionProject() throws Exception
    {
	super();
	setVisibleName("Projets");
	AccessorManager.addAccessor(this, new ProductionProjectType());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom du projet", "projectName"));
	fieldsInit.add(new FieldInt("Type du projet", new ProductionProjectType()
		.getForeignKeyName()));
	fieldsInit.add(new FieldText("Description", "description", true));
	return new Fields(fieldsInit);
    }
}
