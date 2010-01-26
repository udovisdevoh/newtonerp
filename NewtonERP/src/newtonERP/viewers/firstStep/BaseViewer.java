package newtonERP.viewers.firstStep;

import newtonERP.common.ActionLink;
import newtonERP.viewers.secondStep.ButtonLinkViewer;
import newtonERP.viewers.viewerData.BaseViewerData;

/**
 * permet l'affichage des BaseViewerData
 * @author CloutierJo
 * 
 */
public class BaseViewer
{

    /**
     * donne le code html des element de base du haut
     * @param entity l'entity cntenant ces information
     * @return le code html
     * @throws Exception remonte
     */
    public static String getTopHtmlCode(BaseViewerData entity) throws Exception
    {
	String html = "";
	if (entity.getBackLink() != null)
	    html += "<p class='backLink'><a class='backLink' href='"
		    + entity.getBackLink().getUrlParam() + "'> &lt; "
		    + entity.getBackLink().getName() + "</a></p>";

	html += "<h2>" + entity.getTitle() + "</h2>";

	html += getMessage(entity);

	return html;
    }

    /**
     * @param entity
     * @param html
     * @return
     */
    private static String getMessage(BaseViewerData entity)
    {
	String html = "";
	if (entity.getAlertMessageList() != null)
	    for (String message : entity.getAlertMessageList())
		html += "<p class='errorMessage'>" + message + "</p>";

	if (entity.getNormalMessageList() != null)
	    for (String message : entity.getNormalMessageList())
		html += "<p class='normalMessage'>" + message + "</p>";
	return html;
    }

    /**
     * donne le code html des element de base du bas
     * @param entity l'entity cntenant ces information
     * @return le code html
     * @throws Exception remonte
     */
    public static String getBottomHtmlCode(BaseViewerData entity)
	    throws Exception
    {
	String html = "";
	html += "<p>";
	for (ActionLink globalAction : entity.getGlobalActions())
	{
	    html += ButtonLinkViewer.getHtmlCode(globalAction);
	    /*
	     * todo: remove when sucefully tested html += " <a href='" +
	     * globalAction.getUrl() + "'>"; html += globalAction.getName() +
	     * "</a> ";
	     */
	}
	html += getPrintButton(); // todo: find a way to get this out of here...
	html += "</p>";

	return html;
    }

    private static String getPrintButton()
    {
	return "<ins><form class='submitButton' action='.\\'><div><input class='submitButton' type='submit' value='Imprimer' onclick='window.print();return false;'/></div></form></ins>";
    }
}
