package modules.marketing.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Entité dun sector
 * @author Gabriel
 * 
 */
public class Sector extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public Sector() throws Exception
    {
	super();
	setVisibleName("Secteurs");

    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom du Secteur", "sector"));
	return new Fields(fieldsInit);
    }
}