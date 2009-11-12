package newtonERP.viewers;

import java.util.Hashtable;
import java.util.Iterator;

import modules.humanResources.entityDefinitions.TimeTable;
import newtonERP.Authentication;
import newtonERP.ListModule;
import newtonERP.module.AbstractEntity;
import newtonERP.module.Module;
import newtonERP.module.exception.ModuleException;
import newtonERP.serveur.Servlet;
import newtonERP.viewers.firstStep.AlertViewer;
import newtonERP.viewers.firstStep.ForwardViewer;
import newtonERP.viewers.firstStep.ListViewer;
import newtonERP.viewers.firstStep.PromptViewer;
import newtonERP.viewers.firstStep.StaticTextViewer;
import newtonERP.viewers.firstStep.TimeTableViewer;
import newtonERP.viewers.viewables.AlertViewable;
import newtonERP.viewers.viewables.ForwardViewable;
import newtonERP.viewers.viewables.ListViewable;
import newtonERP.viewers.viewables.PromptViewable;
import newtonERP.viewers.viewables.StaticTextViewable;

/**
 * Represents the main viewer for our ERP
 * @author Guillaume Lacasse, Pascal Lemay
 */
public abstract class Viewer
{
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
	    String actionName) throws ViewerException, Exception // TODO: remove
    // trow
    // Exception
    {
	String viewerHtml = "";
	if (entity instanceof PromptViewable)
	    viewerHtml = PromptViewer.getHtmlCode((PromptViewable) entity);
	else if (entity instanceof ListViewable)
	    viewerHtml = ListViewer.getHtmlCode((ListViewable) entity);
	else if (entity instanceof ForwardViewable)
	    viewerHtml = ForwardViewer.getHtmlCode((ForwardViewable) entity);
	else if (entity instanceof AlertViewable)
	    viewerHtml = AlertViewer.getHtmlCode((AlertViewable) entity);
	else if (entity instanceof StaticTextViewable)
	    viewerHtml = StaticTextViewer
		    .getHtmlCode((StaticTextViewable) entity);
	else if (entity instanceof TimeTable)
	    viewerHtml = TimeTableViewer.getHtmlCode((TimeTable) entity);
	else if (entity == null)
	    viewerHtml = "<!-- page vide -->";
	else
	    throw new ViewerException("Couldn't find proper viewer for entity");

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
     */
    public static String getHeader(String moduleName, String actionName)
    {
	String pageTitle = buildPageTitle(moduleName, actionName);

	String header = "";
	header += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
		+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"fr\" >"
		+ "<head><title>"
		+ pageTitle
		+ "</title>"
		+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />";

	// css******************************************************************
	header += "<link rel=\"stylesheet\" media=\"screen\" type=\"text/css\" title=\"base\" href=\"/file/style.css\" />";
	header += "</head><body><div id=\"header\"><h1>" + pageTitle
		+ "</h1></div>";

	return header;
    }

    private static String buildTopMenu()
    {
	String html = "";

	html += "<div class=\"topMenu\">";

	if (Authentication.getCurrentUserName().equals("unLogedUser"))
	    html += "<a href=\"/\">Login</a>";
	else
	{
	    html += Authentication.getCurrentUserName();

	    html += " | <a href=\"/UserRightModule/Logout\">Logout</a>";
	}

	html += "</div>";

	return html;
    }

    private static String buildPageTitle(String moduleName, String actionName)
    {
	String title = "NewtonERP";

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
     * @throws ModuleException an exception that can occur in the module
     */
    public static String getLeftMenu(String moduleName) throws ModuleException
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

	    if (modNameFromIterator.equals(moduleName))
	    {
		menuModuleHtml += "<li><span class=\"selectedLink\">"
			+ module.getVisibleName() + "</span><ul>";

		for (String globalActionName : module.getGlobalActionMenu()
			.getKeyList())
		{
		    menuModuleHtml += "<li><a href=\"";
		    menuModuleHtml += Servlet.makeLink(module, module
			    .getGlobalActionMenu().get(globalActionName));
		    menuModuleHtml += "\">" + globalActionName + "</a></li>";
		}
	    }
	    else
	    {
		menuModuleHtml += "<li><a href=\"" + Servlet.makeLink(module)
			+ "\">" + module.getVisibleName() + "</a><ul>";
	    }

	    menuModuleHtml += "</ul></li>";
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
}