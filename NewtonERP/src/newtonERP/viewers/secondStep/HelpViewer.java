package newtonERP.viewers.secondStep;

import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;

/**
 * Sert à voir l'aide de l'action
 * @author Guillaume Lacasse
 */
public class HelpViewer
{

    /**
     * @param actionLink lien d'action
     * @param helpDivId id DOM du div d'aide
     * @return retourne le code HTML
     */
    public static String getHtmlCode(ActionLink actionLink, int helpDivId)
    {
	String html = "<div class=\"HelpBalloon\" id=\"balloon" + helpDivId
		+ "\">";

	html += "Cliquez ici pour ";

	String actionName = getActionName(actionLink);
	String entityName = getEntityName(actionLink);

	html += actionName;

	for (String word : entityName.split(" "))
	{
	    if (!actionName.trim().toLowerCase().contains(
		    word.toLowerCase().trim()))
		html += " " + word;
	}

	html += "</div>";

	return html;
    }

    private static String getActionName(ActionLink actionLink)
    {
	if (actionLink.getAction().getDetailedDescription() != null)
	    return actionLink.getAction().getDetailedDescription();

	return actionLink.getName();
    }

    private static String getEntityName(ActionLink actionLink)
    {

	if (actionLink.getAction() instanceof BaseAction)
	{
	    BaseAction baseAction = (BaseAction) actionLink.getAction();

	    if (baseAction.getEntity().getDetailedDescription() != null)
		return baseAction.getEntity().getDetailedDescription();

	    return baseAction.getEntity().getVisibleName();
	}
	else if (actionLink.getAction().getEntityUsable() != null)
	{

	    if (actionLink.getAction().getEntityUsable() instanceof AbstractOrmEntity)
	    {
		AbstractOrmEntity abstractOrmEntity = (AbstractOrmEntity) actionLink
			.getAction().getEntityUsable();

		if (abstractOrmEntity.getDetailedDescription() != null)
		    return abstractOrmEntity.getDetailedDescription();

		return abstractOrmEntity.getVisibleName();
	    }
	    return actionLink.getAction().getEntityUsable().getSystemName();
	}
	return "";
    }
}
