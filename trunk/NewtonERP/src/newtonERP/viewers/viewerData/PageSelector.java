package newtonERP.viewers.viewerData;

import newtonERP.module.AbstractEntity;

/**
 * Modèle d'un selecteur de page
 * @author Guillaume
 */
public class PageSelector extends AbstractEntity
{
    private String currentUrl;
    private int currentLimit;
    private int pageCount;
    private int currentOffset;
    private int totalRowCount;

    /**
     * @throws Exception si ça fail
     */
    public PageSelector() throws Exception
    {
	super();
	// TODO Auto-generated constructor stub
	currentLimit = 15;
	pageCount = 100;
    }

    /**
     * @param rowPerPage row per page
     * @param pageCount page count
     * @throws Exception si ça fail
     */
    public PageSelector(int limit, int offset, int totalRowCount,
	    String currentUrl) throws Exception
    {
	this.totalRowCount = totalRowCount;
	currentLimit = limit;
	currentOffset = offset;
	pageCount = (int) Math
		.ceil((double) (totalRowCount) / (double) (limit));
	this.currentUrl = currentUrl;
    }

    public int getPageCount()
    {
	return pageCount;
    }

    public int getCurrentLimit()
    {
	return currentLimit;
    }

    public String getCurrentUrl()
    {
	return currentUrl;
    }

    public int getCurrentOffset()
    {
	return currentOffset;
    }

    public int getTotalRowCount()
    {
	return totalRowCount;
    }
}
