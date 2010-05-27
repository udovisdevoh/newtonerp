package modules.expoModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldBool;
import newtonERP.orm.field.type.FieldInt;

/**
 * Représente un corridor
 * @author Guillaume Lacasse
 */
public class Corridor extends AbstractOrmEntity
{
    /**
     * @throws Exception si ça fail
     */
    public Corridor() throws Exception
    {
	super();
	setVisibleName("Corridor");
	AccessorManager.addAccessor(this, new Floor());
	setDetailedDescription("corridor de kiosque");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt corridorId = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(corridorId);

	FieldInt position = new FieldInt("Position", "Position");
	position.setNaturalKey(true);
	fieldList.add(position);

	FieldBool isVertical = new FieldBool("Si vertical", "IsVertical");
	isVertical.setNaturalKey(true);
	fieldList.add(isVertical);

	FieldInt floorID = new FieldInt("Plancher", "floorID");
	fieldList.add(floorID);
	return new Fields(fieldList);
    }

}
