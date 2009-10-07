package newtonERP.viewers.viewables;

import java.util.Hashtable;

/**
 * @author r3lacasgu
 * 
 */
public interface PromptViewable
{
    /**
     * @return nom des input du prompt et leur valeur courantes
     */
    public Hashtable<String, String> getInputList();

    /**
     * @return nom du bouton "ok"
     */
    public String getButtonName();
}
