package newtonERP.viewers.firstStep;

import java.util.Vector;

import newtonERP.common.ActionLink;
import newtonERP.module.AbstractEntity;
import newtonERP.viewers.ViewerException;
import newtonERP.viewers.secondStep.ButtonLinkViewer;
import newtonERP.viewers.secondStep.PageSelectorViewer;
import newtonERP.viewers.secondStep.SearchBarViewer;
import newtonERP.viewers.secondStep.colorViewer.ColorViewer;
import newtonERP.viewers.viewerData.GridCaseData;
import newtonERP.viewers.viewerData.GridViewerData;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Represents the list viewer for listing data
 * @author Guillaume Lacasse, Pascal Lemay
 */
public class GridViewer
{
    /**
     * Creates the html code for the web page
     * 
     * @param gridEntity the entity to view in list
     * @return html the html code
     * @throws ViewerException an exception that can occur in the viewer
     * @throws Exception general exception
     */
    public static String getHtmlCode(GridViewerData gridEntity)
	    throws ViewerException, Exception
    {
	String html = "";

	// TODO: modifier les class de style et mettre du style css a la place
	// un moment donnée

	String pageSelectorHtml = null;
	String searchBarHtml = null;

	if (gridEntity instanceof ListViewerData)
	{
	    if (((ListViewerData) (gridEntity)).getSearchBar() != null)
	    {
		searchBarHtml = SearchBarViewer
			.getHtmlCode(((ListViewerData) (gridEntity))
				.getSearchBar());
		html += searchBarHtml;
	    }

	    if (((ListViewerData) (gridEntity)).getPageSelector() != null)
	    {
		pageSelectorHtml = PageSelectorViewer
			.getHtmlCode(((ListViewerData) (gridEntity))
				.getPageSelector());
		html += pageSelectorHtml;
	    }
	}

	html += "<table class='ListViewerTable' border='0' cellpadding='3' cellspacing='0'>";
	html += getTableHeader(gridEntity.getHeader(), gridEntity
		.getLeftHeader().length > 0);
	html += getDataRowList(gridEntity);

	html += "</table>";

	if (pageSelectorHtml != null)
	    html += pageSelectorHtml;

	return html;
    }

    private static String getTableHeader(GridCaseData[] headerCase,
	    boolean hasLeftHeader) throws Exception
    {

	String html = "";
	// todo: change by th in css
	String isHeader = " class='ListViewerTableHeader'";
	int k = 0;
	String colspan;
	html += "\n<tr>";
	if (hasLeftHeader)
	    html += "<th></th>";
	for (int i = 0; i < headerCase.length; i++)
	{
	    colspan = "";
	    if (headerCase[i] != null)
	    {
		for (k = i; k < headerCase.length
			&& headerCase[i].equals(headerCase[k]); k++)
		{
		    if (k != i)
			headerCase[k] = null;
		}
		if (k - i > 1)
		    colspan = " colspan='" + (k - i) + "'";

		html += "<th" + isHeader + colspan + ">"
			+ getCase(headerCase[i]) + "</th>";
	    }
	}
	html += "</tr>";
	return html;
    }

    private static String getDataRowList(GridViewerData gridData)
	    throws Exception
    {
	GridCaseData[][] data = gridData.getCases();
	GridCaseData[] leftHeader = gridData.getLeftHeader();
	String caseContent;
	StringBuilder html = new StringBuilder(); // optimisation du string
	// append

	for (int i = 0; i < data.length; i++)
	{
	    html.append("\n<tr>");
	    if (i < leftHeader.length)
		html.append("<th>" + getCase(leftHeader[i]) + "</th>");

	    for (int j = 0; j < data[i].length; j++)
	    {
		int k = 0;
		String rowspan = "";
		String color = "";
		String moneyStyleModifier = "";
		if (data[i][j] != null)
		{
		    if (i == 0 || (i > 0 && !data[i][j].equals(data[i - 1][j]))
			    || !gridData.isSpanSimilar())
		    {
			for (k = i; k < data.length
				&& data[i][j].equals(data[k][j])
				&& gridData.isSpanSimilar(); k++)
			{
			    // nothing to do...
			}
			if (k - i > 1)
			    rowspan = " rowspan='" + (k - i) + "'";

			caseContent = getCase(data[i][j]);

			if (caseContent.endsWith("$"))
			    moneyStyleModifier = " moneyStyleModifier";

			if (gridData.isColored() || data[i][j].isColored())
			    color = " background-color:"
				    + ColorViewer.getColor(caseContent) + ";";

			html.append("<td class='gridCell" + moneyStyleModifier
				+ "' " + rowspan + "style='" + color
				+ "text-align:center;'>" + caseContent
				+ "</td>");
		    }
		}
		else
		    html.append("<td></td>");
	    }
	    if (gridData instanceof ListViewerData)
		html.append(getSpecificButton(gridData
			.getSpecificActionButtonList(),
			((ListViewerData) gridData).getEntity().get(i)));
	    else
		html.append(getSpecificButton(gridData
			.getSpecificActionButtonList(), null));

	    html.append("</tr>");
	}

	return html.toString();
    }

    private static String getSpecificButton(Vector<ActionLink> actionLinks,
	    AbstractEntity entity) throws Exception
    {
	String html = "";

	for (ActionLink action : actionLinks)
	{
	    html += "<td>";

	    html += ButtonLinkViewer.getHtmlCode(action, entity);

	    html += "</td>";
	}

	return html;

    }

    private static String getCase(GridCaseData dtCase) throws Exception
    {
	String html = "";
	if (dtCase != null)
	{
	    if (dtCase.getAction() != null)
		html += "<a href='" + dtCase.getUrlParam() + "'>";
	    html += dtCase.getName();
	    if (dtCase.getAction() != null)
		html += "</a>";
	}
	return html;
    }
}
