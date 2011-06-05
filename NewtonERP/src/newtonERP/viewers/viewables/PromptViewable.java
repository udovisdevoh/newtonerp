package newtonERP.viewers.viewables;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractAction;
import newtonERP.module.Module;
import newtonERP.module.generalEntity.FlagPool;
import newtonERP.module.generalEntity.ListOfValue;

/**
 * Prompt view interface
 * 
 * @author r3lacasgu
 */
public interface PromptViewable {
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
	public Hashtable<String, FlagPool> getPositiveFlagPoolList();

	/**
	 * @return liste des messages d'alerte
	 */
	public Vector<String> getAlertMessageList();

	/**
	 * Retourne la valeur actuelle d'un champ
	 * 
	 * @param inputName nom du champ
	 * @return valeur contenue dans le champ
	 */
	public String getInputValue(String inputName);

	/**
	 * @return url facultatif de retour vers autre page
	 */
	public String getBackLinkUrl();

	/**
	 * @return nom du url facultatif de retour
	 */
	public String getBackLinkName();

	/**
	 * @return Liste des messages normaux
	 */
	public Vector<String> getNormalMessageList();

	/**
	 * @param inputName nom du champ
	 * @return is le champ est un check box (bool)
	 */
	public boolean isMatchCheckBox(String inputName);
}
