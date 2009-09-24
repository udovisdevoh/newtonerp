package newtonERP.module;

import java.util.Hashtable;

import newtonERP.ActionableEntity;

/**
 * @author r3lacasgu
 * 
 */
public interface IAction
{
    public void perform(ActionableEntity entity,
	    Hashtable<String, String> parameters);
}