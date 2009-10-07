package newtonERP.viewers.viewables;

import java.util.Hashtable;

import newtonERP.module.AbstractAction;
import newtonERP.module.Module;

/**
 * @author r3lacasgu
 * 
 */
public interface PromptViewable
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
     * @return message du prompt, exemple: quel est votre nom?
     */
    public String getPromptMessage();

    /**
     * @return nom des input du prompt et leur valeur actuelles
     */
    public Hashtable<String, String> getInputList();

    /**
     * @return nom du bouton "ok"
     */
    public String getButtonCaption();
}
