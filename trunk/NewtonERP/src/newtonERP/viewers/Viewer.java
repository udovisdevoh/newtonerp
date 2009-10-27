package newtonERP.viewers;

import java.util.Hashtable;
import java.util.Iterator;

import newtonERP.ListModule;
import newtonERP.module.AbstractEntity;
import newtonERP.module.Module;
import newtonERP.module.exception.ModuleException;
import newtonERP.serveur.Servlet;
import newtonERP.viewers.viewables.AlertViewable;
import newtonERP.viewers.viewables.ForwardViewable;
import newtonERP.viewers.viewables.ListViewable;
import newtonERP.viewers.viewables.PromptViewable;
import newtonERP.viewers.viewables.StaticTextViewable;

/**
 * @author Guillaume Lacasse, Pascal Lemay
 * 
 *         Represents the main viewer for our ERP
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
	header += "<style type=\"text/css\">"
		+ "body	/*mise en page pour tout le corp*/{width: 1000px;margin: auto;margin-top: 5px;margin-bottom: 5px;background-color: #ecf1f5;}"
		+ "#header{width: 1000px;height: 75px;background-color:#d3d8e1;color:black;font-family: Arial, \"Arial Black\", \"Times New Roman\", Times, serif;border: 1px solid black;margin-bottom: 5px;}"
		+ "h1 /* Gros titres */{color: black;font-family: Arial, \"Arial Black\", \"Times New Roman\", Times, serif;}"
		+ "h2 /* sous-titres */{color: black;font-family: Arial, \"Arial Black\", \"Times New Roman\", Times, serif;}"
		+ "#menu{float: left; /* Le menu flottant à gauche */width: 140px;}"
		+ ".element_menu/*sous-menu*/{background-color: #d3d8e1;border: 1px solid black;margin-bottom: 10px;}"
		+ ".element_menu h3 /* Tous les titres de sous-menus */{color: black;font-family: Arial, \"Arial Black\", \"Times New Roman\", Times, serif;text-align: center;}"
		+ ".element_menu ul /* Listes à puces de sous-menu */{padding: 0px;padding-left: 20px;margin: 0px;margin-bottom: 5px;}"
		+ ".element_menu a /* Tous les liens se trouvant dans un menu */{color: #0081d7;}"
		+ ".element_menu a:hover {color: red;}"
		// + "li {list-style-type:square;color:#000;}"
		// + "li li {list-style-type:square;color:#FFF;}"
		+ ".checkBoxAtomicElement td {border-style:solid;border-width:2px;border-color:#fbfcfd #a3aebf #a3aebf #fbfcfd;margin-right:10px;margin-bottom:10px;padding:3px;padding-right:6px;}"
		+ "#body /*mise en page du corp principal à droite du menu principal*/{margin-left: 150px; /*Une marge à gauche pour pousser le corps, afin qu'il ne passe plus sous le menu*/margin-bottom: 10px;padding: 5px;color: black;background-color: #d3d8e1;border: 1px solid black}"
		+ "#home {height: 600px;}"// tempo pour home
		+ ".ListViewerTable td {background-color:#FFF; border-style:solid;border-width:2px; border-color:#DDD #DDD #AAA #AAA;}"
		+ ".ListViewerTable td.ListViewerTableHeader {background-color:#BBF; border-style:solid;border-width:2px; border-color:#AAD #AAD #77A #77A;}"
		+ "#footer{text-align: center;color: black;background-color: #d3d8e1;border: 1px solid black;}</style>";
	// *********************************************************************

	header += "</head><body><div id=\"header\"><h1>" + pageTitle
		+ "</h1></div>";

	return header;
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

	String menu = "<div id =\"menu\"><!-- Cadre englobant tous les sous-menus -->";
	menuModuleHtml += "<div class=\"element_menu\"> <!-- Cadre correspondant à un sous-menu -->"
		+ "<h3>Modules</h3> <!-- Titre du sous-menu -->" + "<ul>";
	String modNameFromIterator = "";

	// pour l'instant un seul sous-menu, autres sous-menus à déterminer
	while (keys.hasNext())
	{
	    modNameFromIterator = keys.next();

	    Module module = ListModule.getModule(modNameFromIterator);
	    menuModuleHtml += "<li><a href=\"" + Servlet.makeLink(module)
		    + "\">" + modNameFromIterator + "</a><ul>";

	    if (modNameFromIterator.equals(moduleName))
	    {
		for (String globalActionName : module.getGlobalActionMenu()
			.keySet())
		{
		    menuModuleHtml += "<li><a href=\"";
		    menuModuleHtml += Servlet.makeLink(module, module
			    .getGlobalActionMenu().get(globalActionName));
		    menuModuleHtml += "\">" + globalActionName + "</li>";
		}
	    }

	    menuModuleHtml += "</ul></li>";
	}
	menuModuleHtml += "</ul></div>";// ferme liste et ce Module

	// ferme menu gauche + <div id="body">=style css corp de droite
	return (menu + menuModuleHtml + "</div> <div id=\"body\">");
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