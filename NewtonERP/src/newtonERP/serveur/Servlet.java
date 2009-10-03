package newtonERP.serveur;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newtonERP.viewers.Viewable;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;

/**
 * @author JoCloutier
 * 
 *         la gestion du serveur, request manager
 * 
 */
public class Servlet extends AbstractHandler
{
    CommandRouteur cmdRouter = new CommandRouteur();

    /**
     * lance le serveur sur le port indique
     * 
     * @param port numero de port a utiliser
     */
    public Servlet(int port)
    {
	// lance le serveur web

	Server server = new Server(port);
	server.setHandler(this);
	try
	{
	    server.start();
	} catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    public void handle(String target, HttpServletRequest request,
	    HttpServletResponse response, int dispatch) throws IOException,
	    ServletException
    {
	urlToAction(target, request);

	// on formatte la reponse
	response.setContentType("text/html");
	response.setStatus(HttpServletResponse.SC_OK);
	response.getWriter().println("<h1>Hello</h1>"); // TODO: replace by
	// a call to mainViewer
	((Request) request).setHandled(true);
    }

    /**
     * @param target url d'apelle
     * @param request la request
     * @return le Viewable
     */
    @SuppressWarnings("unchecked")
    public Viewable urlToAction(String target, HttpServletRequest request)
    {
	String moduleName;
	String action;
	String ModuleGetter;
	Hashtable<String, String> parameter = new Hashtable<String, String>();

	// on trouve le moduleName et l'actionName dans l'url
	Matcher urlMatch = Pattern.compile("/(.*)/(.*)/(.*)").matcher(target);
	urlMatch.matches();
	try
	{
	    moduleName = urlMatch.group(1);
	} catch (IllegalStateException e)
	{
	    moduleName = "home";
	}
	try
	{
	    action = urlMatch.group(2);
	} catch (IllegalStateException e)
	{
	    action = "index";
	}
	try
	{
	    ModuleGetter = urlMatch.group(3);
	} catch (IllegalStateException e)
	{
	    ModuleGetter = null;
	}

	try
	{
	    // on trouve les parametres pour les mettre dans le hashtable

	    Enumeration e = request.getParameterNames();
	    while (e.hasMoreElements())
	    {
		String paramName = (String) e.nextElement();
		parameter.put(paramName, request.getParameter(paramName));
	    }
	} catch (NullPointerException e)
	{
	    parameter = null;
	}

	return cmdRouter.routeCommand(moduleName, action, ModuleGetter,
		parameter);
    }
}
