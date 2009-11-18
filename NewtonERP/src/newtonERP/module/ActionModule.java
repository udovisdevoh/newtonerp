package newtonERP.module;

import java.util.Hashtable;

/**
 * classe servant a enregistre les lien entre un module et ses action de maniere
 * persistante
 * @author CloutierJo
 * 
 */
public class ActionModule
{
    static Hashtable<Class<?>, Class<?>> actionModule = new Hashtable<Class<?>, Class<?>>();

    /**
     * permet d'obtenir le module proprietaire a partir de l'action
     * @param action l'action dont on veut obtenir le proprietaire
     * @return le module
     * @throws Exception remonte
     */
    public static Module getModule(AbstractAction action) throws Exception
    {
	return (Module) actionModule.get(action.getClass()).newInstance();
    }

    /**
     * ajoute un relation action-module
     * @param action l'Action a ajouter
     * @param module le module a ajouter
     */
    public static void addActionModule(AbstractAction action, Module module)
    {
	actionModule.put(action.getClass(), module.getClass());
    }
}
