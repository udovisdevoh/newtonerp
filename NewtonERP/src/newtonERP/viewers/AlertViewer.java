package newtonERP.viewers;

import newtonERP.viewers.viewables.AlertViewable;

public class AlertViewer
{
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
