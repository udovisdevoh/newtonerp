package newtonERP.viewers;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractAction;
import newtonERP.viewers.viewables.ListViewable;

/**
 * 
 * @author Guillaume Lacasse, Pascal Lemay
 * 
 *         Represents the list viewer for listing data
 */
public class ListViewer
{
    /**
     * Creates the gtml code for the web page FIXME : Does not actually throws
     * the viewer exception.... Is this ok?
     * 
     * @param entity
     * @return html the html code
     * @throws ViewerException
     */
    public static String getHtmlCode(ListViewable entity)
	    throws ViewerException, Exception
    {
	String moduleName = entity.getSubmitModule().getClass().getSimpleName();

	String html = "";

	html += "<h1>" + entity.getTitle() + "</h1>";

	html += "<table>";

	html += getHeaderRow(entity.getColumnTitleList());

	html += getDataRowList(entity.getRowValues(), entity, moduleName);

	html += "</table>";

	html += getGlobalButtonList(entity.getGlobalActionButtonList(),
		moduleName, entity.getCurrentUserName());

	return html;
    }

    private static String getGlobalButtonList(
	    Hashtable<String, AbstractAction> globalActionButtonList,
	    String moduleName, String currentUserName)
    {
	String html = "";
	AbstractAction action;
	for (String buttonCaption : globalActionButtonList.keySet())
	{
	    action = globalActionButtonList.get(buttonCaption);
	    html += getButton(buttonCaption, moduleName, action.getClass()
		    .getSimpleName(), null, null, currentUserName);
	}
	return html;
    }

    private static String getDataRowList(Vector<Vector<String>> rowValues,
	    ListViewable entity, String moduleName)
    {
	String html = "<tr>";
	for (Vector<String> row : rowValues)
	{
	    for (String cell : row)
	    {
		html += "<td>" + cell + "</td>";
	    }

	    html += getSpecificButtonList(entity, moduleName, getKeyName(entity
		    .getColumnTitleList()), getKeyValue(row));
	}

	return html + "</tr>";
    }

    private static String getKeyValue(Vector<String> row)
    {
	for (String value : row)
	    return value;
	return null;
    }

    private static String getKeyName(Iterable<String> columnTitleList)
    {
	for (String value : columnTitleList)
	    return value;
	return null;
    }

    private static String getSpecificButtonList(ListViewable entity,
	    String moduleName, String key, String value)
    {
	String html = "";

	AbstractAction action;
	for (String buttonCaption : entity.getSpecificActionButtonList()
		.keySet())
	{
	    action = entity.getGlobalActionButtonList().get(buttonCaption);
	    html += getButton(buttonCaption, moduleName, action.getClass()
		    .getSimpleName(), key, value, entity.getCurrentUserName());
	}

	return html;
    }

    private static String getButton(String buttonCaption, String moduleName,
	    String actionName, String key, String value, String currentUserName)
    {
	if (key == null)
	    key = "noKeySpeficied";
	if (value == null)
	    value = "noValueSpecified";

	String formActionUrl = "/" + moduleName + "/" + actionName + "?user="
		+ currentUserName + "&" + key + "=" + value;

	String html = "";
	html += "<form method=\"POST\" action=\"" + formActionUrl + "\">";

	html += "<input type=\"submit\" value=\"" + buttonCaption + "\">";

	html += "</form>";

	return html;
    }

    /**
     * Gets the header row
     * 
     * @param columnList
     * @return html
     */
    public static String getHeaderRow(Iterable<String> columnList)
    {
	String html = "<tr>";
	for (String column : columnList)
	    html += "<td>" + column + "</td>";
	return html + "</tr>";
    }
}
