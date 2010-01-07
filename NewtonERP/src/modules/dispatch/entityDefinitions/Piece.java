package modules.dispatch.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Pièce
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Piece extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Piece() throws Exception
    {
	super();
	setVisibleName("Pièce");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKpieceID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKpieceID);

	FieldString description = new FieldString("Description", "Description");
	fieldList.add(description);
	return new Fields(fieldList);
    }
}
