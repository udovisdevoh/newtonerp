package newtonERP.viewers;

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

	if (entity.getTitle() != null)
	    html += "<h4>" + entity.getTitle() + "</h4>";

	for (String linkName : entity.getLinkList().keySet())
	    html += "<a href=\"" + entity.getLinkList().get(linkName) + "\">"
		    + linkName + "</a><br />";

	html += "</div></div>";

	return html;
    }
}
