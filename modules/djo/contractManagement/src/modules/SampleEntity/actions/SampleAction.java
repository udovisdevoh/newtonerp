package modules.SampleEntity.actions;

import java.util.Hashtable;

import modules.SampleEntity.entityDefinitions.SampleEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * 
 * @author
 */
public class SampleAction extends AbstractAction{
	/**
	 * @throws Exception si création fail
	 */
	public SampleAction() throws Exception{
		super(new SampleEntity());
	}
	
	@Override
	public AbstractEntity doAction(AbstractEntity entity,
			Hashtable<String, String> parameters) throws Exception{
		// do something usefull end return the resulting Entity
		return null;
	}
}
