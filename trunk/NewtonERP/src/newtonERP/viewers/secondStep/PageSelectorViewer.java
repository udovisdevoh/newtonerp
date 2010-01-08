package newtonERP.viewers.secondStep;

import newtonERP.viewers.viewerData.PageSelector;

/**
 * Sert à voir les liste de pages
 * @author Guillaume Lacasse
 */
public class PageSelectorViewer
{

    /**
     * @param pageSelector selecteur de page
     * @return code html
     */
    public static String getHtmlCode(PageSelector pageSelector)
    {
	String html = "";

	int pageCount = pageSelector.getPageCount();
	int limit = pageSelector.getCurrentLimit();
	int offset = pageSelector.getCurrentOffset();

	if (offset > 0)
	    html += "<a href='" + pageSelector.getCurrentUrl() + "?limit="
		    + limit + "&offset=" + (offset - limit) + "'>&lt;</a>";
	else
	    html += "&lt;";

	for (int linkCounter = 0; linkCounter < pageCount; linkCounter++)
	{
	    int currentLinkOffset = limit * linkCounter;

	    if (currentLinkOffset != offset)
		html += " <a href='" + pageSelector.getCurrentUrl() + "?limit="
			+ limit + "&offset=" + linkCounter * limit + "'>";
	    else
		html += " ";

	    html += (linkCounter + 1);

	    if (currentLinkOffset != offset)
		html += "</a>";
	}

	if (offset + limit < pageSelector.getTotalRowCount())
	    html += " <a href='" + pageSelector.getCurrentUrl() + "?limit="
		    + limit + "&offset=" + (offset + limit) + "'>&gt;</a>";
	else
	    html += " &gt;";

	return html;
    }
}
