package newtonERP.viewers.viewerData;

import newtonERP.module.AbstractEntity;

/**
 * Modèle d'un selecteur de page
 * @author Guillaume
 */
public class PageSelector extends AbstractEntity
{
    private String currentUrl;
    private String currentSearchEntry;
    private String orderBy;
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
     * @param limit limite
     * @param offset offset
     * @param totalRowCount nonbre total de rangée
     * @param currentUrl url courant
     * @param currentSearchEntry texte de recherche
     * @param orderBy ordre de tri
     * @param rowPerPage row per page
     * @param pageCount page count
     * @throws Exception si ça fail
     */
    public PageSelector(int limit, int offset, int totalRowCount,
	    String currentUrl, String currentSearchEntry, String orderBy)
	    throws Exception
    {
	if (currentSearchEntry == null)
	    currentSearchEntry = "";

	if (orderBy == null)
	    orderBy = "";

	this.totalRowCount = totalRowCount;
	this.currentSearchEntry = currentSearchEntry;
	this.orderBy = orderBy;
	currentLimit = limit;
	currentOffset = offset;
	pageCount = (int) Math
		.ceil((double) (totalRowCount) / (double) (limit));
	this.currentUrl = currentUrl;
    }

    /**
     * @return nombre de pages
     */
    public int getPageCount()
    {
	return pageCount;
    }

    /**
     * @return nombre maximum de rangée par page
     */
    public int getCurrentLimit()
    {
	return currentLimit;
    }

    /**
     * @return url
     */
    public String getCurrentUrl()
    {
	return currentUrl;
    }

    /**
     * @return offset
     */
    public int getCurrentOffset()
    {
	return currentOffset;
    }

    /**
     * @return nombre total de rangée
     */
    public int getTotalRowCount()
    {
	return totalRowCount;
    }

    /**
     * @return recherche courante
     */
    public String getCurrentSearchEntry()
    {
	return currentSearchEntry;
    }

    /**
     * @return ordre de tri
     */
    public String getOrderBy()
    {
	return orderBy;
    }
}
