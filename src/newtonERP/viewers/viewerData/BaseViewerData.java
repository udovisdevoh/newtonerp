package newtonERP.viewers.viewerData;

import java.util.Vector;

import newtonERP.common.ActionLink;

/**
 * viewer servant a standardisé nos viewer
 * @author CloutierJo
 */
public class BaseViewerData extends newtonERP.module.AbstractEntity
{
	private String title;

	private newtonERP.common.ActionLink globalActions = new Vector<ActionLink>();

	private String alertMessageList = new Vector<String>();

	private String normalMessageList = new Vector<String>();

	private newtonERP.common.ActionLink backLink;

	private String complementaryInfoLineList;

	/**
	 * constructeur vide
	 */
	public BaseViewerData()
	{
		super();
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param globalActions the globalActions to set
	 */
	public void setGlobalActions(Vector<ActionLink> globalActions)
	{
		this.globalActions = globalActions;
	}

	/**
	 * @return the globalActions
	 */
	public newtonERP.common.ActionLink getGlobalActions()
	{
		return globalActions;
	}

	/**
	 * @param globalAction the globalActions to add
	 */
	public void addGlobalActions(newtonERP.common.ActionLink globalAction)
	{
		globalActions.add(globalAction);
	}

	/**
	 * @param globalActionName the globalActionName to remove
	 */
	public void removeGlobalActions(String globalActionName)
	{
		for (Iterator<ActionLink> gActionIT = globalActions.iterator(); gActionIT
				.hasNext();)
		{
			ActionLink gAction = gActionIT.next();
			if (gAction.getName().equals(globalActionName))
				gActionIT.remove();
		}
	}

	/**
	 * @return the alertMessageList
	 */
	public Vector<String> getAlertMessageList()
	{
		return alertMessageList;
	}

	/**
	 * @param alertMessageList the alertMessageList to set
	 */
	public void setAlertMessageList(Vector<String> alertMessageList)
	{
		this.alertMessageList = alertMessageList;
	}

	/**
	 * @param message message à ajouter
	 */
	public final void addAlertMessage(String message)
	{
		if (message.length() > 0)
			alertMessageList.add(message);
	}

	/**
	 * @return the normalMessageList
	 */
	public Vector<String> getNormalMessageList()
	{
		return normalMessageList;
	}

	/**
	 * @param normalMessageList the normalMessageList to set
	 */
	public void setNormalMessageList(Vector<String> normalMessageList)
	{
		this.normalMessageList = normalMessageList;
	}

	/**
	 * @param message nouveau message normal
	 */
	public void addNormalMessage(String message)
	{
		normalMessageList.add(message);
	}

	/**
	 * @return the backLink
	 */
	public newtonERP.common.ActionLink getBackLink()
	{
		return backLink;
	}

	/**
	 * @param backLink the backLink to set
	 */
	public void setBackLink(newtonERP.common.ActionLink backLink)
	{
		this.backLink = backLink;
	}

	/**
	 * @param complementaryInfoLine ligne d'information complémentaire à ajouter
	 */
	public void addComplementaryInfoLine(String complementaryInfoLine)
	{
		// TODO Auto-generated method stub
		getComplementaryInfoLineList().add(complementaryInfoLine);
	}

	/**
	 * @return liste des lignes d'information complémentaire
	 */
	public Vector<String> getComplementaryInfoLineList()
	{
		if (complementaryInfoLineList == null)
			complementaryInfoLineList = new Vector<String>();
		return complementaryInfoLineList;
	}

}
