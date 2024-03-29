package newtonERP.viewers.viewerData; 
 // TODO: clean up that file

import java.util.Hashtable;

import newtonERP.common.ActionLink;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.Module;
import newtonERP.module.generalEntity.FlagPool;
import newtonERP.module.generalEntity.ListOfValue;

/**
 * data du promptViewer
 * 
 * @author CloutierJo
 */
public class PromptViewerData extends BaseViewerData {
	private ActionLink buttonAction;
	private AbstractEntity data;
	private boolean isReadOnly;

	/**
	 * default constructor
	 */
	public PromptViewerData() {
		super();
	}

	/**
	 * @return nom du bouton "ok"
	 */
	public ActionLink getButtonAction() {
		return buttonAction;
	}

	/**
	 * @param buttonAction nom du bouton "ok"
	 */
	public void setButtonAction(ActionLink buttonAction) {
		this.buttonAction = buttonAction;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(AbstractEntity data) {
		this.data = data;
		if(getTitle() == null){
			setTitle(data.getSystemName());
		}
	}

	/**
	 * @return the data
	 */
	public AbstractEntity getData() {
		return data;
	}

	// *************************************************************************
	/**
	 * @param inputName nom du champ
	 * @return is le champ est un check box (bool)
	 */
	@Override
	public boolean isMatchCheckBox(String inputName) {
		return false;
	}

	/**
	 * @param fieldKeyName the field key name
	 * @return If list of value exist, return it, else, return null
	 */
	@Override
	public ListOfValue tryMatchListOfValue(String fieldKeyName) {
		return null;
	}

	/**
	 * @return the flag pool list
	 */
	public Hashtable<String, FlagPool> getPositiveFlagPoolList() {
		return null;
	}

	/**
	 * Retourne la valeur actuelle d'un champ
	 * 
	 * @param inputName nom du champ
	 * @return valeur contenue dans le champ
	 */
	public String getInputValue(String inputName) {
		return null;
	}

	/**
	 * @param inputName nom d'un field
	 * @return nom complete d'un field
	 */
	@Override
	public String getLabelName(String inputName) {
		return null;
	}

	/**
	 * @return the current action
	 */
	@Override
	public AbstractAction getCurrentAction() {
		return null;
	}

	/**
	 * @return the current module
	 */
	@Override
	public Module getCurrentModule() {
		return null;
	}

	/**
	 * @param isReadOnly si entité est présentement readonly dans le contexte actuel
	 */
	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	/**
	 * @return si entité est présentement readonly
	 */
	public boolean isReadOnly() {
		return isReadOnly;
	}
}
