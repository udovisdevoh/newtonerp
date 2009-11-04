package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Represents a shipper (Lets say FedEx, Purolator etc...)
 * 
 * @author r3hallejo
 */
public class Shipper extends AbstractOrmEntity implements PromptViewable
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public Shipper() throws Exception
    {
	super();
	setVisibleName("Expéditeur");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numero de l'expéditeur",
		getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom de l'expéditeur", "shipperName"));
	return new Fields(fieldsInit);
    }
}
