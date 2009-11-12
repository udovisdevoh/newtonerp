package newtonERP.viewers.firstStep;

import java.util.Vector;

import newtonERP.common.ActionLink;
import newtonERP.serveur.Servlet;
import newtonERP.viewers.ViewerException;
import newtonERP.viewers.secondStep.ButtonLinkViewer;
import newtonERP.viewers.secondStep.ColorViewer;
import newtonERP.viewers.viewerData.GridCaseData;
import newtonERP.viewers.viewerData.GridViewerData;

/**
 * Represents the list viewer for listing data
 * @author Guillaume Lacasse, Pascal Lemay
 */
public class GridViewer
{
    /**
     * Creates the html code for the web page
     * 
     * @param ttEntity the entity to view in list
     * @return html the html code
     * @throws ViewerException an exception that can occur in the viewer
     * @throws Exception general exception
     */
    public static String getHtmlCode(GridViewerData ttEntity)
	    throws ViewerException, Exception
    {
	String html = "";

	html += "<h2>" + ttEntity.getTitle() + "</h2>";
	// TODO: modifier les class de style et mettre du style css a la place
	// un moment donnée
	html += getGlobalLink(ttEntity.getGlobalActions());

	html += "<table class=\"ListViewerTable\" border=\"0\" cellpadding=\"3\" cellspacing=\"0\">";
	html += getTableHeader(ttEntity.getHeader());
	html += getDataRowList(ttEntity.getCases(), ttEntity.getLeftHeader());

	html += "</table>";

	return html;
    }

    private static String getGlobalLink(Vector<ActionLink> globalActions)
	    throws Exception
    {
	String html = "";
	html += "<p>";
	for (ActionLink globalAction : globalActions)
	{

	    html += ButtonLinkViewer.getHtmlCode(globalAction);
	    /*
	     * todo: remove when sucefully tested html += " <a href=\"" +
	     * globalAction.getUrl() + "\">"; html += globalAction.getName() +
	     * "</a> ";
	     */
	}
	html += "</p>";
	return html;
    }

    private static String getTableHeader(GridCaseData[] headerCase)
	    throws Exception
    {

	String html = "";
	// todo: change by th in css
	String isHeader = " class=\"ListViewerTableHeader\"";

	html += "\n<tr>";
	html += "<th></th>";
	for (GridCaseData hCase : headerCase)
	    html += "<th" + isHeader + ">" + getCase(hCase) + "</th>";

	html += "</tr>";
	return html;
    }

    private static String getDataRowList(GridCaseData[][] data,
	    GridCaseData[] leftHeader) throws Exception
    {
	String caseContent;

	String html = "";

	for (int i = 0; i < leftHeader.length; i++)
	{
	    html += "\n<tr>";
	    html += "<th>" + getCase(leftHeader[i]) + "</th>";
	    for (int j = 0; j < data[i].length; j++)
	    {
		int k = 0;
		String rowspan = "";
		if (data[i][j] != null)
		{
		    for (k = i; k < leftHeader.length
			    && data[i][j].equals(data[k][j]); k++)
		    {
			if (k != i)
			    data[k][j] = null;
		    }
		    if (k - i > 1)
			rowspan = " rowspan='" + (k - i) + "'";

		    caseContent = getCase(data[i][j]);

		    html += "<td class=\"gridCell\"" + rowspan
			    + " style=\"background-color:"
			    + ColorViewer.getColor(caseContent)
			    + ";text-align:center\">" + caseContent + "</td>";
		}
	    }
	    html += "</tr>";
	}

	return html;
    }

    private static String getCase(GridCaseData dtCase) throws Exception
    {
	String html = "";
	if (dtCase != null)
	{
	    if (dtCase.getActionLink() != null)
		html += "<a href=\"" + Servlet.makeLink(dtCase.getActionLink())
			+ "?" + dtCase.getParam() + "\">";
	    html += dtCase.getValue();
	    if (dtCase.getActionLink() != null)
		html += "</a>";
	}
	return html;
    }
}
