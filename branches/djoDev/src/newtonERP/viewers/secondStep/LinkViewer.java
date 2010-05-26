package newtonERP.viewers.secondStep;

import newtonERP.common.ActionLink;

/**
 * Sert à formatter de l'argent
 * @author Guillaume
 * 
 */
public class LinkViewer
{
    /**
     * @param actionLink representation du lien a effectuer
     * @return bouton de lien
     * @throws Exception remonte
     */
    public static String getHtmlCode(ActionLink actionLink) throws Exception
    {
	// String onClickConfirm = "";

	/*
	 * todo: a remettre des que les viewer son plus generaliser que
	 * presentement if (isConfirm) if (action.getEntityUsable() == null)
	 * onClickConfirm = getOnClickConfirm(buttonCaption,"", value); else
	 * onClickConfirm = getOnClickConfirm(buttonCaption,
	 * action.getEntityUsable() .getVisibleInternalElementName(), value);
	 */

	String html = "";
	html += " <a href='" + actionLink.getUrlParam() + "'>";
	html += actionLink.getName() + "</a> ";

	return html;

    }

    @SuppressWarnings("unused")
    // todo: retire lorsque la correction est aporter plus haut
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