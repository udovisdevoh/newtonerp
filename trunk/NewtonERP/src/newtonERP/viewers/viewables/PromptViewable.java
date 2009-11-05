package newtonERP.viewers.viewables;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractAction;
import newtonERP.module.Module;
import newtonERP.module.generalEntity.FlagPool;
import newtonERP.module.generalEntity.ListOfValue;

/**
 * Prompt view interface
 * @author r3lacasgu
 */
public interface PromptViewable
{
    /**
     * @return message du prompt, exemple: quel est votre nom?
     */
    public String getPromptMessage();

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
     * @throws Exception lorsque l'obtention du module courant échoue
     */
    public Module getCurrentModule() throws Exception;

    /**
     * @param fieldKeyName the field key name
     * @return If list of value exist, return it, else, return null
     */
    public ListOfValue tryMatchListOfValue(String fieldKeyName);

    /**
     * @return the flag pool list
     */
    public Hashtable<String, FlagPool> getPositiveFlagPoolList();

    /**
     * @param fieldName the field
     * @return true or false if the field is hidden or not
     */
    public boolean isFieldHidden(String fieldName);

    /**
     * @return liste des messages d'alerte
     */
    public Vector<String> getAlertMessageList();

    /**
     * @return liste ordonnée (par ordre d'insertion) des clefs des champs
     *         hahaha la clef des champs... LOL
     */
    public Vector<String> getOrderedFieldNameList();

    /**
     * Retourne la valeur actuelle d'un champ
     * @param inputName nom du champ
     * @return valeur contenue dans le champ
     */
    public String getInputValue(String inputName);
}
