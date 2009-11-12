package newtonERP.viewers.viewerData;



/**
 * Représente un département dans une compagnie
 * @author Guillaume
 */
public class GridViewerData extends BaseViewer
{
    private GridCaseData[] header;
    private GridCaseData[] leftHeader;
    private GridCaseData[][] cases;
    /**
     * @throws Exception si création fails
     */
    public GridViewerData() throws Exception
    {
	super();
    }

    /**
     * @return the header
     */
    public GridCaseData[] getHeader()
    {
	return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(GridCaseData[] header)
    {
	this.header = header;
    }

    /**
     * @return the leftHeader
     */
    public GridCaseData[] getLeftHeader()
    {
	return leftHeader;
    }

    /**
     * @param leftHeader the leftHeader to set
     */
    public void setLeftHeader(GridCaseData[] leftHeader)
    {
	this.leftHeader = leftHeader;
    }

    /**
     * @return the cases
     */
    public GridCaseData[][] getCases()
    {
	return cases;
    }

    /**
     * @param cases the cases to set
     */
    public void setCases(GridCaseData[][] cases)
    {
	this.cases = cases;
    }
}