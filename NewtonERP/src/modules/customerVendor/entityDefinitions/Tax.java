package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;

/**
 * A tax entity
 * 
 * @author r3hallejo
 */
public class Tax extends AbstractOrmEntity
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public Tax() throws Exception
    {
	super();
	setVisibleName("Taxes");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
	return new Fields(fieldsInit);
    }

}
