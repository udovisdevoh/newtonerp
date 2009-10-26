package newtonERP.viewers;

import java.util.Hashtable;
import java.util.Iterator;

import newtonERP.ListModule;
import newtonERP.module.AbstractEntity;
import newtonERP.module.Module;
import newtonERP.module.exception.ModuleException;
import newtonERP.serveur.Servlet;
import newtonERP.viewers.viewables.ForwardViewable;
import newtonERP.viewers.viewables.ListViewable;
import newtonERP.viewers.viewables.PromptViewable;

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
     * @param entity
     * @return the viewer
     * @throws ViewerException
     */
    public static String getHtmlCode(AbstractEntity entity)
	    throws ViewerException, Exception // TODO: remove trow Exception
    {
	String viewerHtml = "";
	if (entity instanceof PromptViewable)
	    viewerHtml = PromptViewer.getHtmlCode((PromptViewable) entity);
	else if (entity instanceof ListViewable)
	    viewerHtml = ListViewer.getHtmlCode((ListViewable) entity);
	else if (entity == null) // Home
	    viewerHtml = HomeViewer.getHomePage();
	else if (entity instanceof ForwardViewable)
	    viewerHtml = ForwardViewer.getHtmlCode((ForwardViewable) entity);
	else
	    throw new ViewerException("Couldn't find proper viewer for entity");

	return getHeader() + getLeftMenu() + viewerHtml + getFooter();
    }

    /**
     * Gets the html code for the header ***(id="header" -> dans css pour mise
     * en page)
     * 
     * @return html
     */
    public static String getHeader()
    {
	String header = "";
	header += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
		+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"fr\" >"
		+ "<head><title>NewtonERP:"
		+ "nom de module ici"
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
		+ "#body /*mise en page du corp principal à droite du menu principal*/{margin-left: 150px; /*Une marge à gauche pour pousser le corps, afin qu'il ne passe plus sous le menu*/margin-bottom: 10px;padding: 5px;color: black;background-color: #d3d8e1;border: 1px solid black}"
		+ "#home {height: 600px;}"// tempo pour home
		+ ".ListViewerTable td {background-color:#FFF; border-style:solid;border-width:2px; border-color:#DDD #DDD #AAA #AAA;}"
		+ ".ListViewerTable td.ListViewerTableHeader {background-color:#BBF; border-style:solid;border-width:2px; border-color:#AAD #AAD #77A #77A;}"
		+ "#footer{text-align: center;color: black;background-color: #d3d8e1;border: 1px solid black;}</style>";
	// *********************************************************************

	header += "</head><body><div id=\"header\"><h1>NewtonERP</h1></div>";

	return header;
    }

    /**
     * Gets the html code for left menu (css pour mise en page)
     * 
     * @return html
     * @throws ModuleException
     */
    public static String getLeftMenu() throws ModuleException
    {
	Hashtable<String, String> mod = ListModule.getAllModules();
	Iterator<String> keys = mod.keySet().iterator();

	String menuModuleHtml = "";

	String menu = "<div id =\"menu\"><!-- Cadre englobant tous les sous-menus -->";
	menuModuleHtml += "<div class=\"element_menu\"> <!-- Cadre correspondant à un sous-menu -->"
		+ "<h3>Modules</h3> <!-- Titre du sous-menu -->" + "<ul>";
	String modName = "";

	// pour l'instant un seul sous-menu, autres sous-menus à déterminer
	while (keys.hasNext())
	{
	    modName = keys.next();

	    Module module = ListModule.getModule(modName);
	    menuModuleHtml += "<li><a href=\"" + Servlet.makeLink(module)
		    + "\">" + modName + "</a><ul>";

	    for (String globalActionName : module.getGlobalActionMenu()
		    .keySet())
	    {
		menuModuleHtml += "<li><a href=\"";
		menuModuleHtml += Servlet.makeLink(module, module
			.getGlobalActionMenu().get(globalActionName));
		menuModuleHtml += "\">" + globalActionName + "</li>";
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