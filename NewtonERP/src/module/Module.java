/**
 * 
 */
package module;

import java.util.Hashtable;
import java.util.Vector;

/**
 * @author Pascal Lemay
 * 
 */
public abstract class Module
{
    // TODO: changer object par Orm des que possible
    protected static Object orm;// reference a l orm

    // TODO changer object par Entity des que possible
    private Vector<Object> entities;

    // TODO changer object par Action des que possible
    private Hashtable<String, Object> actions;

    public static Object getOrm()
    {
	return orm;
    }

    /**
     * @return the Vector entities
     */
    public Vector<Object> getEntities()
    {
	return entities;
    }

    /**
     * @return the actions HashTable
     */
    public Hashtable<String, Object> getActions()
    {
	return actions;
    }

    /**
     * @paramactionName=nom de l action
     * @return action de ce nom contenue dans le HashTable
     */
    public Object getAction(String actionName)
    {
	return actions.get(actionName);
    }

    // TODO changer void pour Entity des que possible
    /*
     * @call l action et retourne le resultat de l action param action=nom de l
     * action param parametres=liste de parametres (cle,parametre)
     */
    public void doAction(String actionName, Hashtable<String, String> parametres)
    {

    }

}
