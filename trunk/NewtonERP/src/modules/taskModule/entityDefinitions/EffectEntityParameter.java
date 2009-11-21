package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;

/**
 * Entité intermédiaire entre effect et parameter
 * @author Guillaume Lacasse
 */
public class EffectEntityParameter extends AbstractOrmEntity
{

    /**
     * @throws Exception si création fail
     */
    public EffectEntityParameter() throws Exception
    {
	super();
	setVisibleName("Paramètre d'éffet");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Effet", "effectEntityID"));
	fieldsInit.add(new FieldInt("Paramètre", "parameterID"));
	return new Fields(fieldsInit);
    }
}
