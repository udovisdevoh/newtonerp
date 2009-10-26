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
 */
public interface PromptViewable
{
    /**
     * @return message du prompt, exemple: quel est votre nom?
     */
    public String getPromptMessage();

    /**
     * @return nom des input du prompt et leur valeur actuelles
     */
    public Hashtable<String, String> getInputList() throws OrmException;

    /**
     * @return nom du bouton "ok"
     */
    public String getButtonCaption();

    /**
     * @return Can be dummy if entity is not an Orm Entity
     */
    public String getPrimaryKeyName();

    /**
     * @return Can be dummy if entity is not an Orm Entity
     */
    public String getPrimaryKeyValue();

    /**
     * @param inputName nom d'un field
     * @return nom complete d'un field
     */
    public String getLabelName(String inputName);

    public AbstractAction getCurrentAction();

    public Module getCurrentModule();

    /**
     * @param fieldKeyName
     * @return If list of value exist, return it, else, return null
     */
    public ListOfValue tryMatchListOfValue(String fieldKeyName);

    public Hashtable<String, FlagPool> getFlagPoolList();

    public boolean isFieldHidden(String fieldName);
}
