package modules.taskModule.actions;

import java.util.Hashtable;

import modules.taskModule.entityDefinitions.ActionEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.generalEntity.StaticTextEntity;
import newtonERP.sourceCodeBuilder.SourceCodeBuilder;

/**
 * Pour voir le code source (stub) d'une action
 * @author Guillaume Lacasse
 */
public class ViewActionSource extends AbstractAction
{
    /**
     * @throws Exception si création fail
     */
    public ViewActionSource() throws Exception
    {
	super(new ActionEntity());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	ActionEntity actionEntity = (ActionEntity) entity;
	return new StaticTextEntity(SourceCodeBuilder
		.buildActionSourceCode(actionEntity));
    }

}
