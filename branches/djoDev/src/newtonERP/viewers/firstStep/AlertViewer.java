package newtonERP.viewers.firstStep;

import newtonERP.viewers.viewables.AlertViewable;

/**
 * Alert viewer
 * @author Guillaume Lacasse
 */
public class AlertViewer
{
    /**
     * @param entity the alert entity
     * @return the html code (Javascript) of this alert
     */
    public static String getHtmlCode(AlertViewable entity)
    {
	String html = "";
	html += "\n<script language=\"JavaScript\">";
	html += "\n<!--";
	html += "\nalert(\"" + entity.getMessage() + "\")";
	html += "\n-->";
	html += "\n</script>";
	return html;
    }
}
