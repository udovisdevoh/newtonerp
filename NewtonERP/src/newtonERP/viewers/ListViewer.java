package newtonERP.viewers;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractAction;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;
import newtonERP.module.exception.ActionNotFoundException;
import newtonERP.module.exception.ModuleException;
import newtonERP.serveur.Servlet;
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
	String html = "";

	html += "<h1>Liste des " + entity.getInternalElementName() + "</h1>";

	html += "<table border=\"1\" cellpadding=\"3\" cellspacing=\"0\" style=\"background-color:#FFF\">";

	html += getHeaderRow(entity.getColumnTitleList());

	html += getDataRowList(entity.getRowValues(), entity, entity
		.getCurrentModule());

	html += "</table>";

	html += getGlobalButtonList(entity.getGlobalActionButtonList(), entity
		.getCurrentModule());

	return html;
    }

    private static String getGlobalButtonList(
	    Hashtable<String, AbstractAction> globalActionButtonList,
	    Module module)
    {
	String html = "";
	AbstractAction action;
	String actionName;
	for (String buttonCaption : globalActionButtonList.keySet())
	{
	    action = globalActionButtonList.get(buttonCaption);

	    if (action instanceof BaseAction)
		actionName = ((BaseAction) (action)).getActionName();
	    else
		actionName = action.getClass().getSimpleName();

	    html += getButton(buttonCaption, actionName, null, null, action,
		    module);
	}
	return html;
    }

    private static String getDataRowList(Vector<Vector<String>> rowValues,
	    ListViewable listEntity, Module module) throws ModuleException
    {

	String html = "";
	int rowNumber = 0;
	for (Vector<String> row : rowValues)
	{
	    html += "\n<tr>";
	    for (String cell : row)
	    {
		html += "<td>" + cell + "</td>";
	    }

	    html += getSpecificButtonList(listEntity, listEntity.getKeyName(),
		    listEntity.getKeyValue(rowNumber), module);

	    html += "</tr>";
	    rowNumber++;
	}
	return html;
    }

    private static String getSpecificButtonList(ListViewable listEntity,
	    String key, String value, Module module) throws ModuleException
    {
	String html = "", actionName;

	AbstractAction action;
	for (String buttonCaption : listEntity.getSpecificActionButtonList()
		.keySet())
	{
	    html += "<td>";
	    action = listEntity.getSpecificActionButtonList()
		    .get(buttonCaption);
	    if (action == null)
		throw new ActionNotFoundException("Action is null");
	    else if (action instanceof BaseAction)
		actionName = ((BaseAction) (action)).getActionName();
	    else
		actionName = action.getClass().getSimpleName();
	    html += getButton(buttonCaption, actionName, key, value, action,
		    module);

	    html += "</td>";
	}

	return html;
    }

    private static String getButton(String buttonCaption, String actionName,
	    String key, String value, AbstractAction action, Module module)
    {
	/*
	 * String formActionUrl = "/" + moduleName + "/" + actionName + "?user="
	 * + currentUserName + "&" + key + "=" + value;
	 */

	String formActionUrl = Servlet.makeLink(module, action);

	String html = "";
	html += "<form method=\"GET\" name=\"" + actionName + "\" action=\""
		+ formActionUrl + "\">";

	if (key != null && value != null)
	    html += "<input type=\"hidden\" name=\"" + key + "\" value=\""
		    + value + "\">";

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
