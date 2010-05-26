package newtonERP.viewers.viewerData;

import java.util.Iterator;
import java.util.Vector;

import newtonERP.common.ActionLink;
import newtonERP.module.AbstractEntity;

/**
 * viewer servant a standardisé nos viewer
 * @author CloutierJo
 * 
 */
public class BaseViewerData extends AbstractEntity
{
    private String title;
    private Vector<ActionLink> globalActions = new Vector<ActionLink>();
    private Vector<String> alertMessageList = new Vector<String>();
    private Vector<String> normalMessageList = new Vector<String>();
    private ActionLink backLink;

    /**
     * constructeur vide
     * @throws Exception remonte
     */
    public BaseViewerData() throws Exception
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
    public Vector<ActionLink> getGlobalActions()
    {
	return globalActions;
    }

    /**
     * @param globalAction the globalActions to add
     */
    public void addGlobalActions(ActionLink globalAction)
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
    public ActionLink getBackLink()
    {
	return backLink;
    }

    /**
     * @param backLink the backLink to set
     */
    public void setBackLink(ActionLink backLink)
    {
	this.backLink = backLink;
    }

}