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

	for (int linkCounter = 0; linkCounter < pageCount; linkCounter++)
	{
	    int currentLinkOffset = limit * linkCounter;

	    if (currentLinkOffset != pageSelector.getCurrentOffset())
		html += " <a href='" + pageSelector.getCurrentUrl() + "?limit="
			+ limit + "&offset=" + linkCounter * limit + "'>";
	    else
		html += " ";

	    html += (linkCounter + 1);

	    if (currentLinkOffset != pageSelector.getCurrentOffset())
		html += "</a>";
	}

	return html;
    }
}
