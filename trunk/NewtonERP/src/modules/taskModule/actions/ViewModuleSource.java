package modules.taskModule.actions;

import java.util.Hashtable;

import modules.taskModule.entityDefinitions.ModuleEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.generalEntity.StaticTextEntity;
import newtonERP.sourceCodeBuilder.SourceCodeBuilder;

/**
 * 
 * @author Guillaume Lacasse
 */
public class ViewModuleSource extends AbstractAction
{
    /**
     * @throws Exception si création fail
     */
    public ViewModuleSource() throws Exception
    {
	super(new ModuleEntity());
    }

    @Override
    public StaticTextEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	ModuleEntity moduleEntity = (ModuleEntity) entity;
	return new StaticTextEntity(SourceCodeBuilder
		.buildModuleSourceCode(moduleEntity));
    }
}
