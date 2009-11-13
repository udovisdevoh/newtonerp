package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.Fields;

/**
 * Entité intermédiaire entre effect et parameter
 * @author Guillaume Lacasse
 */
public class EffectParameter extends AbstractOrmEntity
{

    /**
     * @throws Exception si création fail
     */
    public EffectParameter() throws Exception
    {
	super();
	setVisibleName("Paramètre d'éffet");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Effet", "effectID"));
	fieldsInit.add(new FieldInt("Paramètre", "parameterID"));
	return new Fields(fieldsInit);
    }

}
