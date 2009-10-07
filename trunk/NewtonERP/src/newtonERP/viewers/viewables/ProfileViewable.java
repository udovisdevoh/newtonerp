package newtonERP.viewers.viewables;

import java.util.Hashtable;

import newtonERP.module.AbstractAction;

/**
 * @author r3lacasgu
 * 
 */
public interface ProfileViewable
{
    /**
     * @return
     */
    public String getTitle();

    /**
     * @return
     */
    public String getButtonCaption();

    /**
     * @return
     */
    public AbstractAction getSubmitAction();

    /**
     * @return Noms des input et leurs valeurs actuelles
     */
    public Hashtable<String, String> getInputList();
}
