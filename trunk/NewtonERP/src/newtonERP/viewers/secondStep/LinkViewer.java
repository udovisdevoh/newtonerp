package newtonERP.viewers.secondStep;

import modules.userRightModule.UserRightModule;
import newtonERP.common.ActionLink;
import newtonERP.common.ListModule;

/**
 * Sert à formatter de l'argent
 * @author Guillaume
 * 
 */
public class LinkViewer
{
    private static int currentBalloonDivId = 0;

    /**
     * @param actionLink representation du lien a effectuer
     * @return bouton de lien
     * @throws Exception remonte
     */
    public static String getHtmlCode(ActionLink actionLink) throws Exception
    {
	// String onClickConfirm = "";

	int balloonDivId = getNextBalloonDivId();

	/*
	 * todo: a remettre des que les viewer son plus generaliser que
	 * presentement if (isConfirm) if (action.getEntityUsable() == null)
	 * onClickConfirm = getOnClickConfirm(buttonCaption,"", value); else
	 * onClickConfirm = getOnClickConfirm(buttonCaption,
	 * action.getEntityUsable() .getVisibleInternalElementName(), value);
	 */

	String html = "";

	if (isPermissionAllowed(actionLink))
	{
	    html += " <a href='"
		    + actionLink.getUrlParam()
		    + "' onmouseover='document.getElementById(\"balloon"
		    + balloonDivId
		    + "\").style.visibility=\"visible\"' onmouseout='document.getElementById(\"balloon"
		    + balloonDivId + "\").style.visibility=\"hidden\"'>";
	    html += actionLink.getName() + "</a> ";

	    html += HelpViewer.getHtmlCode(actionLink, balloonDivId);
	}

	return html;

    }

    /**
     * @return prochain ID de bulle d'aide
     */
    public static int getNextBalloonDivId()
    {
	currentBalloonDivId++;
	return currentBalloonDivId;
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

    private static boolean isPermissionAllowed(ActionLink actionLink)
	    throws Exception
    {
	UserRightModule userRightModule = (UserRightModule) ListModule
		.getModule("UserRightModule");

	return userRightModule.isPermissionAllowed(actionLink);
    }
}