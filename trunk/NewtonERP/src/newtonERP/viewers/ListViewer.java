package newtonERP.viewers;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.common.NaturalMap;
import newtonERP.module.AbstractAction;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;
import newtonERP.module.exception.ActionNotFoundException;
import newtonERP.module.exception.ModuleException;
import newtonERP.serveur.Servlet;
import newtonERP.viewers.viewables.ListViewable;

/**
 * Represents the list viewer for listing data
 * @author Guillaume Lacasse, Pascal Lemay
 */
public class ListViewer
{
    /**
     * Creates the gtml code for the web page
     * 
     * @param listEntity the entity to view in list
     * @return html the html code
     * @throws ViewerException an exception that can occur in the viewer
     * @throws Exception general exception
     */
    public static String getHtmlCode(ListViewable listEntity)
	    throws ViewerException, Exception
    {
	String html = "";

	html += "<h2>Liste des " + listEntity.getVisibleInternalElementName()
		+ "(s)" + "</h2>";

	html += "<table class=\"ListViewerTable\" border=\"0\" cellpadding=\"3\" cellspacing=\"0\">";

	html += getDataRowList(listEntity.getRowList(), listEntity, listEntity
		.getCurrentModule());

	html += "</table>";

	html += getGlobalButtonList(listEntity.getGlobalActionButtonList(),
		listEntity.getCurrentModule(), listEntity);

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

	    html += getButton(buttonCaption, null, null, action, module, entity
		    .getButtonConfirmList().contains(actionName), entity
		    .getVisibleInternalElementName());
	}
	return html;
    }

    private static String getDataRowList(
	    Vector<NaturalMap<String, String>> rowValues,
	    ListViewable listEntity, Module module) throws ModuleException
    {

	String html = "";
	int rowNumber = 0;
	String cellValue = null;
	String isHeader;
	String moneyStyleModifier;

	for (NaturalMap<String, String> row : rowValues)
	{
	    html += "\n<tr>";
	    for (String cellKey : row.getKeyList())
	    {
		cellValue = row.get(cellKey);

		if (cellValue == null || cellValue.equals("null"))
		    cellValue = "";

		if (rowNumber > 0
			&& listEntity
				.isListElementColumnMatchCurrencyFormat(cellKey))
		{
		    cellValue = MoneyViewer.getHtmlCode(cellValue);
		    moneyStyleModifier = " class=\"moneyStyleModifier\"";
		}
		else
		{
		    moneyStyleModifier = "";
		}

		if (rowNumber == 0)
		    isHeader = " class=\"ListViewerTableHeader\"";
		else
		    isHeader = "";

		html += "<td" + isHeader + moneyStyleModifier + ">" + cellValue
			+ "</td>";
	    }

	    html += getSpecificButtonList(listEntity, listEntity.getKeyName(),
		    listEntity.getKeyValue(rowNumber), module, listEntity
			    .getVisibleInternalElementName());

	    html += "</tr>";
	    rowNumber++;
	}
	return html;
    }

    private static String getSpecificButtonList(ListViewable listEntity,
	    String key, String value, Module module, String entityTypeName)
	    throws ModuleException
    {
	String html = "", actionName;

	if (value == null || value.equals("null"))
	    return html;

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

	    html += getButton(buttonCaption, key, value, action, module,
		    listEntity.getButtonConfirmList().contains(actionName),
		    entityTypeName);

	    html += "</td>";
	}

	return html;
    }

    private static String getButton(String buttonCaption, String key,
	    String value, AbstractAction action, Module module,
	    boolean isConfirm, String entityTypeName)
    {
	/*
	 * String formActionUrl = "/" + moduleName + "/" + actionName + "?user="
	 * + currentUserName + "&" + key + "=" + value;
	 */

	String onClickConfirm = "";

	if (isConfirm)
	    onClickConfirm = getOnClickConfirm(buttonCaption, entityTypeName,
		    value);

	String formActionUrl = Servlet.makeLink(module, action);

	String html = "";
	html += "<form method=\"get\" action=\"" + formActionUrl + "\">";

	if (key != null && value != null)
	    html += "<input type=\"hidden\" name=\"" + key + "\" value=\""
		    + value + "\" />";

	html += "<input type=\"submit\" " + onClickConfirm + " value=\""
		+ buttonCaption + "\" />";

	html += "</form>";

	return html;
    }

    private static String getOnClickConfirm(String actionName,
	    String entityTypeName, String value)
    {
	String html = "";

	html += "onclick=\"return confirm(\'Voulez-vous vraiment ";
	html += actionName + " ";
	html += entityTypeName;
	html += " " + value;
	html += "?\')";

	html += "\"";

	return html;
    }
}
