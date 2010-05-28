package newtonERP.viewers;

import java.util.Hashtable;
import java.util.Iterator;

import modules.userRightModule.actions.Login;
import modules.userRightModule.actions.Logout;
import newtonERP.common.ActionLink;
import newtonERP.common.Authentication;
import newtonERP.common.ListModule;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.Module;
import newtonERP.module.generalEntity.SplashScreen;
import newtonERP.serveur.ConfigManager;
import newtonERP.serveur.Servlet;
import newtonERP.viewers.firstStep.AlertViewer;
import newtonERP.viewers.firstStep.BandDiagramViewer;
import newtonERP.viewers.firstStep.BaseViewer;
import newtonERP.viewers.firstStep.FloorViewer;
import newtonERP.viewers.firstStep.ForwardViewer;
import newtonERP.viewers.firstStep.GridViewer;
import newtonERP.viewers.firstStep.ImgViewer;
import newtonERP.viewers.firstStep.PromptViewer;
import newtonERP.viewers.firstStep.SplashScreenViewer;
import newtonERP.viewers.firstStep.StaticTextViewer;
import newtonERP.viewers.secondStep.ButtonLinkViewer;
import newtonERP.viewers.secondStep.LinkViewer;
import newtonERP.viewers.viewables.AlertViewable;
import newtonERP.viewers.viewables.BandDiagramViewable;
import newtonERP.viewers.viewables.FloorViewable;
import newtonERP.viewers.viewables.ForwardViewable;
import newtonERP.viewers.viewables.StaticTextViewable;
import newtonERP.viewers.viewerData.BaseViewerData;
import newtonERP.viewers.viewerData.GridViewerData;
import newtonERP.viewers.viewerData.ImgViewerData;
import newtonERP.viewers.viewerData.PromptViewerData;

/**
 * Represents the main viewer for our ERP
 * @author Guillaume Lacasse, Pascal Lemay
 */
public abstract class Viewer
{
    private static final String defaultEncoding = "iso-8859-1";

    /**
     * Gets the html code by calling the same method in the right viewer
     * 
     * @param entity the entity to view
     * @param moduleName the module of entity
     * @param actionName the action to perform
     * @return the viewer
     * @throws ViewerException an exception that can occur in viewers
     * @throws Exception general exception
     */
    public static String getHtmlCode(AbstractEntity entity, String moduleName,
	    String actionName) throws Exception
    {
	String viewerHtml = "";
	if (entity instanceof BaseViewerData)
	    viewerHtml += BaseViewer.getTopHtmlCode((BaseViewerData) entity);

	if (entity instanceof FloorViewable)
	    viewerHtml += FloorViewer.getHtmlCode((FloorViewable) entity);
	else if (entity instanceof PromptViewerData)
	    viewerHtml += PromptViewer.getHtmlCode((PromptViewerData) entity);
	else if (entity instanceof ForwardViewable)
	    viewerHtml += ForwardViewer.getHtmlCode((ForwardViewable) entity);
	else if (entity instanceof AlertViewable)
	    viewerHtml += AlertViewer.getHtmlCode((AlertViewable) entity);
	else if (entity instanceof StaticTextViewable)
	    viewerHtml += StaticTextViewer
		    .getHtmlCode((StaticTextViewable) entity);
	else if (entity instanceof GridViewerData)
	    viewerHtml += GridViewer.getHtmlCode((GridViewerData) entity);
	else if (entity instanceof ImgViewerData)
	    viewerHtml += ImgViewer.getHtmlCode((ImgViewerData) entity);
	else if (entity instanceof SplashScreen)
	    viewerHtml += SplashScreenViewer.getHtmlCode((SplashScreen) entity);
	else if (entity instanceof BandDiagramViewable)
	    viewerHtml += BandDiagramViewer
		    .getHtmlCode((BandDiagramViewable) entity);
	else if (entity == null)
	    viewerHtml += "<!-- page vide -->";
	else
	    throw new ViewerException("Couldn't find proper viewer for entity");

	if (entity instanceof BaseViewerData)
	    viewerHtml += BaseViewer.getBottomHtmlCode((BaseViewerData) entity);

	return getHeader(moduleName, actionName) + getLeftMenu(moduleName)
		+ viewerHtml + getFooter();
    }

    /**
     * Gets the html code for the header ***(id="header" -> dans css pour mise
     * en page)
     * 
     * @param moduleName the module name
     * @param actionName the action name
     * 
     * @return html
     * @throws Exception si ça fail
     */
    public static String getHeader(String moduleName, String actionName)
	    throws Exception
    {
	String pageTitle = buildPageTitle(moduleName, actionName);

	String header = "";
	header += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
		+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"fr\" >"
		+ "<head><title>"
		+ pageTitle
		+ "</title>"
		+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset="
		+ Viewer.getEncoding() + "\" />";

	// css******************************************************************
	header += "<link rel=\"stylesheet\" type=\"text/css\" title=\"base\" href=\""
		+ ConfigManager.getStyleFileScreen() + "\" media=\"screen\" />";
	header += "<link rel=\"stylesheet\" type=\"text/css\" title=\"base\" href=\""
		+ ConfigManager.getStyleFilePrint() + "\" media=\"print\" />";
	header += "</head><body>";

	if (ConfigManager.isDisplayTopTitle())
	    header += "<div id=\"header\"><h1>" + pageTitle + "</h1></div>";

	return header;
    }

    private static String buildTopMenu() throws Exception
    {
	String html = "";

	html += "<div class=\"topMenu\">";

	if (Authentication.getCurrentUserName().equals("unLogedUser"))
	{
	    // html += "<a href=\"" + new ActionLink. + "\">Login</a>";
	    ActionLink actionLink = new ActionLink("Login", new Login());
	    html += LinkViewer.getHtmlCode(actionLink);
	}
	else
	{
	    html += Authentication.getCurrentUserName();

	    html += " | ";

	    ActionLink actionLink = new ActionLink("Logout", new Logout());
	    html += LinkViewer.getHtmlCode(actionLink);
	}

	html += "</div>";

	return html;
    }

    private static String buildPageTitle(String moduleName, String actionName)
	    throws Exception
    {
	String title = ConfigManager.getDisplayName();

	if (moduleName != null && !moduleName.equals("null"))
	    title += " - " + moduleName;
	if (actionName != null && !actionName.equals("null"))
	    title += " : " + actionName;

	return title;
    }

    /**
     * Gets the html code for left menu (css pour mise en page)
     * 
     * @param moduleName the module name
     * @return html
     * @throws Exception si obtention fail
     */
    public static String getLeftMenu(String moduleName) throws Exception
    {
	Hashtable<String, String> mod = ListModule.getAllModules();
	Iterator<String> keys = mod.keySet().iterator();

	String menuModuleHtml = "";

	String menu = "<div class=\"element_menu\"><!-- Cadre englobant tous les sous-menus -->";
	menuModuleHtml += "<h3>Modules</h3> <!-- Titre du sous-menu -->"
		+ "<ul>";
	String modNameFromIterator = "";

	// pour l'instant un seul sous-menu, autres sous-menus à déterminer
	while (keys.hasNext())
	{
	    modNameFromIterator = keys.next();

	    Module module = ListModule.getModule(modNameFromIterator);
	    if (module.isVisible())
	    {
		if (modNameFromIterator.equals(moduleName))
		{
		    menuModuleHtml += "<li><span class=\"selectedLink\">"
			    + module.getVisibleName() + "</span><ul>";

		    for (String globalActionName : module
			    .getGlobalActionMenuOrReturnDefaultBehavior()
			    .getKeyList())
		    {
			AbstractAction defaultAction = module
				.getGlobalActionMenuOrReturnDefaultBehavior()
				.get(globalActionName);

			String currentLinkHtml = LinkViewer
				.getHtmlCode(new ActionLink(globalActionName,
					defaultAction));

			if (currentLinkHtml.length() > 0)
			    menuModuleHtml += "<li>" + currentLinkHtml
				    + "</li>";
		    }

		    for (ActionLink button : module.getGlobalActionButtonList())
		    {
			String currentLinkHtml = ButtonLinkViewer
				.getHtmlCode(button);
			if (currentLinkHtml != null
				&& currentLinkHtml.length() > 0)
			    menuModuleHtml += "<li>" + currentLinkHtml
				    + "</li>";
		    }
		}
		else
		{
		    menuModuleHtml += "<li><a href=\""
			    + Servlet.makeLink(module) + "\">"
			    + module.getVisibleName() + "</a><ul>";
		}

		menuModuleHtml += "</ul></li>";
	    }
	}
	menuModuleHtml += "</ul>";

	menuModuleHtml = menuModuleHtml.replace("<ul></ul>", "");

	// ferme menu gauche + <div id="body">=style css corp de droite
	return (menu + menuModuleHtml + "</div> <div id=\"body\">" + buildTopMenu());
    }

    /**
     * Gets the html code for the footer ***(id="footer" -> dans css pour mise
     * en page)
     * 
     * @return html
     */
    public static String getFooter()
    {
	String footer = "</div><div id=\"footer\"><p>Copyright © \"G.Lacasse, J.Cloutier, J.Hallée, P.Lemay, G.Therrien\" 2009, tous droits réservés</p></div></body></html>";
	return footer;
    }

    /**
     * @return encodage de charset
     */
    public static String getEncoding()
    {
	return defaultEncoding;
    }
}