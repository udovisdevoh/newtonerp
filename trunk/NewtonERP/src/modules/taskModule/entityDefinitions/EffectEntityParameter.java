package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;

/**
 * Entité intermédiaire entre effect et parameter
 * 
 * @author Guillaume Lacasse
 */
public class EffectEntityParameter extends AbstractOrmEntity {

	/**
	 */
	public EffectEntityParameter() {
		super();
		setVisibleName("Paramètre d'éffet");
	}

	@Override
	public Fields initFields() {
		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		fieldsInit.add(new FieldInt("Effet", "effectEntityID"));
		fieldsInit.add(new FieldInt("Paramètre", "parameterID"));
		return new Fields(fieldsInit);
	}
}
