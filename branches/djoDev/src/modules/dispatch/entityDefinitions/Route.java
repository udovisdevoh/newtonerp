package modules.dispatch.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;

/**
 * Route
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Route extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Route() throws Exception
    {
	super();
	setVisibleName("Route");
	AccessorManager.addAccessor(this, new Livreur());
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKrouteID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKrouteID);

	FieldInt livreurID = new FieldInt("Livreur assigné", "livreurID");
	fieldList.add(livreurID);
	return new Fields(fieldList);
    }
}
