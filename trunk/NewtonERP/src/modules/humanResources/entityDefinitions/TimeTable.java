package modules.humanResources.entityDefinitions;

import java.util.Vector;

import newtonERP.common.ActionLink;
import newtonERP.module.AbstractEntity;

/**
 * Représente un département dans une compagnie
 * @author Guillaume
 */
public class TimeTable extends AbstractEntity
{
    private CaseTable[] header;
    private CaseTable[] leftHeader;
    private CaseTable[][] cases;
    private CaseTable defaultCase = new CaseTable();
    private String title;
    private Vector<ActionLink> globalActions = new Vector<ActionLink>();

    /**
     * @throws Exception si création fails
     */
    public TimeTable() throws Exception
    {
	super();
    }

    /**
     * @return the header
     */
    public CaseTable[] getHeader()
    {
	return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(CaseTable[] header)
    {
	this.header = header;
    }

    /**
     * @return the leftHeader
     */
    public CaseTable[] getLeftHeader()
    {
	return leftHeader;
    }

    /**
     * @param leftHeader the leftHeader to set
     */
    public void setLeftHeader(CaseTable[] leftHeader)
    {
	this.leftHeader = leftHeader;
    }

    /**
     * @return the cases
     */
    public CaseTable[][] getCases()
    {
	return cases;
    }

    /**
     * @param cases the cases to set
     */
    public void setCases(CaseTable[][] cases)
    {
	this.cases = cases;
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

    public void setDefaultCase(CaseTable defaultCase)
    {
	this.defaultCase = defaultCase;
    }

    public CaseTable getDefaultCase()
    {
	return defaultCase;
    }

}