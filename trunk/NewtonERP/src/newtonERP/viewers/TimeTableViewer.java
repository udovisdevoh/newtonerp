package newtonERP.viewers;

import modules.humanResources.entityDefinitions.CaseTable;
import modules.humanResources.entityDefinitions.TimeTable;
import newtonERP.serveur.Servlet;

/**
 * Represents the list viewer for listing data
 * @author Guillaume Lacasse, Pascal Lemay
 */
public class TimeTableViewer
{
    /**
     * Creates the html code for the web page
     * 
     * @param ttEntity the entity to view in list
     * @return html the html code
     * @throws ViewerException an exception that can occur in the viewer
     * @throws Exception general exception
     */
    public static String getHtmlCode(TimeTable ttEntity)
	    throws ViewerException, Exception
    {
	String html = "";

	html += "<h2>" + ttEntity.getTitle() + "</h2>";
	// TODO: modifier les class de style et mettre du style css a la place
	// un moment donnée
	html += "<table class=\"ListViewerTable\" border=\"0\" cellpadding=\"3\" cellspacing=\"0\">";

	html += getTableHeader(ttEntity.getHeader());
	html += getDataRowList(ttEntity.getCases(), ttEntity.getLeftHeader());

	html += "</table>";

	return html;
    }

    private static String getTableHeader(CaseTable[] headerCase)
	    throws Exception
    {

	String html = "";
	// todo: change by th in css
	String isHeader = " class=\"ListViewerTableHeader\"";

	html += "\n<tr>";
	html += "<th></th>";
	for (CaseTable hCase : headerCase)
	    html += "<th" + isHeader + ">" + getCase(hCase) + "</th>";

	html += "</tr>";
	return html;
    }

    private static String getDataRowList(CaseTable[][] data,
	    CaseTable[] leftHeader) throws Exception
    {

	String html = "";
	// todo: change by th in css
	String isHeader = " class=\"ListViewerTableHeader\"";

	for (int i = 0; i < leftHeader.length; i++)
	{
	    html += "\n<tr>";
	    html += "<th" + isHeader + ">" + getCase(leftHeader[i]) + "</th>";
	    for (CaseTable dataCase : data[i])
		html += "<td>" + getCase(dataCase) + "</td>";
	    html += "</tr>";
	}

	return html;
    }

    private static String getCase(CaseTable dtCase) throws Exception
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
