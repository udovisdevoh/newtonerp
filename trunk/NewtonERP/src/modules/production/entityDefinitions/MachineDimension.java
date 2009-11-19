package modules.production.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldDouble;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

/**
 * A machinery dimension. Warning : May disappear in further refactoring
 * 
 * @author r3hallejo
 */
public class MachineDimension extends AbstractOrmEntity
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public MachineDimension() throws Exception
    {
	super();
	setVisibleName("Dimensions");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldsInit.add(new FieldDouble("Hauteur", "height"));
	fieldsInit.add(new FieldDouble("Largeur", "width"));
	fieldsInit.add(new FieldDouble("Profondeur", "depth"));
	fieldsInit.add(new FieldDouble("Poids", "weight"));
	fieldsInit.add(new FieldString("Type d'unités", "unitTypes"));
	return new Fields(fieldsInit);
    }

}
