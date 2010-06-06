package modules.sample;

import modules.sample.entityDefinitions.SampleEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;

/**
 * Module de
 * 
 * @author
 */
public class Sample extends Module{
	
	/**
	 * @throws Exception si création fail
	 */
	public Sample() throws Exception{
		super();
		setDefaultAction(new BaseAction("GetList", new SampleEntity()));
		addGlobalActionMenuItem("Employés ", new BaseAction("GetList",
				new SampleEntity()));
		
		setVisibleName("Ressources humaines");
	}
	
	public void initDB() throws Exception{
		super.initDB();
	}
}
