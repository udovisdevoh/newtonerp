package newtonERP.viewers.firstStep;

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
	public static String getHtmlCode(
			newtonERP.viewers.viewables.AlertViewable entity)
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
