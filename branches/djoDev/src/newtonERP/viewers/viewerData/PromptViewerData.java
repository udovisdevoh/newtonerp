package newtonERP.viewers.viewerData;

/**
 * data du promptViewer
 * @author CloutierJo
 */
public class PromptViewerData extends BaseViewerData
{
	private newtonERP.common.ActionLink buttonAction;

	private newtonERP.module.AbstractEntity data;

	private boolean isReadOnly;

	/**
	 * default constructor
	 */
	public PromptViewerData()
	{
		super();
	}

	/**
	 * @return nom du bouton "ok"
	 */
	public newtonERP.common.ActionLink getButtonAction()
	{
		return buttonAction;
	}

	/**
	 * @param buttonAction nom du bouton "ok"
	 */
	public void setButtonAction(newtonERP.common.ActionLink buttonAction)
	{
		this.buttonAction = buttonAction;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(newtonERP.module.AbstractEntity data)
	{
		this.data = data;
		if (getTitle() == null)
			setTitle(data.getSystemName());
	}

	/**
	 * @return the data
	 */
	public newtonERP.module.AbstractEntity getData()
	{
		return data;
	}

	/**
	 * *************************************************************************
	 * 
	 * @param inputName nom du champ
	 * @return is le champ est un check box (bool)
	 */
	public boolean isMatchCheckBox(String inputName)
	{
		return false;
	}

	/**
	 * @param fieldKeyName the field key name
	 * @return If list of value exist, return it, else, return null
	 */
	public newtonERP.module.generalEntity.ListOfValue tryMatchListOfValue(
			String fieldKeyName)
	{
		return null;
	}

	/**
	 * @return the flag pool list
	 */
	public newtonERP.module.generalEntity.FlagPool getPositiveFlagPoolList()
	{
		return null;
	}

	/**
	 * Retourne la valeur actuelle d'un champ
	 * @param inputName nom du champ
	 * @return valeur contenue dans le champ
	 */
	public String getInputValue(String inputName)
	{
		return null;
	}

	/**
	 * @param inputName nom d'un field
	 * @return nom complete d'un field
	 */
	public String getLabelName(String inputName)
	{
		return null;
	}

	/**
	 * @return the current action
	 */
	public newtonERP.module.AbstractAction getCurrentAction()
	{
		return null;
	}

	/**
	 * @return the current module
	 */
	public newtonERP.module.Module getCurrentModule()
	{
		return null;
	}

	/**
	 * @param isReadOnly si entité est présentement readonly dans le contexte
	 *            actuel
	 */
	public void setReadOnly(boolean isReadOnly)
	{
		this.isReadOnly = isReadOnly;
	}

	/**
	 * @return si entité est présentement readonly
	 */
	public boolean isReadOnly()
	{
		return isReadOnly;
	}

}
