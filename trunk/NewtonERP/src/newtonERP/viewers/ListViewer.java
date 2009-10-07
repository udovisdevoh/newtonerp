package newtonERP.viewers;

import java.util.HashSet;

import newtonERP.module.AbstractAction;
import newtonERP.viewers.viewables.ListViewable;

public class ListViewer
{
    public static String getHtmlCode(ListViewable entity)
	    throws ViewerException
    {
	String moduleName = entity.getSubmitModule().getClass().getSimpleName();
	String actionName = entity.getSubmitAction().getClass().getSimpleName();

	String html = "";

	html += "<h1>" + entity.getTitle() + "</h1>";

	html += "<table>";

	html += getHeaderRow(entity.getColumnTitleList());

	html += getDataRowList(entity.getRowValues(), entity, moduleName,
		actionName);

	html += "</table>";

	return html;
    }

    private static String getDataRowList(Iterable<HashSet<String>> rowValues,
	    ListViewable entity, String moduleName, String actionName)
    {
	String html = "<tr>";
	for (HashSet<String> row : rowValues)
	{
	    for (String cell : row)
	    {
		html += "<td>" + cell + "</td>";
	    }

	    html += getSpecificButtonList(entity, moduleName, actionName,
		    getKeyName(entity.getColumnTitleList()), getKeyValue(row));
	}

	return html + "</tr>";
    }

    private static String getKeyValue(HashSet<String> row)
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
	    String moduleName, String actionName, String key, String value)
    {
	String html = "";

	AbstractAction action;
	for (String buttonCaption : entity.getSpecificActionButtonList()
		.keySet())
	{
	    action = entity.getGlobalActionButtonList().get(buttonCaption);
	    html += getButton(buttonCaption, moduleName, actionName, key,
		    value, entity.getCurrentUserName());
	}

	return html;
    }

    private static String getButton(String buttonCaption, String moduleName,
	    String actionName, String key, String value, String currentUserName)
    {
	String formActionUrl = "/" + moduleName + "/" + actionName + "?user="
		+ currentUserName + "&" + key + "=" + value;

	String html = "";
	html += "<form method=\"GET\" action=\"" + formActionUrl + "\">";

	html += "<input type=\"submit\" value=\"" + buttonCaption + "\">";

	html += "</form>";

	return html;
    }

    public static String getHeaderRow(Iterable<String> columnList)
    {
	String html = "<tr>";
	for (String column : columnList)
	    html += "<td>" + column + "</td>";
	return html + "</tr>";
    }
}
