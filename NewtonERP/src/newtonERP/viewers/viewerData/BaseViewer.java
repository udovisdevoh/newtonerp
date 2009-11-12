package newtonERP.viewers.viewerData;

import java.util.Vector;

import newtonERP.common.ActionLink;
import newtonERP.module.AbstractEntity;

public class BaseViewer extends AbstractEntity
{

    private String title;
    private Vector<ActionLink> globalActions = new Vector<ActionLink>();

    public BaseViewer() throws Exception
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

}