package modules.taskModule.actions; 
 // TODO: clean up that file

import java.util.Hashtable;

import modules.taskModule.entityDefinitions.ModuleEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.generalEntity.StaticTextEntity;
import newtonERP.sourceCodeBuilder.SourceCodeBuilder;

/**
 * @author Guillaume Lacasse
 */
public class ViewModuleSource extends AbstractAction {
	/**
	 */
	public ViewModuleSource() {
		super(new ModuleEntity());
	}

	@Override
	public StaticTextEntity doAction(AbstractEntity entity, Hashtable<String, String> parameters) {
		ModuleEntity moduleEntity = (ModuleEntity) entity;
		return new StaticTextEntity(SourceCodeBuilder.buildModuleSourceCode(moduleEntity));
	}
}
