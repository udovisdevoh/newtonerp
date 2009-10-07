package newtonERP.viewers.viewables;

import java.util.Hashtable;

import newtonERP.module.AbstractAction;
import newtonERP.module.Module;

/**
 * @author r3lacasgu
 * 
 */
public interface ProfileViewable
{
    /**
     * @return Module callé par le prompt
     */
    public Module getSubmitModule();

    /**
     * @return Action à affectuer quand on submit le prompt
     */
    public AbstractAction getSubmitAction();

    /**
     * @return Utilisateur actuel Cette methode sera effacée au sprint 2 car
     *         l'autentification sera faite dans le servlet
     */
    public String getCurrentUserName();

    /**
     * @return
     */
    public String getTitle();

    /**
     * @return
     */
    public String getButtonCaption();

    /**
     * @return Noms des input et leurs valeurs actuelles
     */
    public Hashtable<String, String> getInputList();
}
