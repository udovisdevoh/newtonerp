package newtonERP.viewers.secondStep;

import java.util.Hashtable;

import modules.userRightModule.UserRightModule;
import newtonERP.common.ActionLink;
import newtonERP.common.ListModule;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;

/**
 * Sert à formatter de l'argent
 * @author Guillaume
 * 
 */
public class ButtonLinkViewer
{
    /**
     * @param actionLink representation du lien a effectuer
     * @param entity entity d'ou tiré les parametre, peu etre null
     * @return bouton de lien
     * @throws Exception remonte
     */

    public static String getHtmlCode(ActionLink actionLink,
	    AbstractEntity entity) throws Exception
    {
	String html = "";
	int balloonDivId = LinkViewer.getNextBalloonDivId();

	if (isPermissionAllowed(actionLink))
	{

	    String onClickConfirm = "";

	    if (actionLink.isConfirm())
	    {
		if (entity != null)
		    onClickConfirm = getOnClickConfirm(actionLink.getName(),
			    entity.getSystemName(),
			    ((AbstractOrmEntity) entity)
				    .getNaturalKeyDescription());
		else
		    onClickConfirm = getOnClickConfirm(actionLink.getName());
	    }

	    html += "<ins>";

	    html += "<form method='get' action='" + actionLink.getUrl() + "'>";

	    html += "<div>";

	    Hashtable<String, String> param = actionLink.getParameters(entity);
	    for (String key : param.keySet())
	    {
		html += "<input type='hidden' name='" + key + "' value='"
			+ param.get(key) + "' />";
	    }
	    html += "<input onmouseover='document.getElementById(\"balloon"
		    + balloonDivId
		    + "\").style.visibility=\"visible\"' onmouseout='document.getElementById(\"balloon"
		    + balloonDivId
		    + "\").style.visibility=\"hidden\"' class='submitButton' type='submit' "
		    + onClickConfirm + " value=\"" + actionLink.getName()
		    + "\" />";

	    html += "</div>";

	    html += "</form>";

	    html += "</ins>";

	    html += HelpViewer.getHtmlCode(actionLink, balloonDivId);
	}
	else
	{
	    // html += "<i style=\"font-size:8px\">" + actionLink.getName()
	    // + "</i>";
	}

	return html;
    }

    private static boolean isPermissionAllowed(ActionLink actionLink)
	    throws Exception
    {
	UserRightModule userRightModule = (UserRightModule) ListModule
		.getModule("UserRightModule");

	return userRightModule.isPermissionAllowed(actionLink);
    }

    /**
     * @param actionLink representation du lien a effectuer
     * @return bouton de lien
     * @throws Exception remonte
     */
    public static String getHtmlCode(ActionLink actionLink) throws Exception
    {
	return getHtmlCode(actionLink, null);
    }

    private static String getOnClickConfirm(String actionName,
	    String entityTypeName, String value)
    {
	String html = "";

	html += "onclick='return confirm(\"Voulez-vous vraiment ";
	html += actionName + " ";
	html += entityTypeName;
	html += " " + value;
	html += "?\")";

	html += "'";

	return html;
    }

    private static String getOnClickConfirm(String actionName)
    {
	String html = "";

	html += "onclick='return confirm(\"Voulez-vous vraiment ";
	html += actionName;
	html += "?\")";

	html += "'";

	return html;
    }
}