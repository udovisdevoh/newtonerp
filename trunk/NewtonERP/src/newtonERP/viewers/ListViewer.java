package newtonERP.viewers;

import java.util.Hashtable;
import java.util.Map;
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

	html += "<h2>Liste des " + entity.getInternalElementName() + "</h2>";

	html += "<table class=\"ListViewerTable\" border=\"0\" cellpadding=\"3\" cellspacing=\"0\">";

	html += getHeaderRow(entity.getColumnTitleList());

	html += getDataRowList(entity.getRowList(), entity, entity
		.getCurrentModule());

	html += "</table>";

	html += getGlobalButtonList(entity.getGlobalActionButtonList(), entity
		.getCurrentModule(), entity);

	return html;
    }

    private static String getGlobalButtonList(
	    Hashtable<String, AbstractAction> globalActionButtonList,
	    Module module, ListViewable entity)
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
		    module, entity.getButtonConfirmList().contains(actionName));
	}
	return html;
    }

    private static String getDataRowList(Vector<Map<String, String>> rowValues,
	    ListViewable listEntity, Module module) throws ModuleException
    {

	String html = "";
	int rowNumber = 0;
	for (Map<String, String> row : rowValues)
	{
	    html += "\n<tr>";
	    for (String cellKey : row.keySet())
	    {
		html += "<td>" + row.get(cellKey) + "</td>";
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
		    module, listEntity.getButtonConfirmList().contains(
			    actionName));

	    html += "</td>";
	}

	return html;
    }

    private static String getButton(String buttonCaption, String actionName,
	    String key, String value, AbstractAction action, Module module,
	    boolean isConfirm)
    {
	/*
	 * String formActionUrl = "/" + moduleName + "/" + actionName + "?user="
	 * + currentUserName + "&" + key + "=" + value;
	 */

	String onClickConfirm = "";

	if (isConfirm)
	    onClickConfirm = getOnClickConfirm(actionName, value);

	String formActionUrl = Servlet.makeLink(module, action);

	String html = "";
	html += "<form method=\"GET\" name=\"" + actionName + "\" action=\""
		+ formActionUrl + "\">";

	if (key != null && value != null)
	    html += "<input type=\"hidden\" name=\"" + key + "\" value=\""
		    + value + "\">";

	html += "<input type=\"submit\" " + onClickConfirm + " value=\""
		+ buttonCaption + "\">";

	html += "</form>";

	return html;
    }

    private static String getOnClickConfirm(String actionName, String value)
    {
	String html = "";

	html += "onClick=\"return confirm(\'Voulez-vous vraiment ";
	html += actionName;
	html += " " + value;
	html += "?\')";

	html += "\"";

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
	    html += "<td class=\"ListViewerTableHeader\">" + column + "</td>";
	return html + "</tr>";
    }
}
