package newtonERP.viewers.secondStep;

import newtonERP.common.ActionLink;
import newtonERP.common.NaturalMap;
import newtonERP.viewers.secondStep.colorViewer.ColorViewer;
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
     * @throws Exception si obtention fail
     */
    public static String getHtmlContent(ScrollListViewable entity)
	    throws Exception
    {
	String html = "";

	html += "<div class=\"scrollableDivPair\" style=\"border-color:"
		+ ColorViewer.getColor(entity.getTitle()) + "\"><div>";

	if (entity.getTitleUrl() != null)
	    html += "<a href=\"" + entity.getTitleUrl() + "\">";

	if (entity.getTitle() != null)
	    html += "<b>" + entity.getTitle() + "</b>";

	if (entity.getTitleUrl() != null)
	    html += "</a>";

	html += "<ul>";

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
	    // html += "</li>";
	}

	html += "</ul>";

	html = html.replace("<ul></ul>", "");

	for (ActionLink actionLink : entity.getActionLinkList())
	    html += LinkViewer.getHtmlCode(actionLink);

	html += "</div></div>";

	return html;
    }
}
