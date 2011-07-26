package modules.userRightModule.entityDefinitions;

// TODO: clean up that file

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.FieldFactory;
import newtonERP.orm.fields.field.FieldType;
import newtonERP.orm.fields.field.property.NaturalKey;

/**
 * Entity defenition class representing a group for the users
 * 
 * @author CloutierJo, r3hallejo
 */
public class Groups extends AbstractOrmEntity {
	/**
	 */
	public Groups() {
		super();
		AccessorManager.addAccessor(this, new Right());
		setVisibleName("Groupe");
	}

	@Override
	public Fields initFields() {
		Field groupName = FieldFactory.newField(FieldType.STRING, "groupName");
		groupName.addProperty(new NaturalKey());

		Vector<Field> fieldsInit = new Vector<Field>();
		fieldsInit.add(groupName);
		return new Fields(fieldsInit);
	}

	/**
	 * Searches the rights for this group by the groupsID and then returns this list
	 * 
	 * @return rightResult the right list
	 */
	public Vector<Right> getRightList() {
		Vector<Right> rightList = new Vector<Right>();
		for(AbstractOrmEntity entity : getPluralAccessor("Right")){
			rightList.add((Right) entity);
		}
		return rightList;
	}
}
