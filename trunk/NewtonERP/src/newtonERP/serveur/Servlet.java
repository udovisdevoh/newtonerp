package newtonERP.serveur;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import newtonERP.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;
import newtonERP.viewers.ErrorViewer;
import newtonERP.viewers.Viewer;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.servlet.ServletHandler;

/**
 * @author JoCloutier
 * 
 *         la gestion du serveur, request manager
 * 
 */
public class Servlet extends ServletHandler
{

    CommandRouteur cmdRouter = new CommandRouteur();

    public void handle(String target, HttpServletRequest request,
	    HttpServletResponse response, int dispatch) throws IOException,
	    ServletException
    {
	HttpSession session = request.getSession(true);
	/*
	 * session.setAttribute("SESSION_UserName", "admin"); // TODO: remove //
	 * when // loggin existe
	 */

	Authentication.setCurrentUserName((String) session
		.getAttribute("SESSION_UserName"));
	System.out.println(Authentication.getCurrentUserName());
	String pageContent = "";
	try
	{
	    AbstractEntity viewEntity = urlToAction(target, request);

	    pageContent = Viewer.getHtmlCode(viewEntity);

	} catch (Exception e)
	{
	    e.printStackTrace();

	    StringWriter sw = new StringWriter();
	    e.printStackTrace(new PrintWriter(sw));
	    String stacktrace = sw.toString();
	    pageContent = ErrorViewer.getErrorPage(stacktrace);

	}

	// on formate la reponse
	response.setContentType("text/html");
	response.setStatus(HttpServletResponse.SC_OK);

	response.getWriter().println(pageContent);
	((Request) request).setHandled(true);

	session.setAttribute("SESSION_UserName", Authentication
		.getCurrentUserName());
    }

    /**
     * @param target url d'apelle
     * @param request la request
     * @return le Viewable
     * @throws Exception remonte
     */
    @SuppressWarnings("unchecked")
    public AbstractEntity urlToAction(String target, HttpServletRequest request)
	    throws Exception
    {
	String moduleName;
	String actionName;
	String entityName;
	Hashtable<String, String> parameter = new Hashtable<String, String>();

	// on trouve le moduleName et l'actionName dans l'url
	Matcher urlMatch = Pattern.compile("(/(\\w*)(/(\\w*)(/(\\w*))?)?)?")
		.matcher(target);
	urlMatch.matches();

	moduleName = urlMatch.group(2);
	if (moduleName == null || moduleName.isEmpty())
	    moduleName = "Home";

	actionName = urlMatch.group(4);
	if (actionName == null || actionName.isEmpty())
	    actionName = "default";

	entityName = urlMatch.group(6);

	System.err.println(target);

	// on trouve les parametres pour les mettre dans le hashtable

	Enumeration e = request.getParameterNames();
	while (e.hasMoreElements())
	{
	    String paramName = (String) e.nextElement();
	    parameter.put(paramName, request.getParameter(paramName));
	}

	return cmdRouter.routeCommand(moduleName, actionName, entityName,
		parameter);
    }

    /**
     * @param module module a lier
     * @return le lien relatif vers les ressource demander
     */
    static public String makeLink(Module module)
    {
	return "/" + module.getClass().getSimpleName();
    }

    /**
     * @param module module a lier
     * @param action action a lier
     * @return le lien relatif vers les ressource demander
     */
    static public String makeLink(Module module, AbstractAction action)
    {
	String link = "/" + module.getClass().getSimpleName();
	if (action instanceof BaseAction)
	{
	    String actionName = ((BaseAction) action).getActionName();
	    String entityName = ((BaseAction) action).getEntity().getClass()
		    .getSimpleName();
	    link += "/" + actionName + "/" + entityName;
	}
	else
	{
	    link += "/" + action.getClass().getSimpleName();
	}

	return link;
    }
}
