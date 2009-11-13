package newtonERP.viewers.secondStep;

import newtonERP.common.NaturalMap;
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

	html += "<div class=\"scrollableDivPair\" style=\"border-color:"
		+ ColorViewer.getColor(entity.getTitle()) + "\"><div><ul>";

	if (entity.getTitleUrl() != null)
	    html += "<a href=\"" + entity.getTitleUrl() + "\">";

	if (entity.getTitle() != null)
	    html += "<h4>" + entity.getTitle() + "</h4>";

	if (entity.getTitleUrl() != null)
	    html += "</a>";

	for (String linkName : entity.getLinkList().keySet())
	    html += "<li><a href=\"" + entity.getLinkList().get(linkName)
		    + "\">" + linkName + "</a></li>";

	for (NaturalMap<String, String> linkPair : entity.getLinkGroupList())
	{
	    int counter = 0;
	    String styleModifier;
	    html += "<li>";
	    for (String linkName : linkPair.getKeyList())
	    {
		if (counter > 0)
		    styleModifier = " class=\"linkPairTarget\"";
		else
		    styleModifier = "";
		html += "<a href=\"" + linkPair.get(linkName) + "\""
			+ styleModifier + ">" + linkName + "</a>";

		if (counter == linkPair.size() - 1)
		    html += "</li>";
		else
		    html += " : ";
		counter++;
	    }
	    html += "</li>";
	}

	html += "</ul></div></div>";

	return html;
    }
}
