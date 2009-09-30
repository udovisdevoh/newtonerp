package newtonERP.module.actions;

import java.util.Hashtable;

import newtonERP.viewers.Viewable;

/**
 * @author r3lacasgu
 * 
 */
public interface IAction
{
    /**
     * @param entity
     * @param parameters
     * @return
     */
    public Viewable perform(ActionableEntity entity,
	    Hashtable<String, String> parameters);
}