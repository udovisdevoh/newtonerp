package newtonERP.viewers.viewables;

import java.util.Hashtable;

import newtonERP.module.AbstractAction;
import newtonERP.module.Module;
import newtonERP.module.generalEntity.FlagPool;
import newtonERP.module.generalEntity.ListOfValue;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author r3lacasgu
 * 
 *         Prompt view interface
 */
public interface PromptViewable
{
    /**
     * @return message du prompt, exemple: quel est votre nom?
     */
    public String getPromptMessage();

    /**
     * @return nom des input du prompt et leur valeur actuelles
     * @throws OrmException an exception that can occur in the orm
     */
    public Hashtable<String, String> getInputList() throws OrmException;

    /**
     * @return nom du bouton "ok"
     */
    public String getButtonCaption();

    /**
     * @param inputName nom d'un field
     * @return nom complete d'un field
     */
    public String getLabelName(String inputName);

    /**
     * @return the current action
     */
    public AbstractAction getCurrentAction();

    /**
     * @return the current module
     */
    public Module getCurrentModule();

    /**
     * @param fieldKeyName the field key name
     * @return If list of value exist, return it, else, return null
     */
    public ListOfValue tryMatchListOfValue(String fieldKeyName);

    /**
     * @return the flag pool list
     */
    public Hashtable<String, FlagPool> getFlagPoolList();

    /**
     * @param fieldName the field
     * @return true or false if the field is hidden or not
     */
    public boolean isFieldHidden(String fieldName);
}
