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
     */
    public static String getTopHtmlCode(BaseViewerData entity)
    {
	String html = "";
	html += "<h2>" + entity.getTitle() + "</h2>";
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
	     * todo: remove when sucefully tested html += " <a href=\"" +
	     * globalAction.getUrl() + "\">"; html += globalAction.getName() +
	     * "</a> ";
	     */
	}
	html += getPrintButton(); // todo: find a way to get this out of here...
	html += "</p>";

	return html;
    }

    private static String getPrintButton()
    {

	return "<form class=\"submitButton\"><input class=\"submitButton\" type=\"button\" value=\"Imprimer\" onclick=\"window.print();return false;\"/></form>";
    }
}
