package newtonERP.serveur;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @SuppressWarnings("unchecked")
    public void handle(String target, HttpServletRequest request,
	    HttpServletResponse response, int dispatch) throws IOException,
	    ServletException
    {
	String moduleName;
	String action;
	String ModuleGetter;
	Hashtable<String, String> parameter = new Hashtable<String, String>();

	// on trouve le moduleName et l'actionName dans l'url
	// TODO: ajouté un parametre modulgetter
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

	// on trouve les parametres pour les mettre dans le hashtable
	for (Enumeration e = request.getParameterNames(); e.hasMoreElements();)
	{
	    String paramName = (String) e.nextElement();
	    parameter.put(paramName, request.getParameter(paramName));
	}

	cmdRouter.routeCommand(moduleName, action, ModuleGetter, parameter);

	// on formatte la reponse
	response.setContentType("text/html");
	response.setStatus(HttpServletResponse.SC_OK);
	response.getWriter().println("<h1>Hello</h1>"); // TODO: replace by
	// a call to mainViewer
	((Request) request).setHandled(true);
    }
}
