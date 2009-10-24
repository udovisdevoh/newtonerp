package newtonERP.viewers;

import newtonERP.viewers.viewables.ForwardViewable;

public class ForwardViewer
{

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
