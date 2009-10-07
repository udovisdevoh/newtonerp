package newtonERP.viewers;

import java.util.HashSet;

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

	html += getDataRowList(entity.getRowValues(), entity);

	html += "</table>";

	return html;
    }

    private static String getDataRowList(Iterable<HashSet<String>> rowValues,
	    ListViewable entity)
    {
	String html = "<tr>";
	for (HashSet<String> row : rowValues)
	    for (String cell : row)
		html += "<td>" + cell + "</td>";

	html += getSpecificButtonList(entity);

	return html + "</tr>";
    }

    private static String getSpecificButtonList(ListViewable entity)
    {
	String html;
    }

    public static String getHeaderRow(Iterable<String> columnList)
    {
	String html = "<tr>";
	for (String column : columnList)
	    html += "<td>" + column + "</td>";
	return html + "</tr>";
    }
}
