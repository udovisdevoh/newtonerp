package newtonERP.viewers.firstStep;

import newtonERP.viewers.viewables.ForwardViewable;

/**
 * Used to forward to a new page
 * @author Guillaume Lacasse
 */
public class ForwardViewer
{

    /**
     * @param entity the entity we want to go to
     * @return the entity
     */
    public static String getHtmlCode(ForwardViewable entity)
    {
	String html = "";

	html += "\n<script language=\"JavaScript\">";
	html += "\n<!--";
	html += "\nwindow.location = \"" + entity.getForwardingUrl() + "\"";
	html += "\n//-->";
	html += "\n</script>";
	html += "\n<noscript>";
	html += "\n<a href=\"" + entity.getForwardingUrl()
		+ "\">cliquez ici si vous n'êtes pas redirigé</a>";
	html += "\n</noscript>";

	return html;
    }
}
