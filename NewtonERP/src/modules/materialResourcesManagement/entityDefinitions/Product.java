package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

public class Product extends AbstractOrmEntity implements PromptViewable
{

    public Product() throws Exception
    {
	super();
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numero de produit", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Code de produit", "code"));
	fieldsInit.add(new FieldString("Nom de produit", "name"));
	return new Fields(fieldsInit);
    }

}
