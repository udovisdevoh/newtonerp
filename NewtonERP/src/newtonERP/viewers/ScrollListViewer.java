package newtonERP.viewers;

import java.util.TreeMap;

import newtonERP.viewers.viewables.ScrollListViewable;

/**
 * Sert à afficher des listes de lien et de texte en HTML avec une scrollbar
 * @author Guillaume
 */
public class ScrollListViewer
{
    /**
     * @param entity liste à afficher
     * @return code HTML
     */
    public static String getHtmlContent(ScrollListViewable entity)
    {
	String html = "";

	html += "<div class=\"scrollableDivPair\"><div>";

	if (entity.getTitleUrl() != null)
	    html += "<a href=\"" + entity.getTitleUrl() + "\">";

	if (entity.getTitle() != null)
	    html += "<h4>" + entity.getTitle() + "</h4>";

	if (entity.getTitleUrl() != null)
	    html += "</a>";

	for (String linkName : entity.getLinkList().keySet())
	    html += "<a href=\"" + entity.getLinkList().get(linkName) + "\">"
		    + linkName + "</a><br />";

	for (TreeMap<String, String> linkPair : entity.getLinkGroupList())
	{
	    int counter = 0;
	    for (String linkName : linkPair.keySet())
	    {
		html += "<a href=\"" + linkPair.get(linkName) + "\">"
			+ linkName + "</a>";

		if (counter == linkPair.size() - 1)
		    html += "<br>";
		else
		    html += " : ";
		counter++;
	    }
	}

	html += "</div></div>";

	return html;
    }
}
