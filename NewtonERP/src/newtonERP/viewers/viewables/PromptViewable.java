package newtonERP.viewers.viewables;

import java.util.Vector;

/**
 * @author r3lacasgu
 * 
 */
public interface PromptViewable
{
    /**
     * @return nom des input du prompt
     */
    public Vector<String> getInputNameList();

    /**
     * @return nom du bouton "ok"
     */
    public String getButtonName();
}
