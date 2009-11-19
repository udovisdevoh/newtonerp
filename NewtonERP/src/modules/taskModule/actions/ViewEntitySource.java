package modules.taskModule.actions;

import java.util.Hashtable;

import modules.taskModule.entityDefinitions.EntityEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.generalEntity.StaticTextEntity;
import newtonERP.sourceCodeBuilder.SourceCodeBuilder;

/**
 * 
 * @author Guillaume Lacasse
 */
public class ViewEntitySource extends AbstractAction
{
    /**
     * @throws Exception si création fail
     */
    public ViewEntitySource() throws Exception
    {
	super(new EntityEntity());
    }

    @Override
    public StaticTextEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	EntityEntity entityEntity = (EntityEntity) entity;
	return new StaticTextEntity(SourceCodeBuilder
		.buildEntitySourceCode(entityEntity));
    }
}
