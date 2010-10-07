package newtonERP.viewers.viewables;

import java.util.Vector;

/**
 * Prompt view interface
 * @author r3lacasgu
 */
public interface PromptViewable
{
	/**
	 * @param inputName nom d'un field
	 * @return nom complete d'un field
	 */
	String getLabelName(String inputName);

	/**
	 * @return the current action
	 */
	newtonERP.module.AbstractAction getCurrentAction();

	/**
	 * @return the current module
	 */
	newtonERP.module.Module getCurrentModule();

	/**
	 * @param fieldKeyName the field key name
	 * @return If list of value exist, return it, else, return null
	 */
	newtonERP.module.generalEntity.ListOfValue tryMatchListOfValue(
			String fieldKeyName);

	/**
	 * @return the flag pool list
	 */
	newtonERP.module.generalEntity.FlagPool getPositiveFlagPoolList();

	/**
	 * @return liste des messages d'alerte
	 */
	Vector<String> getAlertMessageList();

	/**
	 * Retourne la valeur actuelle d'un champ
	 * @param inputName nom du champ
	 * @return valeur contenue dans le champ
	 */
	String getInputValue(String inputName);

	/**
	 * @return url facultatif de retour vers autre page
	 */
	String getBackLinkUrl();

	/**
	 * @return nom du url facultatif de retour
	 */
	String getBackLinkName();

	/**
	 * @return Liste des messages normaux
	 */
	Vector<String> getNormalMessageList();

	/**
	 * @param inputName nom du champ
	 * @return is le champ est un check box (bool)
	 */
	boolean isMatchCheckBox(String inputName);

}
