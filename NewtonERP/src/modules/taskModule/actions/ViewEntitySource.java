package modules.taskModule.actions; 
 // TODO: clean up that file

import java.util.Hashtable;

import modules.taskModule.entityDefinitions.EntityEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.generalEntity.StaticTextEntity;
import newtonERP.sourceCodeBuilder.SourceCodeBuilder;

/**
 * @author Guillaume Lacasse
 */
public class ViewEntitySource extends AbstractAction {
	/**
	 */
	public ViewEntitySource() {
		super(new EntityEntity());
	}

	@Override
	public StaticTextEntity doAction(AbstractEntity entity, Hashtable<String, String> parameters) {
		EntityEntity entityEntity = (EntityEntity) entity;
		return new StaticTextEntity(SourceCodeBuilder.buildEntitySourceCode(entityEntity));
	}
}
